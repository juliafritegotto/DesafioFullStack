package com.example.desafio.service;

import com.example.desafio.model.Empresa;
import com.example.desafio.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> obterEmpresaPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public void salvarEmpresa(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    public void excluirEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }
}
