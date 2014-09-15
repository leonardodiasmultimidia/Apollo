/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.util.ArrayList;

/**
 *
 * @author Leonardo Dias
 */
public class Disco {
    
    private int id;
    private String nome;
    private int duracao;
    private ArrayList<Faixa> disco;
    
    public Disco(int id, ArrayList<Faixa> disco){
        this.id = id;
        this.disco = disco;
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
    

    public ArrayList<Faixa> getDisco() {
        return disco;
    }

    public void setDisco(ArrayList<Faixa> disco) {
        this.disco = disco;
    }
    
    public int getNumeroDeFaixas(){
        return disco.size();
    }
    
    public void addFaixa(Faixa faixa){
        disco.add(faixa);
    }
    
    public void removeFaixa(Faixa faixa){
        disco.remove(faixa);
    }
    
    public void removeFaixaByPos(int pos){
        disco.remove(pos);
    }
}
