/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.Listenners;

import apollo.Graphics.IUPrincipal;
import apollo.Player.ListaDeReproducao;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Leonardo Dias
 */
public class tableListenner implements MouseListener, KeyListener{

    static int lastClick = -1;

    @Override
    public void mouseClicked(MouseEvent me) {
        if(lastClick==IUPrincipal.jTableListaReproducao.getSelectedRow())
            ListaDeReproducao.tocarAt(lastClick);
        else
        lastClick = IUPrincipal.jTableListaReproducao.getSelectedRow();
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

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DELETE)
            ListaDeReproducao.removeFaixa(IUPrincipal.jTableListaReproducao.getSelectedRow());
        else if(e.getKeyCode() == KeyEvent.VK_ENTER)
            ListaDeReproducao.tocarAt(IUPrincipal.jTableListaReproducao.getSelectedRow());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
