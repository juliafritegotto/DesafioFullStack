package com.example.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.desafio.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    List<Empresa> findByNomeFantasiaContaining(String nomeFantasia);

    List<Empresa> findEmpresasByFornecedoresId(Long fornecedorId);

}
