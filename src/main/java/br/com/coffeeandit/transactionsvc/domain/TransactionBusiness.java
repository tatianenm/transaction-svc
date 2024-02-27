package br.com.coffeeandit.transactionsvc.domain;

import br.com.coffeeandit.transactionsvc.domain.exception.DomainBusinessException;
import br.com.coffeeandit.transactionsvc.domain.validator.TransactionValidation;
import br.com.coffeeandit.transactionsvc.dto.RequestTransactionDto;
import br.com.coffeeandit.transactionsvc.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TransactionBusiness {
    private TransactionValidation transactionValidation;
    private TransactionRepository transactionRepository;


    public TransactionBusiness(TransactionValidation transactionValidation, TransactionRepository transactionRepository) {
        this.transactionValidation = transactionValidation;
        this.transactionRepository = transactionRepository;

    }

    public void save(final RequestTransactionDto requestTransactionDto) throws DomainBusinessException {
        if(Objects.isNull(requestTransactionDto.getData())) {
            requestTransactionDto.setData(LocalDateTime.now());
            transactionValidation.validate(requestTransactionDto);
            transactionRepository.save(requestTransactionDto);
        }
    }
}
