public class Endereco {

    private String logradouro;
    private String numero;
    private String bairro;
    private String CEP;
    private String telefone;


    public Endereco(String logradouro, String numero, String bairro, String CEP, String telefone) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.CEP = CEP;
        this.telefone = telefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
