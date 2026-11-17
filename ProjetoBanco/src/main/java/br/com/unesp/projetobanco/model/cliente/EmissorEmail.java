package br.com.unesp.projetobanco.model.cliente;

/**
 *
 * @author PC
 */
public class EmissorEmail implements Emissor{
    
    @Override
    public String enviar(Cliente cliente, String mensagem){
        return "Enviando e-mail para " + cliente.getEmail() + ":\n" + mensagem;
    }
}
