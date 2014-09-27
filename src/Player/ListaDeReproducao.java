/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Player;

import Entidades.Faixa;
import Graphics.Window;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Leo
 */
public class ListaDeReproducao {
    
    private static ArrayList<Faixa> lista = null;
    private static int faixaAtual;
    private static Random random;
    private static Reprodutor reprodutor;
    private static DefaultTableModel model;
            
    public static void iniciaLista(){
        lista = new ArrayList<>();
        random = new Random(Calendar.getInstance().get(Calendar.MILLISECOND));
        faixaAtual = -1;
        model = (DefaultTableModel) Window.tableListaReproducao.getModel();
        reprodutor = new Reprodutor();
    }
    
    public static void insereFaixa(Faixa f){
        model.addRow(new Object[]{f.getNome()+"-"+f.getAutor(),f.getDuracao()/60+":"+(new DecimalFormat("00")).format(f.getDuracao()%60)});
        lista.add(f);
    }
    
    public static void removeFaixa(int pos){
        lista.remove(pos);
    }
    
    public static void limpaLista(){
        lista.clear();
    }
    
    public static void insereOutraLista(ArrayList<Faixa> listaExterna){
        for(int cont = 0; cont < listaExterna.size(); cont++)
            insereFaixa(listaExterna.get(cont));
    }
    
    public static void proxMusica(){
        if(lista.isEmpty())
            return;
        if(Configuracoes.getShuffle()){
            faixaAtual = random.nextInt(lista.size());
        }
        else faixaAtual++;
        if(faixaAtual>=lista.size()) faixaAtual = 0;
        tocar();
    }
    
    public static void antMusica(){
        if(lista.isEmpty())
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
        if(!lista.isEmpty()){
            if(faixaAtual==-1){
                proxMusica();
                return;
            }            
        }
        
        else return;
        reprodutor.setMusica(lista.get(faixaAtual).getUrl()+".ogg");
        reprodutor.play();
        System.out.println("Tocando agora: "+lista.get(faixaAtual).getNome() + " - "+ lista.get(faixaAtual).getAutor()+" from: "+lista.get(faixaAtual).getUrl());
    }
    
    public static void continuar(){
        System.out.println("continuando");
        reprodutor.resume();
    }
    
    public static void pause(){
        System.out.println("pausando...");
        reprodutor.pause();
    }
    
    public static void tocarAt(int pos){ //NAO IMPLEMENTADO
        faixaAtual = pos;
        tocar();
    }
    
    public static void parar(){
        System.out.println("parando...");
        reprodutor.stop();
    }
    
}
