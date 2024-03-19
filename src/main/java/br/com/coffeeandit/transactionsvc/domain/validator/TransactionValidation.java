package br.com.coffeeandit.transactionsvc.domain.validator;

import br.com.coffeeandit.transactionsvc.dto.RequestTransactionDto;
import br.com.coffeeandit.transactionsvc.domain.exception.DomainBusinessException;

public interface TransactionValidation {

    public void validate(final RequestTransactionDto requestTransactionDto) throws DomainBusinessException;
}
