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

import com.example.desafio.database.RepositorioFornecedor;
import com.example.desafio.model.Empresa;
import com.example.desafio.model.Fornecedor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/fornecedor")
public class FornecedorREST {
    @Autowired
    private RepositorioFornecedor repositorioFornecedor;

    @GetMapping
    public List<Fornecedor> listar() {
        return repositorioFornecedor.findAll();
    }

    @PostMapping
    public void salvar(@RequestBody Fornecedor fornecedor) {
        repositorioFornecedor.save(fornecedor);
    }

    // @PostMapping
    // public void salvar(@RequestBody Fornecedor fornecedor) {

    // // Salva o fornecedor no banco de dados
    // fornecedor = repositorioFornecedor.save(fornecedor);

    // // Associa as empresas ao fornecedor
    // for (Empresa empresa : fornecedor.getEmpresas()) {
    // if (empresa.getId() != null) {
    // Optional<Empresa> empresaExistenteOptional =
    // repositorioEmpresa.findById(empresa.getId());
    // if (empresaExistenteOptional.isPresent()) {
    // empresa = empresaExistenteOptional.get(); // Use the existing empresa instead
    // } else {
    // empresa = repositorioEmpresa.save(empresa); // Save the new empresa
    // }
    // } else {
    // empresa = repositorioEmpresa.save(empresa); // Save the new empresa
    // }
    // empresa.getFornecedores().add(fornecedor); // Add the association with
    // fornecedor
    // }

    // // Salva o fornecedor atualizado no banco de dados
    // repositorioFornecedor.save(fornecedor);
    // }

    @PutMapping
    public void alterar(@RequestBody Fornecedor fornecedor) {
        if (fornecedor.getId() > 0)
            repositorioFornecedor.save(fornecedor);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        repositorioFornecedor.deleteById(id);
    }
}