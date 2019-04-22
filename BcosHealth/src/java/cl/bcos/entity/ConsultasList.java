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
public class ConsultasList {

    List<Consultas> Consultas;
    status status;

    public List<Consultas> getConsultas() {
        return Consultas;
    }

    public status getStatus() {
        return status;
    }

    public void setConsultas(List<Consultas> Consultas) {
        this.Consultas = Consultas;
    }

    public void setStatus(status status) {
        this.status = status;
    }

}
