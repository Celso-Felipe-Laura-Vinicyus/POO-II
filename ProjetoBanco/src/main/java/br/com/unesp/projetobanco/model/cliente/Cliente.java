package br.com.unesp.projetobanco.model.cliente;

import br.com.unesp.projetobanco.model.endereco.Endereco;

public abstract class Cliente {
    protected String telefone;
    protected String email;
    protected Endereco endereco;
    
    //Construtor
    public Cliente(String telefone, String email, Endereco endereco) {
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }


    //Métodos
    public abstract String getNome(); //Necessário quando quiser exibir o nome/razão social em App
    public abstract String exibirDados();
    
    //public abstract Emissor criarEmissor();

    //Setters and Getters
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
}
