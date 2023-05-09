package com.example.desafio.model;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Objects;

import jakarta.persistence.CascadeType;
// import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "fornecedores")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String documento;

    @Column(unique = true)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cep;

    private String rg;

    private LocalDate dataNascimento;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, mappedBy = "fornecedores")
    @JsonIgnore
    private Set<Empresa> empresas = new HashSet<>();

    public Fornecedor() {

    }

    public Fornecedor(Long id, String documento, String nome, String email, String rg, LocalDate dataNascimento,
            String cep) {
        this.id = id;
        this.documento = documento;
        this.nome = nome;
        this.email = email;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.cep = cep;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Fornecedor fornecedor = (Fornecedor) o;
        return Objects.equals(id, fornecedor.id) &&
                Objects.equals(documento, fornecedor.documento) &&
                Objects.equals(nome, fornecedor.nome) &&
                Objects.equals(email, fornecedor.email) &&
                Objects.equals(cep, fornecedor.cep) &&
                Objects.equals(rg, fornecedor.rg) &&
                Objects.equals(dataNascimento, fornecedor.dataNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documento, nome, email, cep, rg, dataNascimento);
    }

    public boolean hasCpf() {
        if (documento == null || documento.isEmpty()) {
            return false;
        }
        String cpf = documento.replaceAll("[^0-9]", ""); // remove caracteres não numéricos
        if (cpf.length() != 11) {
            return false;
        }
        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Set<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(Set<Empresa> empresas) {
        this.empresas = empresas;
    }
}
