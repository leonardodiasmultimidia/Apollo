/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Player;

import Entidades.Faixa;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;

/**
 *
 * @author Leo
 */
public class ListaDeReproducao {
    
    private static ArrayList<Faixa> lista = null;
    private static int faixaAtual;
    private static Random random;
    private static Reprodutor reprodutor;
    private static Thread threadReprodutor;
            
    public static void iniciaLista(){
        lista = new ArrayList<>();
        random = new Random(Calendar.getInstance().get(Calendar.MILLISECOND));
        faixaAtual = -1;
    }
    
    public static void insereFaixa(Faixa f){
        lista.add(f);
    }
    
    public static void removeFaixa(int pos){
        lista.remove(pos);
    }
    
    public static void limpaLista(){
        while(lista.size()!=0)
            lista.remove(0);
    }
    
    public static void insereOutraLista(ArrayList<Faixa> listaExterna){
        for(int cont = 0; cont < listaExterna.size(); cont++)
            lista.add(listaExterna.get(cont));
    }
    
    public static void proxMusica(){
        if(lista.size()==0)
            return;
        if(Configuracoes.getShuffle()){
            faixaAtual = random.nextInt(lista.size());
        }
        else faixaAtual++;
        if(faixaAtual>=lista.size()) faixaAtual = 0;
    }
    
    public static void antMusica(){
        if(lista.size()==0)
            return;
        if(Configuracoes.getShuffle()){
            faixaAtual = random.nextInt(lista.size());
        }
        else faixaAtual--;
        if(faixaAtual<0) faixaAtual = -1;
    }
    
    public static void tocar(){
        if(lista.size()!=0){
            if(faixaAtual==-1)
                proxMusica();
        }
        else return;
            reprodutor = new Reprodutor();
            reprodutor.setMusica(lista.get(faixaAtual).getUrl()+".mp3");
            reprodutor.play();
        System.out.println("Tocando agora: "+lista.get(faixaAtual).getNome() + " - "+ lista.get(faixaAtual).getAutor()+" from: "+lista.get(faixaAtual).getUrl());
    }
    
    public static void pausar(){
            System.out.println("pausando...");
            reprodutor.pause();
            System.out.println("foi");
    }
    
    public static void continuar(){
        System.out.println("continuando...");
        reprodutor.play();
        System.out.println("foi");
    }
    
}
