/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.BD;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo Dias
 */
public class Data {
    
    private static void salvarDados(String dados) {
            String nomeArquivo = "";
            if(dados.equals("Usuarios"))
                nomeArquivo = BD.getConfiguracoes().getUsuarioDat();
            else if(dados.equals("Faixas"))
                nomeArquivo = BD.getConfiguracoes().getFaixaDat();
            else if(dados.equals("Discos"))
                nomeArquivo = BD.getConfiguracoes().getDiscoDat();
            else if(dados.equals("Coletaneas"))
                nomeArquivo = BD.getConfiguracoes().getColetaneaDat();
            else if(dados.equals("Cobrancas"))
                nomeArquivo = BD.getConfiguracoes().getColetaneaDat();
            else if(dados.equals("Config"))
                nomeArquivo = BD.getConfiguracoes().getConfigDat();
            File arquivo = (new File(nomeArquivo));
            if(!arquivo.exists()){
                //if(!(new File(arquivo.getPath())).exists())
                //    (new File(arquivo.getPath())).mkdir();
                try {
                    arquivo.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(nomeArquivo);
            oos = new ObjectOutputStream(fos);
            //oos.writeObject(this.livros);
            //ou salvar livro a livro
            Object obj = null;
            if(dados.equals("Usuarios"))
                obj = BD.getAllUsuarios();
            else if(dados.equals("Faixas"))
                obj = BD.getAllFaixa();
            else if(dados.equals("Discos"))
                obj = BD.getAllDisco();
            else if(dados.equals("Coletaneas"))
                obj = BD.getAllColetanea();
            else if(dados.equals("Cobrancas"))
                obj = BD.getAllCobranca();
            else if(dados.equals("Config"))
                obj = new Configuracoes();
            
            if(!dados.equals("Config")){
                oos.writeInt(((ArrayList<Object>) obj).size());
                for (Object objects:((ArrayList<Object>)obj)) {
                    oos.writeObject(objects);
                }
            }
            else{
                oos.writeObject(obj);
            }
        } catch (IOException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void recuperarDados(String dados) {
        String nomeArquivo = "";
        if(dados.equals("Usuarios"))
            nomeArquivo = BD.getConfiguracoes().getUsuarioDat();
        else if(dados.equals("Faixas"))
            nomeArquivo = BD.getConfiguracoes().getFaixaDat();
        else if(dados.equals("Discos"))
            nomeArquivo = BD.getConfiguracoes().getDiscoDat();
        else if(dados.equals("Coletaneas"))
            nomeArquivo = BD.getConfiguracoes().getColetaneaDat();
        else if(dados.equals("Cobrancas"))
            nomeArquivo = BD.getConfiguracoes().getColetaneaDat();
        else if(dados.equals("Config"))
            nomeArquivo = BD.getConfiguracoes().getConfigDat();
        if((new File(nomeArquivo)).exists()){
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(nomeArquivo);
                ois = new ObjectInputStream(fis);
                ///this.livros = (ArrayList<Livro>) ois.readObject();
                ///ou ler livro por livro do arquivo -- depende de como salvou
                Object lista = null;
                if(dados.equals("Usuarios"))
                    lista = BD.getAllUsuarios();
                else if(dados.equals("Faixas"))
                    lista = BD.getAllFaixa();
                else if(dados.equals("Discos"))
                    lista = BD.getAllDisco();
                else if(dados.equals("Coletaneas"))
                    lista = BD.getAllColetanea();
                else if(dados.equals("Cobrancas"))
                    lista = BD.getAllCobranca();
                else if(dados.equals("Config"))
                    lista = BD.getConfiguracoes();
                if(!dados.equals("Config")){
                    ((ArrayList<Object>)lista).clear();
                    int numItens = ois.readInt();
                    for (int i=0; i<numItens; i++){
                        Object obj= (Object) ois.readObject();
                        ((ArrayList<Object>)lista).add(obj);
                    }
                }
                else{
                    lista = (Object) ois.readObject();
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    ois.close();
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void salvarUsuarios(){
        salvarDados("Usuarios");
    }
    public static void salvarFaixas(){
        salvarDados("Faixas");
    }
    public static void salvarDiscos(){
        salvarDados("Discos");
    }
    public static void salvarColetaneas(){
        salvarDados("Coletaneas");
    }
    public static void salvarCobrancas(){
        salvarDados("Cobrancas");
    }
    public static void salvarConfiguracoes(){
        salvarDados("Config");
    }
    public static void recuperar(){
        recuperarDados("Config");
        recuperarDados("Usuarios");
        recuperarDados("Faixas");
        recuperarDados("Discos");
        recuperarDados("Coletaneas");
        recuperarDados("Cobrancas");
    }
    
}
