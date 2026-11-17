/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.unesp.projetobanco.app;

import br.com.unesp.projetobanco.view.PrincipalView;

/**
 *
 * @author Vinicyus
 */
public class App {

    //private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PrincipalView.class.getName());
    
    public static void main(String[] args) {

        PrincipalView view = new PrincipalView();       
        view.setVisible(true);
   
    }
}



/*
package br.com.unesp.bancoapp.aplicacao;

import java.util.ArrayList;
import br.com.unesp.bancoapp.modelo.Cliente;
import br.com.unesp.bancoapp.modelo.ClientePF;
import br.com.unesp.bancoapp.modelo.ClientePJ;
import br.com.unesp.bancoapp.modelo.Conta;
import br.com.unesp.bancoapp.modelo.ContaCorrente;
import br.com.unesp.bancoapp.modelo.ContaPoupanca;
import br.com.unesp.bancoapp.modelo.Funcionario;
import br.com.unesp.bancoapp.modelo.Gerente;
import br.com.unesp.bancoapp.modelo.Diretor;
import br.com.unesp.bancoapp.modelo.Endereco;
import br.com.unesp.bancoapp.modelo.TecnicoBancario;
import br.com.unesp.bancoapp.servico.ControleBonificacoes;
import br.com.unesp.bancoapp.servico.AtualizadorConta;


public class App {

    public static void main(String[] var0) {
        //Cadastro de Clientes
        ArrayList<Cliente> clientes= new ArrayList<>();
        Endereco endereco1 = new Endereco("Jose Manoel de Angelo", 176, "Centro", "Castilho", "16920-000");
        Endereco endereco2 = new Endereco("Antonio Carlos Magalhaes", 594, "Sao Francisco", "Xique-Xique", "47400-000");
        Cliente cliente1 = new ClientePF("(12)34567-8910", "wingardium.leviosa@gmail.com", endereco1, "Harry Potter", "123.456.789-10");
        Cliente cliente2 = new ClientePJ("4002-8922", "hotel.xiquexique@outlook.com", endereco2, "Xique Xique Palace Hotel", "12.345.678/0001-99");
        clientes.add(cliente1);
        clientes.add(cliente2);

        //Contas
        Conta cc = new ContaCorrente(9, cliente1, 2000.00);
        Conta cp = new ContaPoupanca(10, cliente2);


        //Depositar      
        cc.deposita(1500);
        cp.deposita(2450.62);
    

        //Transferir
        System.out.println("Saldo de " + cliente1.getNome() + " e de " + cliente2.getNome() + " antes da transferencia (respectivamente): R$" + cc.getSaldo() + " e R$" + cp.getSaldo());
        if (cc.transfere(cp, 50)){
            System.out.println("Saldo das contas depois da transferencia: R$" + cc.getSaldo() + " e R$" + cp.getSaldo());
        }else{
            System.out.println("Transferencia mal sucedida");
        }
        System.out.println();

        //Cadastrar Funcionarios
        //OBS: O salario base de um funcionario é o salario passado ao criar sua instância, no construtor o salário final é definido
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        Funcionario f1 = new Funcionario("Joaozinho Oliveira", "123.432.156-98", 1600);
        Gerente g1 = new Gerente("Rodolfo", "456.123.789-55", 6000.0, 1300.0, "Operacional");
        Diretor d1 = new Diretor("Bob Esponja", "34.937.056-65", 7420.35,  "Comercial");
        TecnicoBancario t1 = new TecnicoBancario("Patrick Estrela", "37.917.956-00", 5200.22,  "TI", 3);
        funcionarios.add(f1);
        funcionarios.add(g1);
        funcionarios.add(d1);
        funcionarios.add(t1);

        //Bonificação
        ControleBonificacoes controle = new ControleBonificacoes();
        controle.registraBonificacao(f1);
        controle.registraBonificacao(g1);
        System.out.println("Bonus do gerente " + g1.getNome() + ": " + g1.getBonificacao());
        System.out.println("Bonus do funcionario " + f1.getNome() + ": " + f1.getBonificacao());
        System.out.println("Total bonificacoes: " + controle.getTotalBonificacao());
        System.out.println();


        //Atualizador de Contas
        AtualizadorConta atualizador = new AtualizadorConta();
        atualizador.atualizarConta(cc, 0.02);
        atualizador.atualizarConta(cp, 0.01);
        System.out.println("Valor total atualizado ate o momento: " + String.format("%.2f", atualizador.getTotalAtualizado()));
        System.out.println();


        //Exibir dados de um funcionario
        System.out.println("Exibindo dados de um funcionario: ");
        g1.exibirDados();
        System.out.println();

        //Exibir informações de todos os funcionários
        System.out.println("Exibindo informacoes de todos os funcionarios cadastrados: ");
        for (Funcionario f : funcionarios){
            f.exibirDados();
            System.out.println();
        }

        //Exibir dados de um cliente
        System.out.println("Exibindo dados de um cliente: ");
        cliente1.exibirDados();
        System.out.println();

        //Exibir informações de todos os clientes
        System.out.println("Exibindo informacoes de todos os clientes");
        for (Cliente cliente : clientes){
            cliente.exibirDados();
            System.out.println();
        }

        

    }
}

*/
