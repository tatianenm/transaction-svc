package br.com.coffeeandit.transactionsvc.domain.validator;

import br.com.coffeeandit.transactionsvc.dto.RequestTransactionDto;
import br.com.coffeeandit.transactionsvc.domain.exception.DomainBusinessException;
import br.com.coffeeandit.transactionsvc.domain.validator.impl.BancoTransactionValidator;
import br.com.coffeeandit.transactionsvc.domain.validator.impl.HorarioTransactionValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@ConditionalOnBean(value = {BancoTransactionValidator.class, HorarioTransactionValidator.class})
@ConditionalOnExpression("${transaction.validation.enabled:true}")
@Slf4j
public class TransactionValidationBean implements TransactionValidation {

    private List<TransactionValidator> transactionValidatorList = new ArrayList<>();

    @PostConstruct
    public void addBeans() {
        transactionValidatorList.add(new BancoTransactionValidator());
        transactionValidatorList.add(new HorarioTransactionValidator());
    }

    @Override
    public void validate(RequestTransactionDto requestTransactionDto) throws DomainBusinessException {
        transactionValidatorList.forEach(transactionValidator -> {
            try {
                transactionValidator.validate(requestTransactionDto);
            } catch (DomainBusinessException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        });

    }
}
