package com.example.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.desafio.model.Fornecedor;

public interface RepositorioFornecedor extends JpaRepository<Fornecedor, Long> {

    Optional<Fornecedor> findByNome(String nome);

}
