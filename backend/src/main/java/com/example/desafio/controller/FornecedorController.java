package com.example.desafio.controller;

import java.util.ArrayList;
import java.util.List;

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
public class FornecedorController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @GetMapping("/fornecedores")
    public ResponseEntity<List<Fornecedor>> getAllFornecedores() {
        List<Fornecedor> fornecedores = new ArrayList<>();

        fornecedorRepository.findAll().forEach(fornecedores::add);

        if (fornecedores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(fornecedores, HttpStatus.OK);
    }

    @GetMapping("/empresas/{empresaId}/fornecedores")
    public ResponseEntity<List<Fornecedor>> getAllFornecedoresByEmpresaId(
            @PathVariable(value = "empresaId") Long empresaId) {
        if (!empresaRepository.existsById(empresaId)) {
            throw new ResourceNotFoundException("Not found Empresa with id = " + empresaId);
        }

        List<Fornecedor> fornecedores = fornecedorRepository.findFornecedoresByEmpresasId(empresaId);
        return new ResponseEntity<>(fornecedores, HttpStatus.OK);
    }

    @GetMapping("/fornecedores/{fornecedorId}")
    public ResponseEntity<Fornecedor> getFornecedorById(@PathVariable(value = "fornecedorId") Long fornecedorId) {
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Fornecedor with id = " + fornecedorId));

        return new ResponseEntity<>(fornecedor, HttpStatus.OK);
    }

    @GetMapping("/fornecedores/{empresaId}/empresas")
    public ResponseEntity<List<Empresa>> getAllEmpresasByFornecedorId(
            @PathVariable(value = "fornecedorId") Long fornecedorId) {
        if (!fornecedorRepository.existsById(fornecedorId)) {
            throw new ResourceNotFoundException("Not found Fornecedor with id = " + fornecedorId);
        }

        List<Empresa> empresas = empresaRepository.findEmpresasByFornecedoresId(fornecedorId);
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    // @PostMapping("/empresas/{empresaId}/fornecedores")
    // public ResponseEntity<Fornecedor> addFornecedorToEmpresa(@PathVariable(value
    // = "empresaId") Long empresaId,
    // @RequestBody Fornecedor fornecedorRequest) {
    // Empresa empresa = empresaRepository.findById(empresaId)
    // .orElseThrow(() -> new ResourceNotFoundException("Not found Empresa with id =
    // " + empresaId));

    // Fornecedor fornecedor =
    // fornecedorRepository.findById(fornecedorRequest.getId())
    // .orElseThrow(() -> new ResourceNotFoundException(
    // "Not found Fornecedor with id = " + fornecedorRequest.getId()));

    // empresa.addFornecedor(fornecedor);
    // empresaRepository.save(empresa);

    // return new ResponseEntity<>(fornecedor, HttpStatus.CREATED);
    // }

    @PostMapping("/empresas/{empresaId}/fornecedores")
    public ResponseEntity<Empresa> addFornecedorToEmpresa(@PathVariable(value = "empresaId") Long empresaId,
            @RequestBody Fornecedor fornecedorRequest) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Empresa with id = " + empresaId));

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setDocumento(fornecedorRequest.getDocumento());
        fornecedor.setNome(fornecedorRequest.getNome());
        fornecedor.setCep(fornecedorRequest.getCep());
        fornecedor.setEmail(fornecedorRequest.getEmail());

        // Save Fornecedor entity
        fornecedorRepository.save(fornecedor);

        // Add Fornecedor to Empresa
        empresa.addFornecedor(fornecedor);
        empresaRepository.save(empresa);

        return new ResponseEntity<>(empresa, HttpStatus.CREATED);
    }

    // @PostMapping("/fornecedores"){

    // public ResponseEntity<Fornecedor> createFornecedor(@RequestBody Fornecedor
    // fornecedor) {
    // Fornecedor _fornecedor = new Fornecedor(id:null, fornecedor.getDocumento(),
    // fornecedor.getNome(), fornecedor.getEmail(), fornecedor;getEmpresa()
    //  fornecedor.getDataNas

    // // Save Empresa entity
    // _fornecedor = fornecedorRepository.save(_fornecedor);
    // }

    @PostMapping("/fornecedores")
    public ResponseEntity<Fornecedor> createFornecedor(@RequestBody Fornecedor fornecedor) {
        Fornecedor novoFornecedor = fornecedorRepository.save(fornecedor);
        return new ResponseEntity<>(novoFornecedor, HttpStatus.CREATED);
    }

    @PutMapping("/fornecedores/{fornecedorId}")
    public ResponseEntity<Fornecedor> updateFornecedor(@PathVariable(value = "fornecedorId") Long fornecedorId,
            @RequestBody Fornecedor fornecedorRequest) {
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Fornecedor with id = " + fornecedorId));

        fornecedor.setNome(fornecedorRequest.getNome());
        fornecedor.setEmail(fornecedorRequest.getEmail());
        // Adicione outras propriedades que você deseja atualizar

        Fornecedor updatedFornecedor = fornecedorRepository.save(fornecedor);
        return new ResponseEntity<>(updatedFornecedor, HttpStatus.OK);
    }

    @DeleteMapping("/empresas/{empresaId}/fornecedores/{fornecedorId}")
    public ResponseEntity<HttpStatus> deleteFornecedorFromEmpresa(@PathVariable(value = "empresaId") Long empresaId,
            @PathVariable(value = "fornecedorId") Long fornecedorId) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Empresa with id = " + empresaId));

        empresa.removeFornecedor(fornecedorId);
        empresaRepository.save(empresa);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/fornecedores/{fornecedorId}")
    public ResponseEntity<HttpStatus> deleteFornecedor(@PathVariable(value = "fornecedorId") Long fornecedorId) {
        fornecedorRepository.deleteById(fornecedorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
