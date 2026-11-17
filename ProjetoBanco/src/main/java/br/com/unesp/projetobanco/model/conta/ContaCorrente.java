package br.com.unesp.projetobanco.model.conta;

import br.com.unesp.projetobanco.model.cliente.Cliente;

public class ContaCorrente extends Conta{
    private double limite;


    //Construtor
    public ContaCorrente(int numero, Cliente titular, double limite) {
        super(numero, titular);
        this.limite = limite;
    }


    //Métodos
    @Override
    public void atualizaSaldo(double taxa){
        super.atualizaSaldo(taxa*2);
    }
    
    @Override
    public String exibirDadosConta(){
        String dados = "Número: " + numero + "\n";
        dados = dados + "Tipo de conta: Conta Corrente" + "\n";
        dados = dados + "Limite:" + limite + "\n";
        dados = dados + "Saldo:" + saldo + "\n";
        return dados;
    }

    
    //Get e Set///////////////////////////////
    public double getLimite() {
        return limite;
    }
    public void setLimite(double limite) {
        this.limite = limite;
    }
}
