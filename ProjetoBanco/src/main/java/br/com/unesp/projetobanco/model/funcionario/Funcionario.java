package br.com.unesp.projetobanco.model.funcionario;
import br.com.unesp.projetobanco.model.endereco.Endereco;

public class Funcionario {
    
    protected String nome;
    protected String cpf;
    protected Endereco endereco;
    protected double salario;

    //Constructor
    public Funcionario(String nome, String cpf, Endereco endereco, double salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
    }

    public String exibirDados() {
        String dados = "Nome: " + this.nome;
        dados += "\nCPF: " + this.cpf;
        dados += endereco.exibir();
        dados += "\nSalario: R$" + String.format("%.2f", this.salario);
        return dados;
    }

    //Para preencher combobox
    public String descricao() {
        return this.nome + " - " + this.cpf + " - R$" + String.format("%.2f", this.salario);
    }
  
    //Getters e Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    
}
