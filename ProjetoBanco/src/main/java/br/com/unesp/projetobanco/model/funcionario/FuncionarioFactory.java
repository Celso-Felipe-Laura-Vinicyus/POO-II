/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unesp.projetobanco.model.funcionario;

/**
 *
 * @author PC
 */
public class FuncionarioFactory {
    public static final String GERENTE = "GERENTE";
    public static final String DIRETOR = "DIRETOR";
    public static final String TECNICO_BANCARIO = "TECNICO_BANCARIO";
    
    public Funcionario criar(String tipo, DadosFuncionario dados) {
        if (GERENTE.equalsIgnoreCase(tipo)) {
            return new Gerente(
                dados.getNome(),
                dados.getCpf(),
                dados.getEndereco(),
                dados.getSalario(),
                dados.getBonus(),
                dados.getAgencia()
            );
        } else if (DIRETOR.equalsIgnoreCase(tipo)) {
            return new Diretor(
                dados.getNome(),
                dados.getCpf(),
                dados.getEndereco(),
                dados.getSalario(),
                dados.getArea()
            );
        } else if (TECNICO_BANCARIO.equalsIgnoreCase(tipo)) {
            return new TecnicoBancario(
                dados.getNome(),
                dados.getCpf(),
                dados.getEndereco(),
                dados.getSalario(),
                dados.getSetor(),
                dados.getHorasExtras()
            );
        } else {
            return new Funcionario(
                dados.getNome(),
                dados.getCpf(),
                dados.getEndereco(),
                dados.getSalario()
            );
        }
    }
    
}
