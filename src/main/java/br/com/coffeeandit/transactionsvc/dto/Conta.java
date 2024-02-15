package br.com.coffeeandit.transactionsvc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@ToString
public class Conta implements Serializable{


    @Serial
    private static final long serialVersionUID = -2989639634638389323L;

    @NotNull(message = "Informar o código da agência")
    private Long codigoAgencia;

    @NotNull(message = "Informar o código da conta")
    private Long codigoConta;

}
