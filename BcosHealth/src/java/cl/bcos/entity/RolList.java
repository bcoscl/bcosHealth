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
public class RolList {
    
    String NOMBRE;
    List<Rol> Rol;
    status status;

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    
    
    public List<Rol> getRoles() {
        return Rol;
    }

    public status getStatus() {
        return status;
    }

    public void setRoles(List<Rol> Roles) {
        this.Rol = Roles;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    
    
}