/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicação;

/**
 * Criar uma conexão com o banco
 * Criar a tabela caso ela não exista
 * 
 * @author weliton.andrade
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBD {

    private static final String URL = "jdbc:sqlite:clientes.db";
    
    public static Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            criarTabela(conn);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void criarTabela(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS clientes (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "nome TEXT NOT NULL," +
                     "endereco TEXT NOT NULL," +
                     "cep TEXT NOT NULL," +
                     "telefone TEXT NOT NULL," +
                     "email TEXT NOT NULL," +
                     "classificacao TEXT NOT NULL," +
                     "empreendimento TEXT NOT NULL," +
                     "nome_empresa TEXT NOT NULL" +
                     ");";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


