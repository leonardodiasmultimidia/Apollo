/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.Player;

import apollo.BD.BD;
import apollo.BD.Data;
import apollo.Entidades.Cobranca;
import apollo.Graphics.IUPrincipal;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo Dias
 */
public class Reprodutor {
    
    public static final int PLAYING = 1;
    public static final int STOPPED = -1;
    public static final int PAUSED = 0;
    private static int status;
    private static int time;
    private static int timeLenght;
    private static int faixaId;
    
    String path;
    Player player;
    progressBarThread pBT;
    Thread threadPlayer;
    Thread threadProgressBar;
    
    public Reprodutor(){
        path = null;
        player = null;
        threadPlayer = null;
        threadProgressBar = null;
        status = -1;
        time = 0;
    }
    
    public void setMusica(int faixaId, String path, int timeLenght){
        this.faixaId = faixaId;
        stop();
        status = -1;
        this.path = path;
        Reprodutor.timeLenght = timeLenght;
    }
    
    public void play(){
        status = PLAYING;
        player = new Player(path);
        threadPlayer = new Thread(player);
        threadPlayer.start();
        pBT = new progressBarThread();
        threadProgressBar = new Thread(pBT);
        threadProgressBar.start();
        BD.adicionaCobranca(new Cobranca(-1, faixaId, IUPrincipal.getSessao().getId(), Calendar.getInstance()));
        Data.salvarCobrancas();
        Data.salvarConfiguracoes();
    }
    
    public void resume(){
        status = PLAYING;
        if(player!=null){
            synchronized(player){
                player.notify();
            }
            synchronized(pBT){
                pBT.notify();
            }
        }
        
    }
    
    public void pause(){
        status = PAUSED;
    }  
    
    public void stop(){
        status = STOPPED;
        if(threadPlayer!=null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Reprodutor.class.getName()).log(Level.SEVERE, null, ex);
            }
            threadPlayer.interrupt();
            threadPlayer = null;
        }
    }
    
    public static int getStatus(){
        return status;
    }
    
    public static int getTime(){
        return time;
    }
    
    public static void updateTime(){
        Reprodutor.time++;
    }
    public static void setTime(int time){
        Reprodutor.time = time;
    }
    
    public static int getTimeLenght(){
        return timeLenght;
    }
    
    class progressBarThread implements Runnable{

        @Override
        public void run() {
            while(true){
                if(status == PAUSED){
                    try {
                        synchronized(this){
                            wait();
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(status == STOPPED){
                    Reprodutor.setTime(0);
                    IUPrincipal.setJProgressBarLenght(0);
                    break;
                }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Reprodutor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Reprodutor.updateTime();
                    IUPrincipal.updateJProgressBar();
                    IUPrincipal.setLabelTrackTime((Reprodutor.getTime()/60<10?"0"+Reprodutor.getTime()/60:Reprodutor.getTime()/60)
                            +":"+
                            (Reprodutor.getTime()%60<10?"0"+Reprodutor.getTime()%60:Reprodutor.getTime()%60)
                            +"/"+IUPrincipal.getLabelTrackTime().split("/")[1]);
            }
        }
    }
}
