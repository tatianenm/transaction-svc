package br.com.coffeeandit.transactionsvc.domain;

import br.com.coffeeandit.transactionsvc.domain.exception.DomainBusinessException;
import br.com.coffeeandit.transactionsvc.domain.validator.TransactionValidation;
import br.com.coffeeandit.transactionsvc.dto.Conta;
import br.com.coffeeandit.transactionsvc.dto.RequestTransactionDto;
import br.com.coffeeandit.transactionsvc.dto.TransactionDto;
import br.com.coffeeandit.transactionsvc.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class TransactionBusiness {
    private TransactionValidation transactionValidation;
    private TransactionRepository transactionRepository;


    public TransactionBusiness(TransactionValidation transactionValidation, TransactionRepository transactionRepository) {
        this.transactionValidation = transactionValidation;
        this.transactionRepository = transactionRepository;

    }

    public void save(final RequestTransactionDto requestTransactionDto) throws DomainBusinessException {
        if (Objects.isNull(requestTransactionDto.getData())) {
            requestTransactionDto.setData(LocalDateTime.now());
            transactionValidation.validate(requestTransactionDto);
            transactionRepository.save(requestTransactionDto);
        }
    }

    public void atualizarTransacao(TransactionDto transactionDto) {
        log.info("Atualizando transação: {}", transactionDto);
        transactionRepository.save(transactionDto);
    }

    public void aprovarTransacao(TransactionDto transEvent) {
        var transacaoDto = buscarTransacao(transEvent);
        if (transacaoDto.isPresent()) {
            var transDb = transacaoDto.get();
            if (!transDb.isAnalisada() && transDb.isAnalisada()) {
                transDb.aprovar();
                atualizarTransacao(transDb);
                log.info("Transação aprovada: {}", transEvent);
            }
        }

    }

    public Optional<TransactionDto> buscarTransacao(TransactionDto transactionDto) {
        return transactionRepository.findById(transactionDto.getUuid());
    }

    public List<TransactionDto> findByConta(final Long codigoAgencia, final Long codigoConta) {
        return transactionRepository.findByConta(new Conta(codigoAgencia, codigoConta));

    }
}
