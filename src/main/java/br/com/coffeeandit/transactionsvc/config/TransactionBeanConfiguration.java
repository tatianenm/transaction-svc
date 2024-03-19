package br.com.coffeeandit.transactionsvc.config;


import br.com.coffeeandit.transactionsvc.domain.validator.EmptyTransactionValidationBean;
import br.com.coffeeandit.transactionsvc.domain.validator.TransactionValidation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionBeanConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @Conditional(TransactionEnableNeverThanJavaSeventeen.class)
    public TransactionValidation emptyTransactionNeverThanJavaSeventeenValidation(){
        return new EmptyTransactionValidationBean();
    }

    @ConditionalOnJava(range = ConditionalOnJava.Range.OLDER_THAN, value = JavaVersion.SEVENTEEN)
    @Bean
    @ConditionalOnMissingBean
    @Conditional(TransactionEnableNeverThanJavaSeventeen.class)
    public TransactionValidation emptyTransactionJavaOlderThanSeventeenValidation(){
        return new EmptyTransactionValidationBean();
    }
}
