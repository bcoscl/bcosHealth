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
public class SucursalesList {

    List<sucursales> sucursales;
    status status;

    public List<sucursales> getSucursales() {
        return sucursales;
    }

    public status getStatus() {
        return status;
    }

    public void setSucursales(List<sucursales> sucursales) {
        this.sucursales = sucursales;
    }

    public void setStatus(status status) {
        this.status = status;
    }

}
