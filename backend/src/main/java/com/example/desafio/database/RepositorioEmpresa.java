package com.example.desafio.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.desafio.entidade.Empresa;

public interface RepositorioEmpresa extends JpaRepository<Empresa, Long> {

}
