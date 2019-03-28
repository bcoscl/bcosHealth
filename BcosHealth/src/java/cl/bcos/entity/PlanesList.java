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
public class PlanesList {

    List<Planes> Planes;
    status status;

    public void setPlanes(List<Planes> Planes) {
        this.Planes = Planes;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public List<Planes> getPlanes() {
        return Planes;
    }

    public status getStatus() {
        return status;
    }

}
