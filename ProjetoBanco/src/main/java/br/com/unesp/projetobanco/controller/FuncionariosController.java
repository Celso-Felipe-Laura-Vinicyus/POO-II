package br.com.unesp.projetobanco.controller;
//view
import br.com.unesp.projetobanco.model.endereco.Endereco;
import br.com.unesp.projetobanco.view.PrincipalView;
import br.com.unesp.projetobanco.view.CadastrarFuncionarioView;
import br.com.unesp.projetobanco.view.PesquisarFuncionarioView;
//model
import br.com.unesp.projetobanco.model.funcionario.Funcionario;
import br.com.unesp.projetobanco.model.funcionario.DadosFuncionario;
import br.com.unesp.projetobanco.model.funcionario.FuncionarioFactory;
//java
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * author Vinicyus SOS
 */
public class FuncionariosController {
    private PrincipalView viewPrincipal;
    private ArrayList<Funcionario> funcionarios;
    private Funcionario funcionarioPesquisado;
    private FuncionarioFactory funcionarioFactory;
    
    //Construtor
    public FuncionariosController(PrincipalView viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
        funcionarios = new ArrayList<Funcionario>();
        funcionarioPesquisado = null;
        funcionarioFactory = new FuncionarioFactory();
    }
    
    //Cria tela de cadastro
    public void cadastrarFuncionario(){
        CadastrarFuncionarioView view = new CadastrarFuncionarioView();
        //Botão de cadastrar
        view.getjButtonCadastrarFuncionario().addActionListener(l -> {
            try{
                //Extraindo dados da view
                String nome = view.getjTextFieldNome().getText();
                String cpf = view.getjTextFieldCpf().getText();
                Double salario = Double.parseDouble(view.getjTextFieldSalario().getText());
                String rua = view.getjTextFieldRua().getText();
                int numero = Integer.parseInt(view.getjTextFieldNumero().getText());
                String bairro = view.getjTextFieldBairro().getText();
                String cidade = view.getjTextFieldCidade().getText();
                String estado = view.getjTextFieldEstado().getText();
                String cep = view.getjTextFieldCep().getText();
                String telResidencial = view.getjTextFieldTelefone().getText();
                
                //Criando endereço
                Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep, telResidencial);
                
                //Dados do funcionário
                DadosFuncionario dados = new DadosFuncionario();
                dados.setNome(nome);
                dados.setSalario(salario);
                dados.setCpf(cpf);
                dados.setEndereco(endereco);
                
                Funcionario funcionario;
                //Gerente
                if (view.getjRadioButtonGerente().isSelected()) {
                    dados.setAgencia(view.getjTextFieldAgencia().getText());
                    dados.setBonus(Double.parseDouble(view.getjTextFieldBonus().getText()));
                    funcionario = funcionarioFactory.criar("GERENTE", dados);
                //Técnico banbário
                } else if (view.getjRadioButtonTecnico().isSelected()) {
                    dados.setSetor(view.getjTextFieldSetor().getText());
                    dados.setHorasExtras(Double.parseDouble(view.getjTextFieldHorasExtras().getText()));
                    funcionario = funcionarioFactory.criar("TECNICO_BANCARIO", dados);
                //Diretor
                } else if (view.getjRadioButtonDiretor().isSelected()) {
                    dados.setArea(view.getjTextFieldArea().getText());
                    funcionario = funcionarioFactory.criar("DIRETOR", dados);
                //Funcionário genérico
                } else { 
                    funcionario = funcionarioFactory.criar(null, dados);
                }
                funcionarios.add(funcionario);
                view.reset();
                JOptionPane.showMessageDialog(view, "Funcionário cadastrado com sucesso!", "Funcionário cadastrado", JOptionPane.INFORMATION_MESSAGE);
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(view, "Alguns campos só aceitam valores númericos válidos!", "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
            }
        });
        view.setVisible(true);
    }
    
    //Cria tela de pesquisa, que exibe informações do usuário e permite promover bonificações
    public void pesquisarFuncionario(){
        PesquisarFuncionarioView view = new PesquisarFuncionarioView();
        atualizarCombobox(view);
        //Botão de pesquisar funcionário
        view.getjButtonPesquisarFuncionario().addActionListener(l -> {
            if (view.getjComboBoxFuncionarios().getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(view, "Nenhum funcionário selecioando!", "Erro ao pesquisar", JOptionPane.ERROR_MESSAGE);           
            }else{          
                String descricao = view.getjComboBoxFuncionarios().getSelectedItem().toString();
                for (Funcionario f : funcionarios){
                    if (descricao.equals(f.descricao())){
                        funcionarioPesquisado = f;
                    }
                }
                //Exibe dados do funcionário
                view.getjTextAreaInfoFuncionario().setText(funcionarioPesquisado.exibirDados());
                JOptionPane.showMessageDialog(view, "Funcionário pesquisado!", "Funcionário pesquisado", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        view.setVisible(true);
    }
  
    //Atualiza combobox de funcionarios
    public void atualizarCombobox(PesquisarFuncionarioView view){
        view.getjComboBoxFuncionarios().removeAllItems();
        view.getjComboBoxFuncionarios().addItem("Formato: Nome - CPF - Salario");
        for (Funcionario f : funcionarios){
           view.getjComboBoxFuncionarios().addItem(f.descricao());
        }
    }
    
    //Exibe informações de todos os funcionários
    public void exibirFuncionarios(){
        viewPrincipal.getjTextAreaInfoFuncionarios().setText("");
        int i = 1;
        for (Funcionario f : funcionarios){
           viewPrincipal.getjTextAreaInfoFuncionarios().append("FUNCIONÁRIO " + i + "----------------------------------\n");
           viewPrincipal.getjTextAreaInfoFuncionarios().append(f.exibirDados() + "\n");
           i++;
        }
    }
     
}
