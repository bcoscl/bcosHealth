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
public class RolesList {
    
    List<Roles> Roles;
    status status;

    public void setRoles(List<Roles> roles) {
        this.Roles = roles;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public List<Roles> getRoles() {
        return Roles;
    }

    public status getStatus() {
        return status;
    }
    

    
}
