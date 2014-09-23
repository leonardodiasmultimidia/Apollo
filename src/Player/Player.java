/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Player;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;

/**
 *
 * @author Leonardo Dias
 */
public class Player extends javazoom.jl.player.advanced.AdvancedPlayer implements Runnable{
    
    private int frameAtual;
    
    public Player(FileInputStream FIS, int frameAtual) throws JavaLayerException{
        super(FIS);
        this.frameAtual = frameAtual;
    }
    
    public void resume(){
        try{
            if(frameAtual != -1)
                play(frameAtual,10000);
            else
                play(0,1000);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int pause(){
        
        close();
    }

    @Override
    public void run() {
        resume();
    }
    
}
