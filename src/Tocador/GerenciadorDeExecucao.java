/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tocador;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo Dias
 */
public class GerenciadorDeExecucao{
    
    Thread threadPlayer;
    Player player;
    
    public GerenciadorDeExecucao() throws FileNotFoundException, JavaLayerException{
        String path = "C:\\Users\\Leo Dias\\Desktop\\My Shared Folder\\lorde - royals.mp3";
        FileInputStream fis = new FileInputStream(path);
        BufferedInputStream bis = new BufferedInputStream(fis);
        player = new Player(bis);
        threadPlayer = new Thread(player);
    }
    
    public void abrirArquivo(){
        threadPlayer.start();
    }
    
    public void continuar(){
        synchronized(this){
            synchronized(player){
                synchronized(threadPlayer){
               player.pause = false;
               threadPlayer.notify();
                }       
            }
        }
    }
    
    public void pausar() throws InterruptedException{
        synchronized(this){
            synchronized(player){
                synchronized(threadPlayer){
                 System.out.println("Chamou");
                player.pause = true;
                threadPlayer.wait();   
                }
        }
        }
    }
}
