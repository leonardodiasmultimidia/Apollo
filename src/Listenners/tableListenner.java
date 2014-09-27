/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Listenners;

import Graphics.Window;
import Player.ListaDeReproducao;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Leonardo Dias
 */
public class tableListenner implements MouseListener{

    static int lastClick = -1;

    @Override
    public void mouseClicked(MouseEvent me) {
        if(lastClick==Window.tableListaReproducao.getSelectedRow())
            ListaDeReproducao.tocarAt(Window.tableListaReproducao.getSelectedRow());
        else
        lastClick = Window.tableListaReproducao.getSelectedRow();
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {

    }
}
