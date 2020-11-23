package meira.emanuel.controller;

import meira.emanuel.model.Person;
import meira.emanuel.model.dto.PersonEditDTO;
import meira.emanuel.model.dto.PersonFiltersDTO;
import meira.emanuel.model.dto.TagDTO;

import meira.emanuel.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Person person){
       Person p = personService.save(person);
       return new ResponseEntity<Person>(p, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        Person p = personService.findById(id);
        return new ResponseEntity<Person>(p, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PersonEditDTO personEditDTO, @PathVariable String id){
        personService.update(id, personEditDTO);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> saveTag(@RequestBody TagDTO tagDTO, @PathVariable String id){
        personService.addTag(id, tagDTO );

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/") //http://localhost:8080/person/?name=pedro&page=0&size=10&sort=1
    public ResponseEntity<?> getByFilters(@Valid PersonFiltersDTO personFilters, Pageable pageable){

        Page<Person>  pagePerson = personService.findByFilters(personFilters, pageable);

        HttpStatus status = pagePerson.getContent().size() == 0l  ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<Page<Person>>(pagePerson, status);
    }


    //TODO deletar pessoa


}
