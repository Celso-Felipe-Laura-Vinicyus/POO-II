package br.com.unesp.projetobanco.controller;
//java
import java.util.ArrayList;
import javax.swing.JOptionPane;
//view
import br.com.unesp.projetobanco.view.CadastrarClienteView;
import br.com.unesp.projetobanco.view.PesquisarClienteView;
import br.com.unesp.projetobanco.view.PrincipalView;
import br.com.unesp.projetobanco.view.AtualizarContaView;
//model
import br.com.unesp.projetobanco.model.cliente.Cliente;
import br.com.unesp.projetobanco.model.cliente.ClienteFactory;
import br.com.unesp.projetobanco.model.endereco.Endereco;
import br.com.unesp.projetobanco.model.conta.Conta;
import br.com.unesp.projetobanco.model.conta.ContaCorrente;
import br.com.unesp.projetobanco.model.conta.ContaPoupanca;
import br.com.unesp.projetobanco.model.conta.AtualizadorConta;

/**
 *
 * author Vinicyus SOS
 */

public class ClientesController {
    ArrayList<Cliente> clientes; 
    ArrayList<Conta> contas;
    int numeroConta; //aumenta a cada cliente cadastrado
    Conta contaPesquisada; //Conta que sera exibida e na qual serão executadas as transações e a atualização
    Cliente clientePesquisado; //Quando cliente não tiver conta, contaPesquisada = null
    AtualizadorConta atualizador;
    ClienteFactory clienteFactory;
    
