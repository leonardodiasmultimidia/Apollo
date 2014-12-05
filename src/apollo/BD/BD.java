/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.BD;

import apollo.Entidades.Cobranca;
import apollo.Entidades.Coletanea;
import apollo.Entidades.Disco;
import apollo.Entidades.Faixa;
import apollo.Entidades.Usuario;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Leonardo Dias
 */
public class BD{
    
    private static ArrayList<Cobranca> extrato = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Faixa> faixas = new ArrayList<>();
    private static ArrayList<Disco> discos = new ArrayList<>();
    private static ArrayList<Coletanea> coletaneas = new ArrayList<>();
    private static Configuracoes config = new Configuracoes();
    
    public static void adicionaCobranca(Cobranca cobranca){
        cobranca.setId(config.getCobrancaIdAI());
        extrato.add(cobranca);
    }
    
    public static void adicionaUsuario(Usuario usuario){
        usuario.setId(config.getUsuarioIdAI());
        usuarios.add(usuario);
    }
    
    public static void adicionaFaixa(Faixa faixa){
        faixa.setId(config.getFaixaIdAI());
        faixas.add(faixa);
    }
    
    public static void adicionaDisco(Disco disco){
        disco.setId(config.getDiscoIdAI());
        discos.add(disco);
    }
    
    public static void adicionaColetanea(Coletanea coletanea){
        coletanea.setId(config.getColetaneaIdAI());
        coletaneas.add(coletanea);
    }
    
    public static Configuracoes getConfiguracoes(){
        return config;
    }
    
    public static ArrayList<Cobranca> getAllCobranca(){
        return extrato;
    }
    
    public static ArrayList<Cobranca> getAllCobranca(Calendar dataInicial, Calendar dataFinal){
        ArrayList<Cobranca> extratoRetorno = new ArrayList<>();
        for(Cobranca cobranca: extrato)
            if(cobranca.getData().after(dataInicial) && cobranca.getData().before(dataFinal))
                extratoRetorno.add(cobranca);
        return extratoRetorno;
    }
    
    public static ArrayList<Cobranca> getAllCobrancaOf(int idUsuario){
        ArrayList<Cobranca> extratoRetorno = new ArrayList<>();
        for(Cobranca cobranca: extrato)
            if(cobranca.getIdUsuario() == idUsuario)
                extratoRetorno.add(cobranca);
        return extratoRetorno;
    }
    
    public static ArrayList<Cobranca> getAllCobrancaOf(int idUsuario, Calendar dataInicial, Calendar dataFinal){
        ArrayList<Cobranca> extratoRetorno = new ArrayList<>();
        for(Cobranca cobranca: extrato)
            if(cobranca.getIdUsuario() == idUsuario && cobranca.getData().after(dataInicial) && cobranca.getData().before(dataFinal))
                extratoRetorno.add(cobranca);
        return extratoRetorno;
    }
    
    public static ArrayList<Usuario> getAllUsuarios(){
        return usuarios;
    }
    
    public static ArrayList<Faixa> getAllFaixa(){
        return faixas;
    }
    
    public static ArrayList<Faixa> getAllFaixaByNome(String nomeFaixa){
        ArrayList<Faixa> faixasRetorno = new ArrayList<>();
        for(Faixa faixa: faixas)
            if(faixa.getNome().toUpperCase().contains(nomeFaixa))
                faixasRetorno.add(faixa);
        return faixasRetorno;
    }
    
    public static ArrayList<Faixa> getAllFaixaByAutor(String nomeAutor){
        ArrayList<Faixa> faixasRetorno = new ArrayList<>();
        for(Faixa faixa: faixas)
            if(faixa.getAutor().toUpperCase().contains(nomeAutor))
                faixasRetorno.add(faixa);
        return faixasRetorno;
    }
    
