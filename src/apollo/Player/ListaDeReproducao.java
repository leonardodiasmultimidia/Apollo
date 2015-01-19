/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.Player;

import apollo.Entidades.Faixa;
import apollo.Graphics.IUPrincipal;
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
        model = (DefaultTableModel) IUPrincipal.jTableListaReproducao.getModel();
        reprodutor = new Reprodutor();
    }
    
    public static void insereFaixa(Faixa f){
        model.addRow(new Object[]{f.getNome()+"-"+f.getAutor(),f.getDuracao()/60+":"+(new DecimalFormat("00")).format(f.getDuracao()%60)});
        lista.add(f);
    }
    
    public static void removeFaixa(int pos){
        if(faixaAtual == pos)
            parar();
        lista.remove(pos);
        model.removeRow(pos);
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
        if(Configuracoes.getRepeatState()  != Configuracoes.REPETIR_UMA){
            if(Configuracoes.getShuffle())
                faixaAtual = random.nextInt(lista.size());
            else 
            {   
                faixaAtual++;
                if(faixaAtual>=lista.size() && Configuracoes.getRepeatState()==Configuracoes.REPETIR_TUDO) 
                    faixaAtual = 0;
                else
                    parar();
                return;
            }
        }
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
        reprodutor.setMusica(lista.get(faixaAtual).getId(), lista.get(faixaAtual).getUrl()+".ogg", lista.get(faixaAtual).getDuracao());
        reprodutor.play();
        IUPrincipal.setLabelTrackName(lista.get(faixaAtual).getNome());
        IUPrincipal.setLabelTrackAutor(lista.get(faixaAtual).getAutor());
        IUPrincipal.setLabelTrackTime("00:00/"+(int)lista.get(faixaAtual).getDuracao()/60+":"+(lista.get(faixaAtual).getDuracao()%60<10?"0"+lista.get(faixaAtual).getDuracao()%60:lista.get(faixaAtual).getDuracao()%60));
        IUPrincipal.setJProgressBarLenght(lista.get(faixaAtual).getDuracao());
        IUPrincipal.setBtPause();
    }
    
    public static void continuar(){
        reprodutor.resume();
        IUPrincipal.setBtPause();
        
    }
    
    public static void pause(){
        reprodutor.pause();
        IUPrincipal.setBtPlay();
    }
    
    public static void tocarAt(int pos){ //NAO IMPLEMENTADO
        faixaAtual = pos;
        tocar();
    }
    
    public static void parar(){
        reprodutor.stop();
        IUPrincipal.setLabelTrackName("- -");
        IUPrincipal.setLabelTrackAutor("- -");
        IUPrincipal.setLabelTrackTime("00:00/-- --");
        IUPrincipal.setJProgressBarZero();
        IUPrincipal.setBtPlay();
    }
    
}
