/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.Graphics;

import apollo.BD.BD;
import apollo.Entidades.Coletanea;
import apollo.Entidades.Disco;
import apollo.Entidades.Faixa;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
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
    
    public void addTexto(String s){
        reset();
        JLabel busca = (new JLabel(s));
        busca.setFont(new Font("Verdana",0,16));
        busca.setBounds(posX, posY, 800, 40);
        add(busca);
        posY+=40;
    }
    
    public void addFaixas(ArrayList<Faixa> faixas){
        JCBox jCBox;
        if(faixas.size()==0){
            JLabel info = (new JLabel("Nenhum resultado encontrado"));
            info.setFont(new Font("Verdana",0,16));
            info.setBounds(posX, posY, 800, 40);
            add(info);
        }
        else 
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
    
    public void addDiscos(ArrayList<Disco> discos){
        JCBox jCBox;
        if(discos.size()==0){
            JLabel info = (new JLabel("Nenhum resultado encontrado"));
            info.setFont(new Font("Verdana",0,16));
            info.setBounds(posX, posY, 800, 40);
            add(info);
        }
        else{
            for(int i = 0; i < discos.size();i++){
                jCBox = new JCBox();
                jCBox.setBounds(posX, posY,800,20);
                jCBox.setText(discos.get(i).getNome());
                jCBox.add(discos.get(i));
                jCBGroup.add(jCBox);
                posY+=20;
                JLabel labelFaixa;
                Faixa faixa;
                add(jCBox);
                for(String faixaId:discos.get(i).getDisco()){
                    faixa = BD.getFaixaById(Integer.parseInt(faixaId));
                    labelFaixa = new JLabel(faixa.getNome()+" - "+faixa.getAutor());
                    labelFaixa.setBounds(posX+20, posY, 780, 20);
                    add(labelFaixa);
                    posY+=20;
                }
                
            }
        }
    }
    
    public void addColetaneas(ArrayList<Coletanea> coletaneas){
        JCBox jCBox;
        if(coletaneas.size()==0){
            JLabel info = (new JLabel("Nenhum resultado encontrado"));
            info.setFont(new Font("Verdana",0,16));
            info.setBounds(posX, posY, 800, 40);
            add(info);
        }
        else{
            for(int i = 0; i < coletaneas.size();i++){
                jCBox = new JCBox();
                jCBox.setBounds(posX, posY,800,20);
                jCBox.setText(coletaneas.get(i).getNome());
                jCBox.add(coletaneas.get(i));
                jCBGroup.add(jCBox);
                posY+=20;
                JLabel labelDisco;
                Disco disco;
                add(jCBox);
                for(String discoId:coletaneas.get(i).getColetanea()){
                    labelDisco = new JLabel(BD.getDiscoById(Integer.parseInt(discoId)).getNome());
                    labelDisco.setBounds(posX+20, posY,800,20);
                    labelDisco.setText(BD.getDiscoById(Integer.parseInt(discoId)).getNome());
                    posY+=20;
                    add(labelDisco);
                    JLabel labelFaixa;
                    Faixa faixa;
                    for(String faixaId:BD.getDiscoById(Integer.parseInt(discoId)).getDisco()){
                        faixa = BD.getFaixaById(Integer.parseInt(faixaId));
                        labelFaixa = new JLabel(faixa.getNome()+" - "+faixa.getAutor());
                        labelFaixa.setBounds(posX+40, posY, 780, 20);
                        add(labelFaixa);
                        posY+=20;
                    }
                }
            }
        }
    }
    
    public ArrayList<String> getSelectedIds(){
        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0; i < jCBGroup.size();i++){
            if(jCBGroup.get(i).isSelected()){
                if(jCBGroup.get(i).getElement() instanceof Faixa)
                    temp.add(((Faixa)jCBGroup.get(i).getElement()).getId()+"");
                if(jCBGroup.get(i).getElement() instanceof Disco)
                    temp.add(((Disco)jCBGroup.get(i).getElement()).getId()+"");
                if(jCBGroup.get(i).getElement() instanceof Coletanea)
                    temp.add(((Coletanea)jCBGroup.get(i).getElement()).getId()+"");
            }
        }
        return temp;
    }
    
    private void reset(){
        posY = 0;
        posX = 20;
        jCBGroup.clear();
        this.removeAll();
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
