package br.com.coffeeandit.transactionsvc.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
@Getter
@Setter
public class BeneficiarioDto implements Serializable {


    @Serial
    private static final long serialVersionUID = -4101044650441935004L;
    @NotNull(message = "Informar o cpf")
    private Long cpf;

    @NotNull(message = "Informar o código do banco de destino")
    private Long codigoBanco;

    @NotNull(message = "Informar a agência de destino")
    private String agencia;

    @NotNull(message = "Informar conta de destino")
    private String conta;

    @NotNull(message = "Informar o nome do favorecido")
    private  String nomeFavorecido;
}
