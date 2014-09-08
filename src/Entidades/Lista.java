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
public class Lista {
    
    private int id;
    private ArrayList<Faixa> lista;
    
    public Lista(int id, ArrayList<Faixa> lista){
        this.id = id;
        this.lista = lista;
    }
    
    public Lista(){
        id = -1;
        lista = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Faixa> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Faixa> lista) {
        this.lista = lista;
    }
    
    public int getNumeroDeFaixas(){
        return lista.size();
    }
    
    public void addFaixa(Faixa faixa){
        lista.add(faixa);
    }
    
    public void removeFaixa(Faixa faixa){
        lista.remove(faixa);
    }
    
    public void removeFaixaByPos(int pos){
        lista.remove(pos);
    }
    
    
}
