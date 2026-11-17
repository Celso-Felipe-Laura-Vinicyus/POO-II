package br.com.unesp.projetobanco.model.cliente;

import br.com.unesp.projetobanco.model.endereco.Endereco;

public class ClientePF extends Cliente{
    private String nome;
    private String cpf;
    
    //Contrutor
    public ClientePF(String telefone, String email, Endereco endereco, String nome, String cpf) {
        super(telefone, email, endereco);
        this.nome = nome;
        this.cpf = cpf;
    }


    //Métodos
    @Override
    public String getNome() {
        return nome;
    }
    @Override
    public String exibirDados(){
        String dados = "Nome: " + nome + "\n";
        dados = dados + "Tipo de cliente: Pessoa fisica" + "\n";
        dados = dados + "CPF: " + cpf + "\n";
        dados = dados + "Telefone: " + telefone + "\n";
        dados = dados + "Email: " + email;
        dados = dados + endereco.exibir() + "\n";
        return dados;
    }


    //Getters e Setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
