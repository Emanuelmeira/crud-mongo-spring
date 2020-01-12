package meira.emanuel.controller;

import meira.emanuel.model.Pessoa;
import meira.emanuel.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Pessoa pessoa){
       Pessoa p = pessoaService.save(pessoa);
       return new ResponseEntity<Pessoa>(p, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        Pessoa p = pessoaService.findById(id);
        return new ResponseEntity<Pessoa>(p, HttpStatus.OK);
    }





}
