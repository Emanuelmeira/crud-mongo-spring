package features;

import meira.emanuel.CrudMongoApplication;
import meira.emanuel.model.Person;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = CrudMongoApplication.class)
@RunWith(SpringRunner.class)
public class MongoFeatureTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Before
    public void beforeAll(){
        System.out.println("ok1");
    }

    @Test
    public void teste(){
        System.out.println("ok2");
        mongoTemplate.findById("23434343" , Person.class);
    }



}
