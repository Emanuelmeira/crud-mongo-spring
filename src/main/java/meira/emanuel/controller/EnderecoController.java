package meira.emanuel.controller;

import meira.emanuel.model.Pessoa;
import meira.emanuel.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{idPessoa}/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<?> save(@PathVariable String idPessoa, @RequestBody Pessoa.Endereco endereco){

        enderecoService.save(idPessoa, endereco);

        return new ResponseEntity<Pessoa>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa.Endereco>> listAll(@PathVariable String idPessoa){

        List<Pessoa.Endereco> lsEndereco = enderecoService.listAllByIdPessoa(idPessoa);

        return new ResponseEntity<List<Pessoa.Endereco>>(lsEndereco, HttpStatus.OK);
    }
    //como da um count dentro do array de uma coleçao
}
