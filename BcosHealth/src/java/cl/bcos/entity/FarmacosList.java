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
public class FarmacosList {

    List<Farmacos> Farmacos;
    status status;

    public List<Farmacos> getFarmacos() {
        return Farmacos;
    }

    public status getStatus() {
        return status;
    }

    public void setFarmacos(List<Farmacos> Farmacos) {
        this.Farmacos = Farmacos;
    }

    public void setStatus(status status) {
        this.status = status;
    }

}
