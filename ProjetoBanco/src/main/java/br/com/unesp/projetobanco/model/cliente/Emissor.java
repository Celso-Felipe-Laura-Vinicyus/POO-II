package br.com.unesp.projetobanco.model.cliente;

/**
 *
 * @author PC
 */
public interface Emissor {
    String enviar(Cliente cliente, String mensagem);
}
