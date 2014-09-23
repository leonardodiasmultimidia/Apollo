/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;

/**
 *
 * @author Leonardo Dias
 */
public class Reprodutor {
    
    String path;
    Player player;
    Thread threadPlayer;
    int frameAtual;
    
    public Reprodutor(){
        path = null;
        player = null;
        threadPlayer = null;
        frameAtual = -1;
    }
    
    public void setMusica(String path){
            this.path = path;
    }
    
    public void play(){
        try {
            player = new Player(new FileInputStream(path), frameAtual);
        } catch (JavaLayerException ex) {
            Logger.getLogger(Reprodutor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reprodutor.class.getName()).log(Level.SEVERE, null, ex);
        }
        threadPlayer = new Thread(player);
        threadPlayer.start();
    }
    
    public void pause(){
        player.pause();
    }
    
}
