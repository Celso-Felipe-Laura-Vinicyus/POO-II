package br.com.unesp.projetobanco.model.cliente;

/**
 *
 * @author vso.santos_unesp
 */
public class EmissorSMS implements Emissor{
    @Override
    public String enviar(Cliente cliente, String mensagem){
        return "Enviando mensagem SMS para o número " + cliente.getTelefone() + ":\n" + mensagem;
    }
}
