package br.com.coffeeandit.transactionsvc.repository;

import br.com.coffeeandit.transactionsvc.dto.Conta;
import br.com.coffeeandit.transactionsvc.dto.TransactionDto;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionDto, UUID> {
   // @Query("conta.codigoAgencia = 0?") query mongodb
    List<TransactionDto> findByConta(final Conta conta);
}