    public static ArrayList<Faixa> getAllFaixaByDisco(Disco disco){
        ArrayList<Faixa> faixasRetorno = new ArrayList<>();
        for(String idFaixa: disco.getDisco())
            faixasRetorno.add(getFaixaById(Integer.parseInt(idFaixa)));
        return faixasRetorno;
    }
    
    public static ArrayList<Faixa> getAllFaixaByColetanea(Coletanea coletanea){
        ArrayList<Faixa> faixasRetorno = new ArrayList<>();
        for(String idDisco: coletanea.getColetanea())
            for(Faixa faixa:getAllFaixaByDisco(getDiscoById(Integer.parseInt(idDisco))))
                faixasRetorno.add(faixa);
        return faixasRetorno;
    }
    
    public static ArrayList<Disco> getAllDisco(){
        return discos;
    }
    
    public static ArrayList<Disco> getAllDiscoByNome(String nomeDisco){
        ArrayList<Disco> discoRetorno = new ArrayList<>();
        for(Disco disco: discos)
            if(disco.getNome().toUpperCase().contains(nomeDisco))
                discoRetorno.add(disco);
        return discoRetorno;
    }
    
    public static ArrayList<Disco> getAllDiscoByAutor(String nomeAutor){
        ArrayList<Disco> discoRetorno = new ArrayList<>();
        for(Disco disco: discos)
            for(Faixa faixa: getAllFaixaByDisco(disco))
                if(faixa.getAutor().toUpperCase().contains(nomeAutor)){
                    discoRetorno.add(disco);
                    break;
                }
        return discoRetorno;
    }
    
    public static ArrayList<Coletanea> getAllColetanea(){
        return coletaneas;
    }
    
    public static ArrayList<Coletanea> getAllColetaneaByNome(String nomeColetanea){
        ArrayList<Coletanea> coletaneaRetorno = new ArrayList<>();
        for(Coletanea coletanea: coletaneas)
            if(coletanea.getNome().toUpperCase().contains(nomeColetanea))
                coletaneaRetorno.add(coletanea);
        return coletaneaRetorno;
    }
    
    public static ArrayList<Coletanea> getAllColetaneaByAutor(String nomeAutor){
        ArrayList<Coletanea> coletaneaRetorno = new ArrayList<>();
        for(Coletanea coletanea: coletaneas)
            for(Faixa faixa: getAllFaixaByColetanea(coletanea))
                if(faixa.getAutor().toUpperCase().contains(nomeAutor)){
                    coletaneaRetorno.add(coletanea);
                    break;
                }
        return coletaneaRetorno;
    }
    
    public static Cobranca getCobrancaBy(int idCobranca){
        for(Cobranca cobranca: extrato)
            if(cobranca.getIdUsuario() == idCobranca)
                return cobranca;
        return null;
    }
    
    public static Usuario getUsuarioBy(int idUsuario){
        for(Usuario usuario: usuarios)
            if(usuario.getId() == idUsuario)
                return usuario;
        return null;
    }
    
    public static Usuario getUsuarioBy(String email, String senha){
        for(Usuario usuario: usuarios)
            if(usuario.getEmail().equals(email) && usuario.getSenha().equals(senha))
                return usuario;
        return null;
    }
    
    public static Faixa getFaixaById(int idFaixa){
        //for(Faixa faixa: faixas)
        //    if(faixa.getId() == idFaixa)
        //        return faixa;
        if(faixas.get(idFaixa)!=null)
            return faixas.get(idFaixa);
        return null;
    }
    
    public static Disco getDiscoById(int idDisco){
        //for(Disco disco: discos)
        //    if(disco.getId() == idDisco)
        //        return disco;
        if(discos.get(idDisco)!=null)
            return discos.get(idDisco);
        return null;
    }
    
    public static Coletanea getColetaneaById(int idColetanea){
        //for(Coletanea coletanea: coletaneas)
        //    if(coletanea.getId() == idColetanea)
        //        return coletanea;
        if(coletaneas.get(idColetanea)!=null)
            return coletaneas.get(idColetanea);
        return null;
    }
}
