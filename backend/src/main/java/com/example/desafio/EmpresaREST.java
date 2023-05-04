package com.example.desafio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafio.database.RepositorioEmpresa;
import com.example.desafio.entidade.Empresa;

@RestController
@RequestMapping("/empresa")
public class EmpresaREST {
    @Autowired
    private RepositorioEmpresa repositorioEmpresa;

    @GetMapping
    public List<Empresa> listar() {
        return repositorioEmpresa.findAll();
    }

    @PostMapping
    public void salvar(@RequestBody Empresa empresa) {
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
