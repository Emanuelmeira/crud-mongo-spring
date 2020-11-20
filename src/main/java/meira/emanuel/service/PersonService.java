package meira.emanuel.service;

import meira.emanuel.model.Person;
import meira.emanuel.model.dto.PersonEditDTO;
import meira.emanuel.model.dto.TagDTO;
import meira.emanuel.model.enums.Tag;
import meira.emanuel.repository.PersonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Person save(Person person){
        return personRepository.save(person);
    }

    public Person findById(String id) {

        return  mongoTemplate.findById(id , Person.class);
        //return pessoaRepository.findOne(id, Pessoa.class);
    }

    public void update(String idPerson, PersonEditDTO personEditDTO) {

        Query query = new Query(Criteria.where("_id").is(idPerson));

        Update update = this.prepareUpdate(personEditDTO);

        mongoTemplate.updateFirst(query, update, Person.class);
    }

    public void addTag(String idPerson, TagDTO tagDTO) {

        Query query = new Query(Criteria.where("_id").is(idPerson));
        Update update = new Update();

        if(Objects.nonNull(tagDTO.getTag())) {
            update.addToSet("tags", tagDTO.getTag());
        }

        mongoTemplate.updateFirst(query, update, Person.class);
    }

    public void removeProperties(String idPerson, String properties) {

        Query query = new Query(Criteria.where("_id").is(idPerson));
        Update update = new Update();

        if(!StringUtils.isEmpty(properties)) {
            update.unset(properties);
        }

        mongoTemplate.updateFirst(query, update, Person.class);
    }

    public void delete(String id) {
        personRepository.deleteById(new ObjectId(id));
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public long count() {
        return personRepository.count();
    }

    private Update prepareUpdate(PersonEditDTO personEditDTO){

        Update update = new Update();
        if(Objects.nonNull(personEditDTO.getAge() )){
            update.set("age", personEditDTO.getAge() );
        }

        if(Objects.nonNull(personEditDTO.getName())){
            update.set("name", personEditDTO.getName());
        }

        return update;
    }


}
