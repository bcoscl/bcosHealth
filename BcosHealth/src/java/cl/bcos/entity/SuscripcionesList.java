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
public class SuscripcionesList {

    List<suscripciones> suscripciones;
    status status;

    public List<suscripciones> getSuscripciones() {
        return suscripciones;
    }

    public status getStatus() {
        return status;
    }

    public void setSuscripciones(List<suscripciones> suscripciones) {
        this.suscripciones = suscripciones;
    }

    public void setStatus(status status) {
        this.status = status;
    }


}
