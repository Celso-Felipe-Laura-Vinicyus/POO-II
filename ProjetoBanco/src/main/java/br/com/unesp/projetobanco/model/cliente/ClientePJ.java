package br.com.unesp.projetobanco.model.cliente;

import br.com.unesp.projetobanco.model.endereco.Endereco;

public class ClientePJ extends Cliente{
    private String razaoSocial;
    private String cnpj;
    
    //Construtor
    public ClientePJ(String telefone, String email, Endereco endereco, String razaoSocial, String cnpj) {
        super(telefone, email, endereco);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
    }


    //Métodos
    @Override
    public String getNome() {
        return razaoSocial;
    }
    @Override
    public String exibirDados(){
        String dados = "Razão social: " + razaoSocial + "\n";
        dados = dados + "Tipo de cliente: Pessoa jurídica" + "\n";
        dados = dados + "CNPJ: " + cnpj + "\n";
        dados = dados + "Telefone: " + telefone + "\n";
        dados = dados + "Email: " + email;
        dados = dados + endereco.exibir() + "\n";
        return dados;
    }


    //Getters e Setters
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
