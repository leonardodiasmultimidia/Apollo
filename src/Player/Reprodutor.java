/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Player;

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
    public static int status;
    
    String path;
    Player player;
    Thread threadPlayer;
    
    public Reprodutor(){
        path = null;
        player = null;
        threadPlayer = null;
        status = -1;
    }
    
    public void setMusica(String path){
        stop();
        status = -1;
        this.path = path;
    }
    
    public void play(){
        status = PLAYING;
        player = new Player(path);
        threadPlayer = new Thread(player);
        threadPlayer.start();
    }
    
    public void resume(){
        status = PLAYING;
        if(player!=null){
            synchronized(player){
                player.notify();
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
    
}
