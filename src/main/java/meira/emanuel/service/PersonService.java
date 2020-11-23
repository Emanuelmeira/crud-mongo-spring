package meira.emanuel.service;

import meira.emanuel.model.Person;
import meira.emanuel.model.dto.PersonEditDTO;
import meira.emanuel.model.dto.PersonFiltersDTO;
import meira.emanuel.model.dto.TagDTO;
import meira.emanuel.repository.PersonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Page<Person> findByFilters(PersonFiltersDTO personFilters, Pageable pageable) {

        //TODO buscar em duas collections diferentes

        Query query = new Query();
        query.with(pageable);
        query.with(new Sort(Sort.Direction.ASC,"name")); //TODO pesquisar melhor

        query.addCriteria(Criteria.where("name").is(personFilters.getName()));

        Optional.ofNullable(personFilters.getAge()).ifPresent(age -> {
            query.addCriteria(Criteria.where("age").is(personFilters.getAge()));
        });

        List<Person> people = mongoTemplate.find(query, Person.class);
        Page<Person> pagePerson = new PageImpl<>(people, pageable, people.size());
        return pagePerson;

        //https://www.baeldung.com/queries-in-spring-data-mongodb
        //return personRepository.findAll(pageable);
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
