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
public class Coletanea {
    
    private int id;
    private String nome;
    private ArrayList<Disco> coletanea;

    public Coletanea(int id, ArrayList<Disco> coletanea) {
        this.id = id;
        this.coletanea = coletanea;
    }

    public Coletanea() {
        id = -1;
        coletanea = null;
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
    

    public ArrayList<Disco> getColetanea() {
        return coletanea;
    }

    public void setColetanea(ArrayList<Disco> coletanea) {
        this.coletanea = coletanea;
    }
    
    public int getNumDiscos(){
        return coletanea.size();
    }
    
    public void addDisco(Disco disco){
        coletanea.add(disco);
    }
    
    public void removeDisco(Disco disco){
        coletanea.remove(disco);
    }
    
    public void removeDiscoByPos(int pos){
        coletanea.remove(pos);
    }
    
    
}
