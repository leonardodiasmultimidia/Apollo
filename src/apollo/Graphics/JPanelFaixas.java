/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.Graphics;

import apollo.Entidades.Faixa;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 *
 * @author Leonardo Dias
 */
public class JPanelFaixas extends JPanel{
    
    private int posY;
    private int posX;
    private ArrayList<JCBox> jCBGroup;
    
    public JPanelFaixas(){
        setLayout(null);
        setBackground(Color.WHITE);
        posY = 0;
        posX = 20;
        jCBGroup = new ArrayList<JCBox>();
    }
    
    
    public void addFaixas(ArrayList<Faixa> faixas){
        JCBox jCBox;
        for(int i = 0; i < faixas.size();i++){
            jCBox = new JCBox();
            jCBox.setBounds(posX, posY,800,20);
            jCBox.setText(faixas.get(i).getNome()+" - "+faixas.get(i).getAutor());
            jCBox.add(faixas.get(i));
            jCBGroup.add(jCBox);
            add(jCBox);
            posY+=20;
        }
    }
    
    /*public void addDisco(ArrayList<Disco> discos){
        JCBox jCBox;
        for(int i = 0; i < discos.size();i++){
            jCBox = new JCBox();
            jCBox.setBounds(posX, posY+i*20,800,20);
            jCBox.setText(faixas.get(i).getNome()+" - "+faixas.get(i).getAutor());
            jCBox.add(faixas.get(i));
            jCBGroup.add(jCBox);
            add(jCBox);
            posY+=discos.get(i).getNumeroDeFaixas()*20;
        }
    }*/
    
    public ArrayList<Faixa> getSelected(){
        ArrayList<Faixa> temp = new ArrayList<Faixa>();
        for(int i = 0; i < jCBGroup.size();i++){
            if(jCBGroup.get(i).isSelected()){
                if(jCBGroup.get(i).getElement() instanceof Faixa)
                    temp.add((Faixa)jCBGroup.get(i).getElement());
            }
        }
        return temp;
    }
    
    private class JCBox extends JCheckBox{

        Object element;
        
        public void add(Object obj){
            element = obj;
        }
       
        public Object getElement(){
            return element;
        }
    }
}
