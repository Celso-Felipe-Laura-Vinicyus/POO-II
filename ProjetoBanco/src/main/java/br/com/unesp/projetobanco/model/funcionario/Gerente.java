package br.com.unesp.projetobanco.model.funcionario;

import br.com.unesp.projetobanco.model.endereco.Endereco;

public class Gerente extends Funcionario {
    private double bonus;
    private String agencia;


    //Construtor
    public Gerente(String nome, String cpf, Endereco endereco, double salario, double bonus, String agencia) {
        super(nome, cpf, endereco, salario);
        this.bonus = bonus;
        this.agencia = agencia;
        this.salario += this.salario * 0.2; //salario final 
    }


    //Métodos
    @Override
    public String exibirDados() {
        String dados = "Cargo: Gerente\n";
        dados += super.exibirDados(); //Dados básicos
        dados += ("\nBônus: R$" + String.format("%.2f",this.bonus));
        dados += ("\nAgência responsável: " + this.agencia);
        return dados;
    }


    //Getters e Setters
    public double getBonus() {
        return bonus;
    }
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    public String getAgencia() {
        return agencia;
    }
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }
    
}
