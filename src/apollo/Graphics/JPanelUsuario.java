/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.Graphics;

import apollo.BD.Utilitarios;
import apollo.Entidades.Cobranca;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Leonardo Dias
 */
public class JPanelUsuario extends JPanel{
    
    private int posY;
    private int posX;
    ArrayList<JCBox> cobrancas = null;
    
    public JPanelUsuario(){
        setLayout(null);
        setBackground(Color.WHITE);
        posY = 0;
        posX = 20;
        cobrancas = new ArrayList<JCBox>();
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
                JCBoxData line1 = null;
                JCBox line2 = null;
                for (Cobranca cobranca : fatura) {
                    if(line1==null){
                        line1 = new JCBoxData();
                    }
                    if(!line1.getText().equals(Utilitarios.getData(cobranca.getData()))){
                        line1 = new JCBoxData();
                        line1.setText(Utilitarios.getData(cobranca.getData()));
                        line1.setBounds(posX, posY, 600, 20);
                        add(line1);
                        posY+=20;
                    }
                    line2 = new JCBox();
                    line2.setText(cobranca.getFaixa().getNome() + " - " + cobranca.getFaixa().getAutor() + " R$ " + cobranca.getFaixa().getValor());
                    line1.add(line2);
                    line2.setBounds(posX+20, posY, 600, 20);
                    cobrancas.add(line2);
                    add(line2);
                    posY+=20;
                }
            }
        }
        update();
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
    
    public void pagarSelecionados(){
        for(JCBox jCBox:cobrancas)
            if(jCBox.isSelected())
                jCBox.getElement().pagar();
    }
    
    public float getValorSelecionados(){
        float valor = 0;
        for(JCBox jCBox:cobrancas)
            if(jCBox.isSelected())
                valor += jCBox.getElement().getFaixa().getValor();
        return valor;
    }
    
    private class JCBox extends JCheckBox{

        Cobranca cobranca;
        
        public void add(Cobranca cobranca){
            this.cobranca = cobranca;
        }
       
        public Cobranca getElement(){
            return cobranca;
        }
    }
    
    private class JCBoxData extends JCheckBox implements ActionListener{
        ArrayList<JCBox> jCBoxList;
        
        public JCBoxData(){
            jCBoxList = new ArrayList<JCBox>();
            addActionListener(this);
        }
        
        public void add(JCBox jcbox){
            jCBoxList.add(jcbox);
        }
        
        public ArrayList<JCBox> getList(){
            return jCBoxList;
        }
        
        public void selectAll(){
            for(JCBox jCBox:jCBoxList)
                jCBox.setSelected(true);
        }
        
        public void deselectAll(){
            for(JCBox jCBox:jCBoxList)
                jCBox.setSelected(false);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(this.isSelected())
                selectAll();
            else
                deselectAll();
        }
    }
}
