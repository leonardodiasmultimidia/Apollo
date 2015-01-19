/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.Player;

/**
 *
 * @author Leo
 */
public class Configuracoes {
    
    public static final int NONE = 0;
    public static final int REPETIR_TUDO = 1;
    public static final int REPETIR_UMA = 2;
    private static boolean shuffle = false;
    private static int repeatState = 0;
    
    public static void changeRepeatState(int repeatState){
        Configuracoes.repeatState = repeatState;
    }
    
    public static void changeShuffle(){
        shuffle = !shuffle;
    }
    
    public static void changeShuffle(boolean novoShuffle){
        shuffle = novoShuffle;
    }
    
    public static boolean getShuffle(){
        return shuffle;
    }
    
    public static int getRepeatState(){
        return repeatState;
    }
    
}
