package br.com.unesp.projetobanco.model.cliente;

import br.com.unesp.projetobanco.model.endereco.Endereco;

/**
 *
 * @author PC
 */
public class ClienteFactory {
    public static final boolean CLIENTE_FISICO = true;
    public static final boolean CLIENTE_JURIDICO = false;
    
    public Cliente criar(boolean tipoCliente, String telefone, String email, Endereco endereco, String nome, String cpf_cnpj){
        if (tipoCliente == CLIENTE_FISICO){
            return new ClientePF(telefone, email, endereco, nome, cpf_cnpj);
        }
        if (tipoCliente == CLIENTE_JURIDICO){
            return new ClientePJ(telefone, email, endereco, nome, cpf_cnpj);
        }
        return null;
    }
}
