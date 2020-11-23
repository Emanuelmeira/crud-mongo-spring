package meira.emanuel.config;

import meira.emanuel.model.Person;
import meira.emanuel.model.enums.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {

        Person p1 = Person.builder()
        .name("Emanuel Meira")
        .age(67)
        .tags(Arrays.asList(Tag.ITSA4,Tag.BBSE3))
        .address(
                Arrays.asList(Person.Address.builder()._id(new ObjectId()).city("Salvador").state("BA").build(),
                        Person.Address.builder()._id(new ObjectId()).city("São Paulo").state("SP").build(),
                        Person.Address.builder()._id(new ObjectId()).city("Florianopolis").state("SC").build(),
                        Person.Address.builder()._id(new ObjectId()).city("Campina grande").state("PB").build()
                )).build();


        Person p2 = Person.builder()
        .name("Juca chaves")
        .age(15)
        .tags(Arrays.asList(Tag.MGLU3,Tag.TAEE11, Tag.SANB3))
        .address(
                Arrays.asList(Person.Address.builder()._id(new ObjectId()).city("Salvador").state("BA").build(),
                        Person.Address.builder()._id(new ObjectId()).city("São gonsalo").state("BA").build(),
                        Person.Address.builder()._id(new ObjectId()).city("Florianopolis").state("SC").build(),
                        Person.Address.builder()._id(new ObjectId()).city("Campina grande").state("PB").build()
                )).build();

        Person p3 = Person.builder()
        .name("Ana julia")
        .age(25)
        .tags(Arrays.asList(Tag.MGLU3, Tag.SANB3))
        .address(
                Arrays.asList(Person.Address.builder()._id(new ObjectId()).city("Salvador").state("BA").build(),
                        Person.Address.builder()._id(new ObjectId()).city("São Gonsalo").state("BA").build(),
                        Person.Address.builder()._id(new ObjectId()).city("Florianopolis").state("SC").build(),
                        Person.Address.builder()._id(new ObjectId()).city("Campina grande").state("PB").build()
                )).build();

        
        mongoTemplate.insertAll(Arrays.asList(p1, p2, p3));
    }
}
