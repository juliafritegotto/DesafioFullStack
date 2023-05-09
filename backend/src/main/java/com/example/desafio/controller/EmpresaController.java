package com.example.desafio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.desafio.exception.ResourceNotFoundException;
import com.example.desafio.model.Empresa;
import com.example.desafio.model.Fornecedor;
import com.example.desafio.repository.EmpresaRepository;
import com.example.desafio.repository.FornecedorRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EmpresaController {

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    FornecedorRepository fornecedorRepository;

    @GetMapping("/empresas")
    public ResponseEntity<List<Empresa>> getAllEmpresas(@RequestParam(required = false) String nomeFantasia) {
        List<Empresa> empresas = new ArrayList<Empresa>();

        if (nomeFantasia == null)
            empresaRepository.findAll().forEach(empresas::add);
        else
            empresaRepository.findByNomeFantasiaContaining(nomeFantasia).forEach(empresas::add);

        if (empresas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    @GetMapping("/empresas/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable("id") Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Empresa with id = " + id));

        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    // @PostMapping("/empresas")
    // public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {

    // Empresa _empresa = empresaRepository
    // .save(new Empresa(null, empresa.getCnpj(), empresa.getNomeFantasia(),
    // empresa.getCep()));

    // return new ResponseEntity<>(_empresa, HttpStatus.CREATED);
    // }
    @PostMapping("/empresas")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {
        Empresa _empresa = new Empresa(null, empresa.getCnpj(), empresa.getNomeFantasia(), empresa.getCep());

        // Save Empresa entity
        _empresa = empresaRepository.save(_empresa);

        // Add the Empresa to Fornecedores
        Set<Fornecedor> fornecedores = empresa.getFornecedores();
        if (fornecedores != null) {
            for (Fornecedor fornecedor : fornecedores) {
                fornecedor.getEmpresas().add(_empresa); // Add Empresa to Fornecedor's Set<Empresa>
                fornecedorRepository.save(fornecedor);
            }
        }

        return new ResponseEntity<>(_empresa, HttpStatus.CREATED);
    }

    @PutMapping("/empresas/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable("id") Long id, @RequestBody Empresa empresa) {
        Empresa _empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Empresa with id = " + id));

        _empresa.setCnpj(empresa.getCnpj());
        _empresa.setNomeFantasia(empresa.getNomeFantasia());
        _empresa.setCep(empresa.getCep());

        return new ResponseEntity<>(empresaRepository.save(_empresa), HttpStatus.OK);
    }

    @DeleteMapping("/empresas/{id}")
    public ResponseEntity<HttpStatus> deleteEmpresa(@PathVariable("id") Long id) {
        empresaRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/empresas")
    public ResponseEntity<HttpStatus> deleteAllEmpresas() {
        empresaRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
