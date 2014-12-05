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
public class Coletanea implements Serializable{
    
    private int id;
    private String nome;
    private ArrayList<String> coletanea;
    private float valor;
    private int duracao;

    public Coletanea(int id, String nome, ArrayList<String> coletanea) {
        this.id = id;
        this.coletanea = coletanea;
        this.valor = 0;
        this.duracao = 0;
        this.nome = nome;
    }

    public Coletanea() {
        id = -1;
        coletanea = null;
        valor = 0;
        duracao = 0;
        nome = "";
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
    

    public ArrayList<String> getColetanea() {
        return coletanea;
    }

    public void setColetanea(ArrayList<String> coletanea) {
        this.coletanea = coletanea;
    }
    
    public int getNumDiscos(){
        return coletanea.size();
    }
    
    public void addDisco(String disco){
        coletanea.add(disco);
    }
    
    public void removeDisco(String disco){
        coletanea.remove(disco);
    }
    
    public void removeDiscoByPos(int pos){
        coletanea.remove(pos);
    }
    
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    
    public void calculaValor(){
        this.valor = 0;
        for(String disco:coletanea)
            for(String faixa:BD.getDiscoById(Integer.parseInt(disco)).getDisco())
                valor += BD.getFaixaById(Integer.parseInt(faixa)).getValor();
    }
    
    public void calculaDuracao(){
        this.duracao = 0;
        for(String disco:coletanea)
            for(String faixa:BD.getDiscoById(Integer.parseInt(disco)).getDisco())
                duracao += BD.getFaixaById(Integer.parseInt(faixa)).getDuracao();
    }
}
