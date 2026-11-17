package br.com.unesp.projetobanco.model.endereco;

public class Endereco {
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String telefone;
    
    //Contrutor
    public Endereco(String rua, int numero, String bairro, String cidade, String estado, String cep, String telefone) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.telefone = telefone;
    }
    
    
    //Exibir endereço
    public String exibir(){
        String info = "\nEndereço: ";
        if (rua != null){
            info += "Rua " + rua + ", " + numero;
        }
        if (bairro != null){
            info += "\nBairro: " + bairro;
        }
        if (cidade != null){
            info += ", cidade de " + cidade;
        }
        if (estado != null){
            info += ", estado de " + estado;
        }
        if (cep != null){
            info += "\nCEP: " + cep;
        }
        if (telefone != null){
            info += "\nTelefone residencial: " + telefone;
        }
        return info;
    }
    

    //Getters e Setters
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }  
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    
}
