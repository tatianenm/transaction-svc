package br.com.coffeeandit.transactionsvc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
@ToString(of = {"uuid", "situacaoEnum"})
public class TransactionDto {

    @Id
    private UUID uuid;
    @NotNull(message = "Informar o valor da transação")
    private BigDecimal valor;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime data;
    @Valid
    private Conta conta;
    @Valid
    private BeneficiarioDto beneficiarioDto;
    @NotNull(message = "Informar o tipo da transação")
    private TipoTransacao tipoTransacao;
    private SituacaoEnum situacaoEnum;

    public void suspeitaFraude(){
        situacaoEnum = SituacaoEnum.EM_SUSPEITA_FRAUDE;
    }

    public void analiseHumana(){
        situacaoEnum = SituacaoEnum.EM_ANALISE_HUMANA;
    }

    public void analisada(){
        situacaoEnum = SituacaoEnum.ANALISADA;
    }
    @JsonIgnore
    public boolean isAnalisada(){
        return getSituacaoEnum().equals(SituacaoEnum.ANALISADA);
    }
    public void aprovar(){
        situacaoEnum = SituacaoEnum.APROVADA;
    }
}
