/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Player;

import Entidades.Faixa;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author Leo
 */
public class ListaDeReproducao {
    
    private static ArrayList<Faixa> lista = null;
    private static int faixaAtual = 0;
    private static Random random;
            
    public static void iniciaLista(){
        lista = new ArrayList<>();
        random = new Random(Calendar.getInstance().get(Calendar.MILLISECOND));
    }
    
    public static void insereFaixa(Faixa f){
        lista.add(f);
    }
    
    public static void removeFaixa(int pos){
        lista.remove(pos);
    }
    
    public static void insereOutraLista(ArrayList<Faixa> listaExterna){
        for(int cont = 0; cont < listaExterna.size(); cont++)
            lista.add(listaExterna.get(cont));
    }
    
    public static void proxMusica(){
        if(Configuracoes.getShuffle()){
            faixaAtual = random.nextInt(lista.size());
        }
        else faixaAtual++;
        if(faixaAtual>=lista.size()) faixaAtual = 0;
    }
    
    public static void antMusica(){
        if(Configuracoes.getShuffle()){
            faixaAtual = random.nextInt(lista.size());
        }
        else faixaAtual--;
        if(faixaAtual<0) faixaAtual = lista.size()-1;
    }
    
    public static void tocar(){
        System.out.println("Tocando agora: "+lista.get(faixaAtual).getNome() + " - "+ lista.get(faixaAtual).getAutor());
    }
    
}
