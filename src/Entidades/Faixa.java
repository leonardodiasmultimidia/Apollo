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
    int id;
    String url;
    int duracao; //em segundos
    String nome;
    String autor;
    String disco;
    int discoId;

    public Faixa(int id, String url, int duracao, String nome, String autor, String disco, int discoId) {
        this.id = id;
        this.url = url;
        this.duracao = duracao;
        this.nome = nome;
        this.autor = autor;
        this.disco = disco;
        this.discoId = discoId;
    }
    
    public Faixa(){
        
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

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public int getDiscoId() {
        return discoId;
    }

    public void setDiscoId(int discoId) {
        this.discoId = discoId;
    }
}
