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
public class PacientesList {

    List<Paciente> Paciente;
    status status;

    public List<Paciente> getPaciente() {
        return Paciente;
    }

    public status getStatus() {
        return status;
    }

    public void setPaciente(List<Paciente> Paciente) {
        this.Paciente = Paciente;
    }

    public void setStatus(status status) {
        this.status = status;
    }


}
