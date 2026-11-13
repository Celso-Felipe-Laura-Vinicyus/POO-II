/**
 * Classe base Funcionario, que é utilozada para fazer a hierarquia de cargos dos colaboradores do banco.
 * Da classe, advém classes filhas: Gerente, TecnicoBancario e Diretor.
 * Por se tratar de uma classe base, a classe Funcionario não possui uma regra de cálculo para o salário.
 */
public class Funcionario {

    // Atributos comuns
    private String nome;
    private String CPF;
    private Endereco endereco;
    private double salarioBase;

    // Construtor da classe
    public Funcionario(String nome, String CPF, Endereco endereco,double salarioBase) {
        this.nome = nome;
        this.CPF = CPF;
        this.endereco = endereco;
        this.salarioBase = salarioBase;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEndereco() {
        return("Logradoro: " + endereco.getLogradouro() +
                "\nNúmero: " + endereco.getNumero() +
                "\nBairro: " + endereco.getBairro() +
                "\nCEP: " + endereco.getCEP() +
                "\nTelefone: " + endereco.getTelefone());
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }
}
