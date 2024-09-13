/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicação;

/**
 *
 * @author weliton.andrade
 * Criar uma classe cliente com todos os atributos de cadastro
 * Criar os getters e os setters
 * Atributos: nome, endereço, cep,telefone,email, classificação, empreendimento, nome da empresa
 */
public class Cliente {

    private int id;
    private String nome;
    private String endereco;
    private String cep;
    private String telefone;
    private String email;
    private String classificacao;
    private String empreendimento;
    private String nomeEmpresa;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getClassificacao() { return classificacao; }
    public void setClassificacao(String classificacao) { this.classificacao = classificacao; }
    public String getEmpreendimento() { return empreendimento; }
    public void setEmpreendimento(String empreendimento) { this.empreendimento = empreendimento; }
    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }
}

