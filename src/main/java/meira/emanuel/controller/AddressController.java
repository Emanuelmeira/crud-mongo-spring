package meira.emanuel.controller;

import meira.emanuel.model.Person;
import meira.emanuel.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{idPerson}/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<?> save(@PathVariable String idPerson, @RequestBody Person.Address address){

        addressService.save(idPerson, address);

        return new ResponseEntity<Person>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Person.Address>> listAll(@PathVariable String idPerson){

        List<Person.Address> addresses = addressService.listAllByIdPessoa(idPerson);

        return new ResponseEntity<List<Person.Address>>(addresses, HttpStatus.OK);
    }
    //como da um count dentro do array de uma coleçao
}
