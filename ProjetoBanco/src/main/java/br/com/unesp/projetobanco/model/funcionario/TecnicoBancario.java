package br.com.unesp.projetobanco.model.funcionario;

import br.com.unesp.projetobanco.model.endereco.Endereco;

public class TecnicoBancario extends Funcionario {
    private String setor;
    private double horasExtras;


    //Contrutor
    public TecnicoBancario(String nome, String cpf, Endereco endereco, double salario, String setor, double horasExtras) {
        super(nome, cpf, endereco, salario);
        this.setor = setor;
        this.horasExtras = horasExtras;
        this.salario += this.horasExtras * 30; //salario final
    }
    

    //Métodos
    @Override
    public String exibirDados() {
        String dados = ("Cargo: Tecnico Bancario\n");
        dados += super.exibirDados(); //Dados básicos
        dados += ("\nQuantidade de horas extras: " + String.format("%.2f", this.horasExtras));
        dados += ("\nSetor de atendimento: " + this.setor);
        return dados;
    }


    //Getters e Setters
    public String getSetor() {
        return setor;
    }
    public void setSetor(String setor) {
        this.setor = setor;
    }
    public double getHorasExtras() {
        return horasExtras;
    }
    public void setHorasExtras(double horasExtras) {
        this.horasExtras = horasExtras;
    }
}
