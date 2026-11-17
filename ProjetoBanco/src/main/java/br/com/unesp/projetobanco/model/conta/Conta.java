package br.com.unesp.projetobanco.model.conta;

import br.com.unesp.projetobanco.model.cliente.Cliente;

public abstract class Conta {
    protected int numero;
    protected Cliente titular;
    protected double saldo;

    //Construtor////////////////////////////////////////////
    public Conta(int numero, Cliente titular) {
        this.numero = numero;
        this.titular = titular;
    }
    ////////////////////////////////////////////////////////


    //Setters and Getters///////////////////////////////////
    public int getNumero() {
        return numero;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }
    ////////////////////////////////////////////////////////


    public boolean saca(double valor){
        if (this.saldo < valor){
            return false;
        }else{
            this.saldo -= valor;
            return true;
        }
    }

    public void deposita(double quantidade){
        this.saldo += quantidade;
    }

    public boolean transfere(Conta contaDestino, double valor){
        if (this.saca(valor)){
            contaDestino.deposita(valor);
            return true;
        }
        return false;
    }

    public void atualizaSaldo(double taxa){
        this.saldo += this.saldo * taxa;
    }
    
    abstract public String exibirDadosConta();
    
}
