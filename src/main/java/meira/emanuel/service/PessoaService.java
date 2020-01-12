package meira.emanuel.service;

import meira.emanuel.model.Pessoa;
import meira.emanuel.repository.PessoaRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public Pessoa save(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public long count() {
        return pessoaRepository.count();
    }

    public Pessoa findById(String id) {

        return  mongoTemplate.findById(id , Pessoa.class);
        //return pessoaRepository.findOne(id, Pessoa.class);
    }

    public void delete(String id) {
        pessoaRepository.deleteById(id);
    }
}
