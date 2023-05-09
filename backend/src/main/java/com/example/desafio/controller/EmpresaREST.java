package com.example.desafio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafio.database.RepositorioEmpresa;
import com.example.desafio.database.RepositorioFornecedor;
import com.example.desafio.model.Empresa;
import com.example.desafio.model.Fornecedor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/empresa")
public class EmpresaREST {
    @Autowired
    private RepositorioEmpresa repositorioEmpresa;

    @Autowired
    private RepositorioFornecedor repositorioFornecedor;

    @GetMapping
    public List<Empresa> listar() {
        return repositorioEmpresa.findAll();
    }

    // @PostMapping
    // public void salvar(@RequestBody Empresa empresa) {
    // // Salva a empresa no banco de dados
    // Empresa empresaSalva = repositorioEmpresa.save(empresa);

    // // Associa os fornecedores à empresa
    // for (Fornecedor fornecedor : empresa.getFornecedores()) {
    // if (fornecedor.getId() == null) {
    // // Fornecedor doesn't exist, save it
    // fornecedor = repositorioFornecedor.save(fornecedor); // Save fornecedor and
    // update the reference
    // fornecedor.setEmpresas(List.of(empresaSalva)); // Set the association with
    // empresa
    // } else {
    // // Fornecedor already exists, update its relationship with the empresa
    // Fornecedor fornecedorExistente =
    // repositorioFornecedor.getReferenceById(fornecedor.getId());
    // fornecedorExistente.setEmpresas(List.of(empresaSalva)); // Set the
    // association with empresa
    // }
    // }
    // }

    // @PostMapping
    // public void salvar(@RequestBody Empresa empresa) {
    // // Salva a empresa no banco de dados
    // Empresa empresaSalva = repositorioEmpresa.save(empresa);

    // // Associa os fornecedores à empresa
    // for (Fornecedor fornecedor : empresa.getFornecedores()) {
    // // Check if the fornecedor already exists
    // Optional<Fornecedor> fornecedorExistenteOptional =
    // repositorioFornecedor.findByNome(fornecedor.getNome());
    // if (fornecedorExistenteOptional.isPresent()) {
    // fornecedor = fornecedorExistenteOptional.get(); // Use the existing
    // fornecedor instead
    // } else {
    // fornecedor = repositorioFornecedor.save(fornecedor); // Save the new
    // fornecedor
    // }
    // fornecedor.getEmpresas().add(empresaSalva); // Add the association with
    // empresa
    // }

    // }

    // @PostMapping
    // public void salvar(@RequestBody Empresa empresa) {
    // // Salva a empresa no banco de dados
    // Empresa empresaSalva = repositorioEmpresa.save(empresa);

    // // Associa os fornecedores à empresa
    // for (Fornecedor fornecedor : empresa.getFornecedores()) {
    // if (fornecedor.getId() != null) {
    // Optional<Fornecedor> fornecedorExistenteOptional =
    // repositorioFornecedor.findById(fornecedor.getId());
    // if (fornecedorExistenteOptional.isPresent()) {
    // fornecedor = fornecedorExistenteOptional.get(); // Use the existing
    // fornecedor instead
    // } else {
    // fornecedor = repositorioFornecedor.save(fornecedor); // Save the new
    // fornecedor
    // }
    // } else {
    // fornecedor = repositorioFornecedor.save(fornecedor); // Save the new
    // fornecedor
    // }
    // fornecedor.getEmpresas().add(empresaSalva); // Add the association with
    // empresa
    // }
    // }

    @PostMapping
    public void salvar(@RequestBody Empresa empresa) {

        // Associa os fornecedores à empresa
        for (Fornecedor fornecedor : empresa.getFornecedores()) {
            if (fornecedor.getId() != null) {
                Optional<Fornecedor> fornecedorExistenteOptional = repositorioFornecedor.findById(fornecedor.getId());
                if (fornecedorExistenteOptional.isPresent()) {
                    fornecedor = fornecedorExistenteOptional.get(); // Use the existing fornecedor instead
                } else {
                    fornecedor = repositorioFornecedor.save(fornecedor); // Save the new fornecedor
                }
            } else {
                fornecedor = repositorioFornecedor.save(fornecedor); // Save the new fornecedor
            }
            fornecedor.getEmpresas().add(empresa); // Add the association with empresa
        }

        // Salva a empresa no banco de dados
        repositorioEmpresa.save(empresa);
    }

    @PutMapping
    public void alterar(@RequestBody Empresa empresa) {
        if (empresa.getId() > 0)
            repositorioEmpresa.save(empresa);
    }

    // @DeleteMapping
    // public void excluir(@RequestBody Empresa empresa) {
    // repositorioEmpresa.delete(empresa);
    // }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        repositorioEmpresa.deleteById(id);
    }
}
