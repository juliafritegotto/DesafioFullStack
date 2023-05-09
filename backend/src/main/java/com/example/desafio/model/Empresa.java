package com.example.desafio.model;

import java.util.Set;
import java.util.HashSet;

// import com.example.desafio.model.Fornecedor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String nomeFantasia;

    @Column(nullable = false)
    private String cep;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "empresa_fornecedores", joinColumns = { @JoinColumn(name = "empresa_id") }, inverseJoinColumns = {
            @JoinColumn(name = "fornecedor_id") })

    private Set<Fornecedor> fornecedores = new HashSet<>();

    public Empresa() {
    }

    public Empresa(Long id, String cnpj, String nomeFantasia, String cep) {
        this.id = id;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.cep = cep;
    }

    // @Override
    // public boolean equals(Object o) {
    // if (this == o)
    // return true;
    // if (o == null || getClass() != o.getClass())
    // return false;
    // Empresa empresa = (Empresa) o;
    // return Objects.equals(id, empresa.id) &&
    // Objects.equals(cnpj, empresa.cnpj) &&
    // Objects.equals(nomeFantasia, empresa.nomeFantasia) &&
    // Objects.equals(cep, empresa.cep);
    // }

    // @Override
    // public int hashCode() {
    // return Objects.hash(id, cnpj, nomeFantasia, cep);
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Set<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(Set<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public void addFornecedor(Fornecedor fornecedor) {
        this.fornecedores.add(fornecedor);
        fornecedor.getEmpresas().add(this);
    }

    public void removeFornecedor(long fornecedor_id) {
        Fornecedor fornecedor = this.fornecedores.stream().filter(t -> t.getId() == fornecedor_id).findFirst()
                .orElse(null);
        if (fornecedor != null) {
            this.fornecedores.remove(fornecedor);
            fornecedor.getEmpresas().remove(this);
        }
    }

}
