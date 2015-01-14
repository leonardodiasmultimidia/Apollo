/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.Player;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author Leonardo Dias
 */
public class Player implements Runnable{
    private File file = null;
    
    public Player(String file){
        this.file = new File(file);
        //this.file = new File("D:\\Private\\ApolloNB\\lib\\01.ogg");
    }
    
    public void play()
    {
      try
      {
        // Get AudioInputStream from given file.	
        AudioInputStream in= AudioSystem.getAudioInputStream(file);
        //AudioInputStream in= AudioSystem.getAudioInputStream(new URL("https://docs.google.com/uc?authuser=0&id=0B6fZgwGy6zsNbENVLVRrcy1FM1k&export=download"));
        AudioInputStream din;
        if (in != null)
        {
            AudioFormat baseFormat = in.getFormat();
            AudioFormat  decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false);
             // Get AudioInputStream that will be decoded by underlying VorbisSPI
            din = AudioSystem.getAudioInputStream(decodedFormat, in);
            // Play now !
            rawplay(decodedFormat, din);
            in.close();		
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }

    private void rawplay(AudioFormat targetFormat, 
                                       AudioInputStream din) throws IOException, LineUnavailableException
    {
       byte[] data = new byte[4096];
      SourceDataLine line = getLine(targetFormat);		
      if (line != null)
      {
         // Start
        line.start();
         int nBytesRead = 0, nBytesWritten = 0;
         while (nBytesRead != -1)
        {
            if(Reprodutor.getStatus()== Reprodutor.PAUSED){
                try {
                    synchronized(this){
                        wait();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(Reprodutor.getStatus()== Reprodutor.STOPPED)
                break;
            nBytesRead = din.read(data, 0, data.length);
            if (nBytesRead != -1){ 
                nBytesWritten = line.write(data, 0, nBytesRead);
            }
        }
        line.drain();
        line.stop();
        line.close();
        din.close();
        if(Reprodutor.getStatus()!=Reprodutor.STOPPED){
            if(Configuracoes.getRepeatState() == Configuracoes.REPETIR_UMA)
                ListaDeReproducao.tocar();
            else{
                ListaDeReproducao.proxMusica();
            }
        }
      }	

}
  private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException
    {
      SourceDataLine res = null;
      DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
      res = (SourceDataLine) AudioSystem.getLine(info);
      res.open(audioFormat);
      return res;
    }

    @Override
    public void run() {
        play();
    }
    
}
