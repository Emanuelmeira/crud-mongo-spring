package meira.emanuel.service;

import meira.emanuel.model.Pessoa;
import meira.emanuel.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(String idPessoa, Pessoa.Endereco endereco){

        Query query = new Query(Criteria.where("id").is(idPessoa));
        Update update = new Update();
        update.push("endereco", endereco);

        mongoTemplate.updateFirst(query, update, Pessoa.class);
    }


    public List<Pessoa.Endereco> listAllByIdPessoa(String idPessoa){

        Query find = new Query( Criteria.where("id").is(idPessoa) );  //To find matching documents
        find.fields().include("endereco");


        List<Pessoa.Endereco> enderecos = mongoTemplate.find(find, Pessoa.Endereco.class);
        return  pessoaRepository.findEnderecoById(idPessoa);
    }

}
