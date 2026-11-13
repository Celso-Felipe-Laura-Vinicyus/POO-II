public class App {

    public static void main() {
        Endereco e1 = new Endereco("Rua Santos Dumont","153","Vila Ortega","19362-160","3263-3305");
        Funcionario f1 = new Funcionario("Celso Garcia Neto","39947773809",e1,2000.00);

        System.out.println("===== FUNCIONÁRIOS =====");
        System.out.println("Nome: " + f1.getNome() +
                            "\nCPF: " + f1.getCPF() +
                            "\n" + f1.getEndereco() +
                            "\nSalário: R$ " + f1.getSalarioBase());
    }
}