    //Construtor
    public ClientesController() {     
        this.clientes = new ArrayList<>();
        this.contas = new ArrayList<>();
        this.numeroConta = 1;
        contaPesquisada = null; //Cliente sem conta
        atualizador = new AtualizadorConta();
        clienteFactory = new ClienteFactory();
    }
    
    
    
    
    
    
    //Cria tela de cadastro e adiciona funções ao botão de cadastrar
    public void cadastrarCliente(){
        CadastrarClienteView cadastro = new CadastrarClienteView(this);      
        //botão de cadastrar
        cadastro.getjButtonCadastrarCliente().addActionListener(e -> {
            Cliente cliente = criarCliente(cadastro);
            if (cliente != null) { //Se cliente foi criado, ele é adicionado na lista
                //Caso conta seja criada junto com o cadastro do cliente
                try{
                    if (cadastro.getjRadioButtonContaCorrente().isSelected()){
                        Conta conta = new ContaCorrente(numeroConta, cliente, 
                                Double.parseDouble(cadastro.getjTextFieldLimite().getText())
                        );
                        numeroConta++;
                        contas.add(conta);
                    }else if (cadastro.getjRadioButtonContaPoupanca().isSelected()){
                        Conta conta = new ContaPoupanca(numeroConta, cliente);
                        numeroConta++;
                        contas.add(conta);
                    }
                }catch (NumberFormatException i){
                    JOptionPane.showMessageDialog(cadastro, "O campo 'Limite' deve conter apenas valores numéricos válidos!", "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                clientes.add(cliente);
            }else return;
            JOptionPane.showMessageDialog(
                cadastro, "Cliente cadastrado com sucesso!", "Cliente cadastrado", JOptionPane.INFORMATION_MESSAGE
            );              
        });  
        cadastro.setVisible(true);
    }
   
    
    //Retorna cliente com base nas informações inseridas na tela de cadastro
    public Cliente criarCliente(CadastrarClienteView cadastro){
        //Caso nenhum tipo de cliente (PF ou PJ) esteja selecionado
        if (!cadastro.getjRadioButtonClientePf().isSelected() && !cadastro.getjRadioButtonClientePj().isSelected()) {
            JOptionPane.showMessageDialog(cadastro, "Tipo de cliente não selecionado", "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        try{
            //Extraindo dados preenchidos na view
            String rua = cadastro.getjTextFieldRua().getText();
            int numero = Integer.parseInt(cadastro.getjTextFieldNumero().getText());
            String bairro = cadastro.getjTextFieldBairro().getText();
            String cidade = cadastro.getjTextFieldCidade().getText();
            String estado = "SP"; // Depois troque por: cadastro.getjTextFieldEstado().getText()
            String cep = cadastro.getjTextFieldCep().getText();
            String telResidencial = "4002-8922"; // Depois troque por: cadastro.getjTextFieldTelefoneResidencial().getText()
            String telefone = cadastro.getjTextFieldTelefone().getText();
            String email = cadastro.getjTextFieldEmail().getText();
            String nome = cadastro.getjTextFieldNomeCliente().getText();
            String cpfCnpj = cadastro.getjTextFieldCpfCliente().getText();
            boolean isPessoaFisica = cadastro.getjRadioButtonClientePf().isSelected();

            //Criando endereço
            Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep, telResidencial);
            
            //Criando cliente (físico ou jurídico, decidido na fábrica)
            Cliente cliente = clienteFactory.criar(isPessoaFisica, telefone, email, endereco, nome, cpfCnpj);
            return cliente;
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(cadastro, "O campo 'Número' deve conter apenas valores numéricos válidos!", "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    
    
    
    
    //Cria tela de pesquisa e adiciona funções para os botões na tela
    public void pesquisarCliente(){
        PesquisarClienteView pesquisa = new PesquisarClienteView(this);
        contaPesquisada = null;
        clientePesquisado = null;
        atualizarVisibilidadeTransacao(pesquisa); //Esconde painel de transação
        atualizaCombobox(pesquisa); //Preenchendo comboboxes      
        //botão de pesquisar cliente
        pesquisa.getjButtonPesquisarCliente().addActionListener(e -> {
            //Verifica se não tem nenhum cliente selecionado
            if (pesquisa.getjComboBoxPesquisarClientes().getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(pesquisa, "Nenhum cliente selecionado!", "Erro ao pesquisar", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Exibe informações do cliente
            String clienteSelecionado = pesquisa.getjComboBoxPesquisarClientes().getSelectedItem().toString();
            String[] partes = clienteSelecionado.split(" - ");
            String numConta = partes[0];
            String nome = partes[1];
            if (numConta.equals("Sem conta")) {
                contaPesquisada = null;
                clientePesquisado = pesquisarClienteNome(nome); // Se cliente não tiver uma conta, busca por nome
                //exibirInfoCliente(pesquisa, nome); 
            } else {
                contaPesquisada = pesquisarContaNumero(Integer.parseInt(numConta));
                clientePesquisado = contaPesquisada.getTitular();
                //exibirInfoCliente(pesquisa, Integer.parseInt(numConta)); // Se Cliente tiver uma conta, busca pelo número da conta     
            }
            exibirInfoCliente(pesquisa);
            atualizarVisibilidadeTransacao(pesquisa); //Exibe painel de transação se cliente tiver uma conta           
            if (contaPesquisada == null){ //sem conta
                JOptionPane.showMessageDialog(
                        pesquisa, 
                        "Cliente " + clientePesquisado.getNome() + ", sem conta, selecionado ", 
                        "Cliente selecionado",
                        JOptionPane.INFORMATION_MESSAGE);                
            }else{ //com conta
                JOptionPane.showMessageDialog(
                        pesquisa, 
                        "Cliente " + clientePesquisado.getNome() + ", e conta " + contaPesquisada.getNumero() + " selecionados",
                        "Conta e cliente selecionados", 
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });       
        //Botão de criar conta
        pesquisa.getjButtonCriarConta().addActionListener(e -> {
            if (clientePesquisado == null){
                JOptionPane.showMessageDialog(pesquisa, "Nenhum cliente selecionado", "Erro ao criar conta", JOptionPane.ERROR_MESSAGE);
            }else{
                try{
                    if (pesquisa.getjRadioButtonContaCorrente().isSelected()){
                        Double limite = Double.parseDouble(pesquisa.getjTextFieldLimite().getText());
                        Conta conta = new ContaCorrente(numeroConta, clientePesquisado, limite);
                        numeroConta++;
                        contas.add(conta);
                        //exibirInfoCliente(pesquisa, conta.getNumero());
                        contaPesquisada = conta;
                        clientePesquisado = conta.getTitular();
                        atualizaCombobox(pesquisa);
                        exibirInfoCliente(pesquisa);
                        atualizarVisibilidadeTransacao(pesquisa);
                        JOptionPane.showMessageDialog(
                                pesquisa, 
                                "Conta criada com sucesso! \nConta " + conta.getNumero() + " selecionada",
                                "Conta criada", 
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }else if (pesquisa.getjRadioButtonContaPoupanca().isSelected()){
                        Conta conta = new ContaPoupanca(numeroConta, clientePesquisado);
                        numeroConta++;
                        contas.add(conta);
                        //exibirInfoCliente(pesquisa, conta.getNumero());
                        contaPesquisada = conta;
                        clientePesquisado = conta.getTitular();
                        atualizaCombobox(pesquisa);
                        exibirInfoCliente(pesquisa);
                        atualizarVisibilidadeTransacao(pesquisa);
                        JOptionPane.showMessageDialog(
                                pesquisa, 
                                "Conta criada com sucesso! \nConta " + conta.getNumero() + " selecionada",
                                "Conta criada", 
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }else{
                        JOptionPane.showMessageDialog(pesquisa, "Nenhum tipo de conta selecionado", "Erro ao criar conta", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (NumberFormatException i){
                    JOptionPane.showMessageDialog(pesquisa, "O campo 'Limite' deve conter apenas valores numéricos válidos!", "Erro ao criar conta", JOptionPane.ERROR_MESSAGE);
                }
            }
        });      
        //Botão de realizar transação
        pesquisa.getjButtonRealizarTransacao().addActionListener(e -> {   
            try{
                Double valor = Double.parseDouble(pesquisa.getjTextFieldValor().getText());
                //Transferência
                if (pesquisa.getjRadioButtonTransferencia().isSelected()){ 
                    //Verifica se não tem nenhum cliente selecionado
                    if (pesquisa.getjComboBoxPesquisarClientesTransferencia().getSelectedIndex() == 0){
                        JOptionPane.showMessageDialog(pesquisa, "Nenhuma conta foi selecionada para realizar a transferência!", "Erro ao realizar transferência", JOptionPane.ERROR_MESSAGE);
                    }else{
                        String contaSelecionada = pesquisa.getjComboBoxPesquisarClientesTransferencia().getSelectedItem().toString();
                        String[] partes = contaSelecionada.split(" - ");
                        int numConta = Integer.parseInt(partes[0]);
                        if (transferencia(numConta, valor)){
                            JOptionPane.showMessageDialog(pesquisa, "Transferência bem sucedida!", "Tranferência realizada", JOptionPane.INFORMATION_MESSAGE);
                            //exibirInfoCliente(pesquisa, contaPesquisada.getNumero());
                            exibirInfoCliente(pesquisa);
                        } else{
                            JOptionPane.showMessageDialog(pesquisa, "Transferência mal sucedida, saldo insuficiente", "Erro ao transferir", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                //Depósito
                } else if (pesquisa.getjRadioButtonDeposito().isSelected()){ 
                    contaPesquisada.deposita(valor);
                    //exibirInfoCliente(pesquisa, contaPesquisada.getNumero());
                    exibirInfoCliente(pesquisa);
                    JOptionPane.showMessageDialog(pesquisa, "Depósito bem sucedido!", "Depósito realizado", JOptionPane.INFORMATION_MESSAGE);                    
                //Saque
                } else if (pesquisa.getjRadioButtonSaque().isSelected()){ 
                    if (contaPesquisada.saca(valor)){
                        //exibirInfoCliente(pesquisa, contaPesquisada.getNumero());
                        exibirInfoCliente(pesquisa);
                        JOptionPane.showMessageDialog(pesquisa, "Saque bem sucedido!", "Saque realizado", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(pesquisa, "Saque mal sucedido, saldo insuficiente", "Erro ao sacar", JOptionPane.ERROR_MESSAGE);                       
                    }
                } else {
                     JOptionPane.showMessageDialog(pesquisa, "Nenhum tipo de transação selecionada!", "Erro ao realizar transação", JOptionPane.ERROR_MESSAGE);
                }
            }catch (NumberFormatException i){
                JOptionPane.showMessageDialog(pesquisa, "O campo 'Valor' deve conter apenas valores numéricos válidos!", "Erro ao realizar transação", JOptionPane.ERROR_MESSAGE);
            }
        });
        pesquisa.setVisible(true);
    }
    
    
    //Realizar Transferência
    public Boolean transferencia(int numContaDestino, Double valor){       
        for (Conta conta : contas){ //Procura conta
            if (conta.getNumero() == numContaDestino){
                //retorna true se transferência foi bem sucedida, e false se não
                return (contaPesquisada.transfere(conta, valor));
            }
        }
        return false;
    }
    
    
    //Preenche combobox de pesquisar cliente
    public void atualizaCombobox(PesquisarClienteView pesquisa){
        //Guarda item atual para voltar depois
        //String itemAnterior = pesquisa.getjComboBoxPesquisarClientes().getSelectedItem().toString();
        //Limpa comboxe e adiciona o item neutro
        pesquisa.getjComboBoxPesquisarClientes().removeAllItems();
        pesquisa.getjComboBoxPesquisarClientes().addItem("Formato: (Número da conta) - (Nome do cliente)");
        for (Cliente cliente : clientes){
            Boolean temConta = false;
            for (Conta conta : contas){
                //Clientes que tem pelo menos uma conta
                if (conta.getTitular() == cliente){
                   pesquisa.getjComboBoxPesquisarClientes().addItem(conta.getNumero() + " - " + cliente.getNome());
                   temConta = true;
                }
            }
            //Clientes que não tem nenhuma conta
            if (!temConta) {
                pesquisa.getjComboBoxPesquisarClientes().addItem("Sem conta - " + cliente.getNome());
            }
        }
        /*String[] partes = itemAnterior.split(" - ");
        if (!partes[0].equals("Sem conta")){ //Se o item anterior era um cliente sem conta, volta pra posição genérica
            for (int i = 0; i < pesquisa.getjComboBoxPesquisarClientes().getItemCount(); i++){
                String item = pesquisa.getjComboBoxPesquisarClientes().getItemAt(i);
                if (itemAnterior.equals(item)){
                    pesquisa.getjComboBoxPesquisarClientes().setSelectedIndex(i);
                    break;
                }
            }
        }*/
    }
    
    
    //Preenche combobox de pesquisar conta para realizar transferência
    public void atualizaComboboxTransferencia(PesquisarClienteView pesquisa){
        //Limpa comboxe e adiciona o item neutro
        pesquisa.getjComboBoxPesquisarClientesTransferencia().removeAllItems();
        pesquisa.getjComboBoxPesquisarClientesTransferencia().addItem("Formato: (Número da conta) - (Nome do cliente)");
        //Para esse combobox, não importa os clientes sem conta, pois não é possível transferir para um cliente sem conta 
        for (Conta conta : contas){
            if (conta != contaPesquisada){
                pesquisa.getjComboBoxPesquisarClientesTransferencia().addItem(conta.getNumero() + " - " + conta.getTitular().getNome());
            }
        }
    }
    
    
    //Encontrar cliente sem conta do combobox
    public Cliente pesquisarClienteNome(String nome){
        for (Cliente cliente : clientes){
            if (cliente.getNome().equals(nome)){
                return cliente;
            }
        }
        return null;
    }
    
    //Encontrar conta no combobox
    public Conta pesquisarContaNumero(int numero){
        for (Conta conta : contas){
            if (conta.getNumero() == numero){
                return conta;
            }
        }
        return null;
    }
    
    
    public void exibirInfoCliente(PesquisarClienteView pesquisa){
        if (contaPesquisada == null){ //sem conta
            //Exibe cliente sem conta
            pesquisa.getjTextAreaInfoCliente().setText("");
            pesquisa.getjTextAreaInfoCliente().append("DADOS DO CLIENTE---------------------------\n");
            pesquisa.getjTextAreaInfoCliente().append(clientePesquisado.exibirDados());
        }else{ //com conta
            //Exibe cliente com a conta pesquisada e qualquer outra que a ele pertence
            pesquisa.getjTextAreaInfoCliente().setText("");
            pesquisa.getjTextAreaInfoCliente().append("DADOS DO CLIENTE---------------------------\n");
            pesquisa.getjTextAreaInfoCliente().append(clientePesquisado.exibirDados());
            pesquisa.getjTextAreaInfoCliente().append("DADOS DA CONTA (PESQUISADA)---------------------------\n");
            pesquisa.getjTextAreaInfoCliente().append(contaPesquisada.exibirDadosConta() + "\n\n\n");
            //Procura por outras conta do cliente e imprime
            for (Conta conta: contas){
                if (conta.getTitular() == clientePesquisado && conta != contaPesquisada){
                    pesquisa.getjTextAreaInfoCliente().append("OUTRA CONTA DO CLIENTE---------------------------\n");
                    pesquisa.getjTextAreaInfoCliente().append(conta.exibirDadosConta());
                }
            } 
            }
      
    }
    
    
    //Exibir informação do cliente pesquisado usando o número da conta
    /*public void exibirInfoCliente(PesquisarClienteView pesquisa, int numeroContaPesquisa){
        //Procura conta com base no número
        for (Conta conta : contas){
            if (conta.getNumero() == numeroContaPesquisa){
                contaPesquisada = conta; //atualiza contaPesquisada
                break;
            }
        }
        clientePesquisado = contaPesquisada.getTitular(); //atualiza clientePesquisado
        pesquisa.getjTextAreaInfoCliente().setText("");
        pesquisa.getjTextAreaInfoCliente().append("DADOS DO CLIENTE---------------------------\n");
        pesquisa.getjTextAreaInfoCliente().append(clientePesquisado.exibirDados());
        //Procura por alguma conta do cliente e imprime
        for (Conta conta: contas){
            if (conta.getTitular() == clientePesquisado){
                pesquisa.getjTextAreaInfoCliente().append("CONTA DO CLIENTE---------------------------\n");
                pesquisa.getjTextAreaInfoCliente().append(conta.exibirDadosConta());
            }
        }
    }*/
   
    
    //Exibir informação do cliente pesquisado usando seu nome (cliente sem conta)
    /*public void exibirInfoCliente(PesquisarClienteView pesquisa, String nomeCliente){
        contaPesquisada = null; //reseta contaPesquisada pois o cliente pesquisado no momento não tem conta
        //Procura cliente pelo nome
        for (Cliente cliente : clientes){
            if (cliente.getNome().equals(nomeCliente)) {
                clientePesquisado = cliente; //atualiza clientePesquisado
                break;
            }
        }
        pesquisa.getjTextAreaInfoCliente().setText("");
        pesquisa.getjTextAreaInfoCliente().append("DADOS DO CLIENTE---------------------------\n");
        pesquisa.getjTextAreaInfoCliente().append(clientePesquisado.exibirDados());
    }*/
    

    //Exibe ou esconde o painel de Transação, dependendo se o cliente tem uma conta ou não
    public void atualizarVisibilidadeTransacao(PesquisarClienteView pesquisar){
        if (contaPesquisada == null){ //cliente sem conta
            pesquisar.getjPanelAcaoCliente().setVisible(false);           
        }else{ //cliente com conta
            pesquisar.getjPanelAcaoCliente().setVisible(true);
            atualizaComboboxTransferencia(pesquisar); //preenche combobox de contas na opção de transferência
        }
        pesquisar.revalidate(); 
        pesquisar.repaint();
    }

    
    
    
    
    //Imprime dados de todos os clientes (e suas contas) no campo de texto na pagina principal
    public void exibirInfoTodosClientes(PrincipalView principal){
        principal.getjTextAreaInformacoesTodosClientes().setText("");
        for (Cliente cliente : clientes) {
            principal.getjTextAreaInformacoesTodosClientes().append("DADOS DO CLIENTE---------------------------\n");
            principal.getjTextAreaInformacoesTodosClientes().append(cliente.exibirDados());
            //Procura por alguma conta do cliente e imprime
            for (Conta conta: contas){
                if (conta.getTitular() == cliente){
                    principal.getjTextAreaInformacoesTodosClientes().append("CONTA DO CLIENTE---------------------------\n");
                    principal.getjTextAreaInformacoesTodosClientes().append(conta.exibirDadosConta());
                }
            }
            principal.getjTextAreaInformacoesTodosClientes().append("\n\n\n");
        }
    }
    
    
    
    
    
    //Abre página de atualizar conta e dá funções aos botões
    public void atualizarConta(){
        AtualizarContaView atualizar = new AtualizarContaView();      
        //Limpa combox e adiciona o item neutro
        atualizar.getjComboBoxPesquisarContaAtualizarConta().removeAllItems();
        atualizar.getjComboBoxPesquisarContaAtualizarConta().addItem("Formato: (Número da conta) - (Nome do cliente)");
        //Para esse combobox, não importa os clientes sem conta, pois não é possível atualizar saldo de um cliente sem conta 
        for (Conta conta : contas){
            atualizar.getjComboBoxPesquisarContaAtualizarConta().addItem(conta.getNumero() + " - " + conta.getTitular().getNome());
        }       
        //Botão para selecionar conta
        atualizar.getjButtonSelecionarConta().addActionListener(e -> {
            if (atualizar.getjComboBoxPesquisarContaAtualizarConta().getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(atualizar, "Nenhuma conta foi selecionada para realizar a transferência!", "Erro ao realizar transferência", JOptionPane.ERROR_MESSAGE);
            }else{
                String contaSelecionada = atualizar.getjComboBoxPesquisarContaAtualizarConta().getSelectedItem().toString();
                String[] partes = contaSelecionada.split(" - ");
                int numConta = Integer.parseInt(partes[0]);
                for (Conta conta : contas){
                    if (conta.getNumero() == numConta){
                        contaPesquisada = conta;
                        break;
                    }
                }
                //Exibir informações da conta
                atualizar.getjTextAreaInfoConta().setText(contaPesquisada.exibirDadosConta());
                try{
                    //botão de aplicar taxa
                    atualizar.getjButtonAplicarTaxa().addActionListener(l -> {
                        if (contaPesquisada.getSaldo() != 0.0){
                            Double taxa = Double.parseDouble(atualizar.getjTextFieldTaxa().getText());
                            taxa = taxa/100; //Convertendo porcentagem para decimal
                            atualizador.atualizarConta(contaPesquisada, taxa);
                            atualizar.getjTextAreaInfoConta().setText(contaPesquisada.exibirDadosConta());
                            JOptionPane.showMessageDialog(atualizar, "Taxa aplicada com sucesso!", "Taxa aplicada", JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(atualizar, "Sem saldo pra aplicar taxa!", "Erro ao aplicar taxa", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                }catch (NumberFormatException i){
                    JOptionPane.showMessageDialog(atualizar, "O campo 'Taxa' deve conter apenas valores numéricos válidos!", "Erro ao aplicar taxa", JOptionPane.ERROR_MESSAGE);
                }
                //Botão de exibir informações da conta
                atualizar.getjButtonExibirInfoContaAtualizarConta().addActionListener(l -> {
                    atualizar.getjTextAreaInfoConta().setText(contaPesquisada.exibirDadosConta());
                });
                //Botão de exibir total atualizado
                atualizar.getjButtonExibirTotalAtualizado().addActionListener(l -> {
                    atualizar.getjTextFieldTotalAtualizado().setText(Double.toString(atualizador.getTotalAtualizado()));
                });    
            }
        });
        atualizar.setVisible(true);
    }
     
}
