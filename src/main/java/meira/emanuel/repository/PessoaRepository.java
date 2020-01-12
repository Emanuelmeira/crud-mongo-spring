package meira.emanuel.repository;

import meira.emanuel.model.Pessoa;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PessoaRepository extends MongoRepository<Pessoa, String> {

    List<Pessoa.Endereco> findEnderecoById(String id);
}
