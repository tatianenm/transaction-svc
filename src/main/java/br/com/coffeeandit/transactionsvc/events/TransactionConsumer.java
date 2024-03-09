package br.com.coffeeandit.transactionsvc.events;

import br.com.coffeeandit.transactionsvc.domain.TransactionBusiness;
import br.com.coffeeandit.transactionsvc.dto.SituacaoEnum;
import br.com.coffeeandit.transactionsvc.dto.TransactionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
public class TransactionConsumer {

    private final ObjectMapper objectMapper;
    private final TransactionBusiness transactionBusiness;

    public TransactionConsumer(ObjectMapper objectMapper, TransactionBusiness transactionBusiness) {
        this.objectMapper = objectMapper;
        this.transactionBusiness = transactionBusiness;
    }

    @KafkaListener(topics = "${events.consumeTopic}")
    public void consumeTransaction(String message) {
        try {
            var transactionDto = getTransaction(message);
            transactionBusiness.atualizarTransacao(transactionDto);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @KafkaListener(topics = "${events.returnTopic}")
    public void consumeTransactionExtorno(String message) {
        try {
            var transactionDto = getTransaction(message);
            log.info("Transação recebida da análise: {}", transactionDto);
            if (!transactionDto.isAnalisada()) {
                return;
            } else {
                log.info("Transação analisada: {}", transactionDto);
                transactionBusiness.aprovarTransacao(transactionDto);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private TransactionDto getTransaction(String message) throws IOException {
        TransactionDto transactionDto = objectMapper.readValue(message, TransactionDto.class);
        if (Objects.isNull(transactionDto.getSituacaoEnum())) {
            transactionDto.setSituacaoEnum(SituacaoEnum.NAO_ANALISADA);
        }
        transactionDto.setData(LocalDateTime.now());
        return transactionDto;
    }

}
