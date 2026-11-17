package br.com.unesp.projetobanco.model.conta;

import br.com.unesp.projetobanco.model.conta.Conta;

public class AtualizadorConta {
    private double totalAtualizado;

    //Método
    public void atualizarConta(Conta conta, double taxa){
        //System.out.println("Saldo anterior do cliente " + conta.getTitular().getNome() + ": " + conta.getSaldo());
        conta.atualizaSaldo(taxa);
        this.totalAtualizado += conta.getSaldo();
        //System.out.println("Saldo do cliente depois da atualizacao : " + String.format("%.2f", conta.getSaldo()));
        //System.out.println();
    }

    //Get
    public double getTotalAtualizado(){
        return this.totalAtualizado;
    }

}
