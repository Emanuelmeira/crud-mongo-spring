package meira.emanuel.repository;

import meira.emanuel.model.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, ObjectId> {

    List<Person.Address> findAddressBy_id(ObjectId id);
}
