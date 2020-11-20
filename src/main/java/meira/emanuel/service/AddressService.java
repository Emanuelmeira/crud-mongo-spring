package meira.emanuel.service;

import meira.emanuel.model.Person;
import meira.emanuel.repository.PersonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(String idPerson, Person.Address address){

        Query query = new Query(Criteria.where("_id").is(idPerson));
        Update update = new Update();
        update.push("address", address);

        mongoTemplate.updateFirst(query, update, Person.class);
    }

    public List<Person.Address> listAllByIdPessoa(String idPerson){

        Query find = new Query( Criteria.where("_id").is(idPerson) );  //To find matching documents
        find.fields().include("address");


        List<Person.Address> addresses = mongoTemplate.find(find, Person.Address.class);
        return  personRepository.findAddressBy_id(new ObjectId(idPerson) );
    }

}

//TODO
// colocar mais um endereço dentro do array endereco sem buscar pessoa
//atulizar endereco dentro do array sem buscar entidade pessoa
// deletar endereco so usando o indice do array

//criar um index e usar com muitos dados