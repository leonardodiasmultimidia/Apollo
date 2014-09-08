/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

/**
 *
 * @author Leonardo Dias
 */
public class Faixa {
    private int id;
    private String url;
    private int duracao; //em segundos
    private String nome;
    private String autor;

    public Faixa(int id, String url, int duracao, String nome, String autor) {
        this.id = id;
        this.url = url;
        this.duracao = duracao;
        this.nome = nome;
        this.autor = autor;
    }
    
    public Faixa(){
        id = -1;
        url = "";
        duracao = -1;
        nome = "";
        autor = "";            
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
