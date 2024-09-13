/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicação;

/**
 * Usar a biblioteca Swing para criar a interface grafica
 * incluir os campos para inserir as informações dos clientes
 * Botoes para as operações CRUD
 * botão para exportar os dados em csv
 * usar o DAO para integrar ao banco de dados
 * java.awt.para arrumar os layout da aplicação
 * java.awt.event.ActionEvent para reconhecer ações nos botões como click
 * java.io.FileWriter para gravar os caracteres nos arquivos
 * java.io.IOException para ajudar a tratar possiveis erros de entrada e saida
 * java.util.List para ajudar na manipulação das listas de clientes
 * 
 * @author weliton.andrade
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class InterfaceGrafica {

    private final JTextField tfNome, tfEndereco, tfCep, tfTelefone, tfEmail, tfClassificacao, tfEmpreendimento, tfNomeEmpresa;
    private final JButton btnSalvar, btnPesquisar, btnAlterar, btnExcluir, btnExportar;
    private JTextArea taResultados;
    private ClienteDAO clienteDAO;
    private Cliente clienteAtual; // Cliente atual para edição

    public InterfaceGrafica() {
        clienteDAO = new ClienteDAO();

        JFrame frame = new JFrame("Cadastro de Clientes - Br Bliss Oficial");
        frame.setSize(0x384, 0x1c2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        panelCampos.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        tfNome = new JTextField(30); 
        panelCampos.add(tfNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        panelCampos.add(new JLabel("Endereço:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        tfEndereco = new JTextField(30); 
        panelCampos.add(tfEndereco, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        panelCampos.add(new JLabel("CEP:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        tfCep = new JTextField(15); 
        panelCampos.add(tfCep, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        panelCampos.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        tfTelefone = new JTextField(15); 
        panelCampos.add(tfTelefone, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
        panelCampos.add(new JLabel("E-mail:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        tfEmail = new JTextField(30); 
        panelCampos.add(tfEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 1;
        panelCampos.add(new JLabel("Classificação:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        tfClassificacao = new JTextField(20); 
        panelCampos.add(tfClassificacao, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 1;
        panelCampos.add(new JLabel("Empreendimento:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        tfEmpreendimento = new JTextField(25); 
        panelCampos.add(tfEmpreendimento, gbc);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 1;
        panelCampos.add(new JLabel("Nome da Empresa:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        tfNomeEmpresa = new JTextField(30); 
        panelCampos.add(tfNomeEmpresa, gbc);

        JPanel panelBotoes = new JPanel();
        btnSalvar = new JButton("Salvar");
        btnPesquisar = new JButton("Pesquisar");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir");
        btnExportar = new JButton("Exportar");

        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnPesquisar);
        panelBotoes.add(btnAlterar);
        panelBotoes.add(btnExcluir);
        panelBotoes.add(btnExportar);

        taResultados = new JTextArea(20,40);
        taResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taResultados);

        frame.add(panelCampos, BorderLayout.CENTER);
        frame.add(panelBotoes, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.EAST);

        // Action Listeners
        btnSalvar.addActionListener((ActionEvent e) -> {
            if (clienteAtual == null) {
                Cliente cliente = new Cliente();
                cliente.setNome(tfNome.getText());
                cliente.setEndereco(tfEndereco.getText());
                cliente.setCep(tfCep.getText());
                cliente.setTelefone(tfTelefone.getText());
                cliente.setEmail(tfEmail.getText());
                cliente.setClassificacao(tfClassificacao.getText());
                cliente.setEmpreendimento(tfEmpreendimento.getText());
                cliente.setNomeEmpresa(tfNomeEmpresa.getText());
                
                clienteDAO.salvar(cliente);
                JOptionPane.showMessageDialog(frame, "Cliente salvo com sucesso!");
            } else {
                clienteAtual.setNome(tfNome.getText());
                clienteAtual.setEndereco(tfEndereco.getText());
                clienteAtual.setCep(tfCep.getText());
                clienteAtual.setTelefone(tfTelefone.getText());
                clienteAtual.setEmail(tfEmail.getText());
                clienteAtual.setClassificacao(tfClassificacao.getText());
                clienteAtual.setEmpreendimento(tfEmpreendimento.getText());
                clienteAtual.setNomeEmpresa(tfNomeEmpresa.getText());
                
                clienteDAO.alterar(clienteAtual);
                JOptionPane.showMessageDialog(frame, "Cliente alterado com sucesso!");
                clienteAtual = null;
            }
            
            limparCampos();
        });

        btnPesquisar.addActionListener((ActionEvent e) -> {
            String nome = JOptionPane.showInputDialog(frame, "Digite o nome do cliente:");
            List<Cliente> clientes = clienteDAO.buscarPorNome(nome);
            taResultados.setText("");
            for (Cliente cliente : clientes) {
                taResultados.append(cliente.getId() + ": " + cliente.getNome() + "\n");
            }
        });

        btnAlterar.addActionListener((ActionEvent e) -> {
            int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Digite o ID do cliente a ser alterado:"));
            clienteAtual = clienteDAO.buscarPorId(id);
            
            if (clienteAtual != null) {
                tfNome.setText(clienteAtual.getNome());
                tfEndereco.setText(clienteAtual.getEndereco());
                tfCep.setText(clienteAtual.getCep());
                tfTelefone.setText(clienteAtual.getTelefone());
                tfEmail.setText(clienteAtual.getEmail());
                tfClassificacao.setText(clienteAtual.getClassificacao());
                tfEmpreendimento.setText(clienteAtual.getEmpreendimento());
                tfNomeEmpresa.setText(clienteAtual.getNomeEmpresa());
            } else {
                JOptionPane.showMessageDialog(frame, "Cliente não encontrado!");
            }
        });

        btnExcluir.addActionListener((ActionEvent e) -> {
            int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Digite o ID do cliente a ser excluído:"));
            clienteDAO.excluir(id);
            JOptionPane.showMessageDialog(frame, "Cliente excluído com sucesso!");
            limparCampos();
        });

        btnExportar.addActionListener((ActionEvent e) -> {
            exportarParaCSV();
            JOptionPane.showMessageDialog(frame, "Dados exportados para CSV com sucesso!");
        });

        frame.setVisible(true);
    }

    private void limparCampos() {
        tfNome.setText("");
        tfEndereco.setText("");
        tfCep.setText("");
        tfTelefone.setText("");
        tfEmail.setText("");
        tfClassificacao.setText("");
        tfEmpreendimento.setText("");
        tfNomeEmpresa.setText("");
    }

    private void exportarParaCSV() {
        List<Cliente> clientes = clienteDAO.buscarTodos();
        try (FileWriter fw = new FileWriter(System.getProperty("user.home") + "/Downloads/clientes.csv");
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println("ID,Nome,Endereco,CEP,Telefone,Email,Classificacao,Empreendimento,Nome da Empresa");
            for (Cliente c : clientes) {
                pw.printf("%d,%s,%s,%s,%s,%s,%s,%s,%s%n",
                          c.getId(), c.getNome(), c.getEndereco(), c.getCep(),
                          c.getTelefone(), c.getEmail(), c.getClassificacao(),
                          c.getEmpreendimento(), c.getNomeEmpresa());
            }
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        ConfiguradorSistema.main(args); // Configura o sistema

        SwingUtilities.invokeLater(() -> new InterfaceGrafica());
    }
}
