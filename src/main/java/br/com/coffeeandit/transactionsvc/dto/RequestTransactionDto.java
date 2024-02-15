package br.com.coffeeandit.transactionsvc.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class RequestTransactionDto extends TransactionDto{

    private SituacaoEnum situacaoEnum;

    private LocalDateTime data;
}
