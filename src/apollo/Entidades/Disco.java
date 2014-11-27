/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.Entidades;

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
    
    public Disco(int id, ArrayList<String> disco, float valor){
        this.id = id;
        this.disco = disco;
        this.valor = valor;
    }
    
    public Disco(){
        id = -1;
        disco = null;
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
}
