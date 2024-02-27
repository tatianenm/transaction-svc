package br.com.coffeeandit.transactionsvc.domain.validator.impl;

import br.com.coffeeandit.transactionsvc.domain.validator.TransactionValidator;
import br.com.coffeeandit.transactionsvc.dto.RequestTransactionDto;
import br.com.coffeeandit.transactionsvc.domain.exception.DomainBusinessException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
@ConditionalOnProperty(
        value = "transaction.validation.banco",
        havingValue = "true",
        matchIfMissing = false

)
public class BancoTransactionValidator implements TransactionValidator {

    public static final int CODIGO_BANCO = 785;

    @Override
    public void validate(RequestTransactionDto requestTransactionDto) throws DomainBusinessException {
          if(Objects.isNull(requestTransactionDto.getBeneficiarioDto())){
              throw new DomainBusinessException("Inválido banco do beneficiário");
          } else if (Objects.isNull(requestTransactionDto.getBeneficiarioDto().getCodigoBanco()) ||
                      requestTransactionDto.getBeneficiarioDto().getCodigoBanco().compareTo((long) CODIGO_BANCO) != 0
          ) {
              throw new DomainBusinessException("Inválido banco do beneficiário");
          }

    }
}
