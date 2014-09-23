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
    private static int faixaAtual;
    private static Random random;
    private static Reprodutor reprodutor;
    private static Thread threadReprodutor;
    private static boolean playing;
            
    public static void iniciaLista(){
        lista = new ArrayList<>();
        random = new Random(Calendar.getInstance().get(Calendar.MILLISECOND));
        faixaAtual = -1;
        playing = false;
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
        tocar();
    }
    
    public static void antMusica(){
        if(lista.size()==0)
            return;
        if(Configuracoes.getShuffle()){
            int next;
            do{
            next = random.nextInt(lista.size());
            }while(faixaAtual==next);
            faixaAtual = next;
        }
        else faixaAtual--;
        if(faixaAtual<0) faixaAtual = lista.size()-1;
        tocar();
    }
    
    public static void tocar(){
        if(lista.size()!=0){
            if(faixaAtual==-1){
                proxMusica();
                return;
            }            
        }
        else return;
        if(playing)
            parar();
        reprodutor = new Reprodutor();
        reprodutor.setMusica(lista.get(faixaAtual).getUrl()+".mp3");
        reprodutor.play();
        System.out.println("Tocando agora: "+lista.get(faixaAtual).getNome() + " - "+ lista.get(faixaAtual).getAutor()+" from: "+lista.get(faixaAtual).getUrl());
        playing = true;
    }
    
    public static void parar(){
            System.out.println("parando...");
            reprodutor.pause();
            playing = false;
    }
    
}
