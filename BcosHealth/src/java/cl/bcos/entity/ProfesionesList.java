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
public class ProfesionesList {
    
     List<Profesiones> Profesiones;
    status status;

    public List<Profesiones> getProfesiones() {
        return Profesiones;
    }

    public status getStatus() {
        return status;
    }

    public void setProfesiones(List<Profesiones> profesiones) {
        this.Profesiones = profesiones;
    }

    public void setStatus(status status) {
        this.status = status;
    }
    
    
    
    
}
