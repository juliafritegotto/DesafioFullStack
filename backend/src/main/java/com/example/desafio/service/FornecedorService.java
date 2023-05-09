package com.example.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.desafio.model.Fornecedor;
import com.example.desafio.repository.FornecedorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public Fornecedor criarFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor atualizarFornecedor(Long id, Fornecedor fornecedor) {
        Optional<Fornecedor> fornecedorExistente = fornecedorRepository.findById(id);
        if (fornecedorExistente.isPresent()) {
            Fornecedor fornecedorAtualizado = fornecedorExistente.get();
            fornecedorAtualizado.setDocumento(fornecedor.getDocumento());
            fornecedorAtualizado.setNome(fornecedor.getNome());
            fornecedorAtualizado.setEmail(fornecedor.getEmail());
            fornecedorAtualizado.setRg(fornecedor.getRg());
            fornecedorAtualizado.setDataNascimento(fornecedor.getDataNascimento());
            fornecedorAtualizado.setCep(fornecedor.getCep());
            return fornecedorRepository.save(fornecedorAtualizado);
        } else {
            return null;
        }
    }

    public boolean excluirFornecedor(Long id) {
        Optional<Fornecedor> fornecedorExistente = fornecedorRepository.findById(id);
        if (fornecedorExistente.isPresent()) {
            fornecedorRepository.delete(fornecedorExistente.get());
            return true;
        } else {
            return false;
        }
    }

    public Fornecedor obterFornecedorPorId(Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        return fornecedor.orElse(null);
    }

    public List<Fornecedor> obterTodosFornecedores() {
        return fornecedorRepository.findAll();
    }
}
