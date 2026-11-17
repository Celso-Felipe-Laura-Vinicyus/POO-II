package br.com.unesp.projetobanco.model.funcionario;

import br.com.unesp.projetobanco.model.endereco.Endereco;

public class Diretor extends Funcionario {
    private String area;
    private double parteNosLucros;


    //Construtor
    public Diretor(String nome, String cpf, Endereco endereco, double salario, String area) {
        super(nome, cpf, endereco, salario);
        this.area = area;
        this.parteNosLucros = 0.35;
        this.salario += this.salario * this.parteNosLucros;
    }

    
    //Métodos
    @Override
    public String exibirDados() {
        String dados = "Cargo: Diretor\n";
        dados += super.exibirDados(); //Dados básicos
        dados += "\nParticipação nos lucros: " + String.format("%.2f", this.parteNosLucros*100) + "%";
        dados += "\nÁrea responsável: " + this.area;
        return dados;
    }


    //Getters e Setters
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public double getParteNosLucros() {
        return parteNosLucros;
    }
    public void setParteNosLucros(double parteNosLucros) {
        this.parteNosLucros = parteNosLucros;
    }
      
}
