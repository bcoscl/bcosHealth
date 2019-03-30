/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.entity;

import java.util.List;

/**
 *
 * @author aacantero
 */
public class FichasList {

    public List<Fichas> getFichas() {
        return Fichas;
    }

    public status getStatus() {
        return status;
    }

    public void setFichas(List<Fichas> Fichas) {
        this.Fichas = Fichas;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    List<Fichas> Fichas;
    status status;
    
    
}
