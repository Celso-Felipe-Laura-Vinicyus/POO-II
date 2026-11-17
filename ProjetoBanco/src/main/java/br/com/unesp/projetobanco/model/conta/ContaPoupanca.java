package br.com.unesp.projetobanco.model.conta;

import br.com.unesp.projetobanco.model.cliente.Cliente;

public class ContaPoupanca extends Conta{

    //Construtor
    public ContaPoupanca(int numero, Cliente titular) {
        super(numero, titular);
    }

    //Métodos
    @Override
    public void atualizaSaldo(double taxa){
        super.atualizaSaldo(taxa*3);
    }   
    
    @Override
    public String exibirDadosConta(){
        String dados = "Número: " + numero + "\n";
        dados = dados + "Tipo de conta: Conta Poupança" + "\n";
        dados = dados + "Saldo:" + saldo + "\n";
        return dados;
    }
    
}
