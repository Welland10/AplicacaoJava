/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicação;

/**
 * classe DAO para manipulação no banco de dados sqlite
 * @author weliton.andrade
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, endereco, cep, telefone, email, classificacao, empreendimento, nome_empresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getEndereco());
            pstmt.setString(3, cliente.getCep());
            pstmt.setString(4, cliente.getTelefone());
            pstmt.setString(5, cliente.getEmail());
            pstmt.setString(6, cliente.getClassificacao());
            pstmt.setString(7, cliente.getEmpreendimento());
            pstmt.setString(8, cliente.getNomeEmpresa());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> buscarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setCep(rs.getString("cep"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setClassificacao(rs.getString("classificacao"));
                cliente.setEmpreendimento(rs.getString("empreendimento"));
                cliente.setNomeEmpresa(rs.getString("nome_empresa"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public Cliente buscarPorId(int id) {
        Cliente cliente = null;
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setEndereco(rs.getString("endereco"));
                    cliente.setCep(rs.getString("cep"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setClassificacao(rs.getString("classificacao"));
                    cliente.setEmpreendimento(rs.getString("empreendimento"));
                    cliente.setNomeEmpresa(rs.getString("nome_empresa"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE nome LIKE ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + nome + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setEndereco(rs.getString("endereco"));
                    cliente.setCep(rs.getString("cep"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setClassificacao(rs.getString("classificacao"));
                    cliente.setEmpreendimento(rs.getString("empreendimento"));
                    cliente.setNomeEmpresa(rs.getString("nome_empresa"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public void alterar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, endereco = ?, cep = ?, telefone = ?, email = ?, classificacao = ?, empreendimento = ?, nome_empresa = ? WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getEndereco());
            pstmt.setString(3, cliente.getCep());
            pstmt.setString(4, cliente.getTelefone());
            pstmt.setString(5, cliente.getEmail());
            pstmt.setString(6, cliente.getClassificacao());
            pstmt.setString(7, cliente.getEmpreendimento());
            pstmt.setString(8, cliente.getNomeEmpresa());
            pstmt.setInt(9, cliente.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


