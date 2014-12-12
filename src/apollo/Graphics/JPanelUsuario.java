/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.Graphics;

import apollo.BD.Utilitarios;
import apollo.Entidades.Cobranca;
import apollo.Entidades.Usuario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Leonardo Dias
 */
public class JPanelUsuario extends JPanel{
    
    private int posY;
    private int posX;
    
    public JPanelUsuario(Usuario u){
        setLayout(null);
        setBackground(Color.WHITE);
        posY = 0;
        posX = 20;
    }
    
    public void addTexto(String s){
        reset();
        JLabel busca = (new JLabel(s));
        busca.setFont(new Font("Verdana",0,16));
        busca.setBounds(posX, posY, 509, 40);
        add(busca);
        posY+=40;
    }
    
    public void addCobranca(ArrayList<Cobranca> fatura){
        if(fatura!=null){
            if(fatura.isEmpty()){
                JLabel info = (new JLabel("Nenhum resultado encontrado"));
                info.setFont(new Font("Verdana",0,16));
                info.setBounds(posX, posY, 800, 40);
                add(info);
            }
            else{
                JLabel line1;
                JLabel line2;
                for (Cobranca cobranca : fatura) {
                    line1 = new JLabel(Utilitarios.getData(cobranca.getData()));
                    line2 = new JLabel(cobranca.getFaixa().getNome() + " - " + cobranca.getFaixa().getAutor() + "R$ " + cobranca.getFaixa().getValor());
                    line1.setBounds(posX+20, posY, 600, 20);
                    line2.setBounds(posX+40, posY+20, 600, 20);
                    add(line1);
                    add(line2);
                    posY+=40;
                }
            }
        }
    }
    
    private void reset(){
        posY = 0;
        posX = 20;
        this.removeAll();
    }
    
    private void update(){
        setPreferredSize(new Dimension(500,posY));
        validate();
        repaint();
    }
}
