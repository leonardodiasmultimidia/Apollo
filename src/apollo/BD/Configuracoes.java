/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.BD;

import java.io.Serializable;

/**
 *
 * @author Leonardo Dias
 */
public class Configuracoes implements Serializable{
    private int usuarioIdAI = 0;
    private int faixaIdAI = 0;
    private int discoIdAI = 0;
    private int coletaneaIdAI = 0;
    private int cobrancaIdAI = 0;
    private String usuarioDat = "data/usuario.dat";
    private String faixaDat = "data/faixa.dat";
    private String discoDat = "data/disco.dat";
    private String coletaneaDat = "data/coletanea.dat";
    private String cobrancaDat = "data/cobranca.dat";
    private String configDat = "data/config.dat";

    public int getUsuarioIdAI() {
        return usuarioIdAI++;
    }

    public int getFaixaIdAI() {
        return faixaIdAI++;
    }

    public int getDiscoIdAI() {
        return discoIdAI++;
    }

    public int getColetaneaIdAI() {
        return coletaneaIdAI++;
    }

    public int getCobrancaIdAI() {
        return cobrancaIdAI++;
    }

    public String getUsuarioDat() {
        return usuarioDat;
    }

    public String getFaixaDat() {
        return faixaDat;
    }

    public String getDiscoDat() {
        return discoDat;
    }

    public String getColetaneaDat() {
        return coletaneaDat;
    }

    public String getCobrancaDat() {
        return cobrancaDat;
    }

    public String getConfigDat() {
        return configDat;
    }
}
