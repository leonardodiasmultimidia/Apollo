/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.Entidades;

import apollo.BD.BD;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Leonardo Dias
 */
public class Disco implements Serializable{
    
    private int id;
    private String nome;
    private int duracao;
    private ArrayList<String> disco;
    private float valor;
    
    public Disco(int id, String nome, ArrayList<String> disco){
        this.id = id;
        this.disco = disco;
        this.nome = nome;
    }
    
    public Disco(){
        id = -1;
        disco = new ArrayList<>();
        nome = "";
        duracao = 0;
        valor = 0.0f;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    

    public ArrayList<String> getDisco() {
        return disco;
    }

    public void setDisco(ArrayList<String> disco) {
        this.disco = disco;
    }
    
    public int getNumeroDeFaixas(){
        return disco.size();
    }
    
    public void addFaixa(int idFaixa){
        disco.add(idFaixa+"");
    }
    
    public void removeFaixa(int idFaixa){
        disco.remove(idFaixa+"");
    }
    
    public void removeFaixaByPos(int pos){
        disco.remove(pos);
    }
    
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    public void calculaValor(){
        this.valor = 0;
        for(String faixa:disco)
            valor += BD.getFaixaById(Integer.parseInt(faixa)).getValor();
    }
    
    public void calculaDuracao(){
        this.duracao = 0;
        for(String faixa:disco)
            duracao += BD.getFaixaById(Integer.parseInt(faixa)).getDuracao();
    }
}
