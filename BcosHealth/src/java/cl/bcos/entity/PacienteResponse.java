/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.entity;

/**
 *
 * @author aacantero
 */
public class PacienteResponse {
    
    Paciente Paciente;
    status status;

    
    public status getStatus() {
        return status;
    }


    public void setStatus(status status) {
        this.status = status;
    }

    public Paciente getPaciente() {
        return Paciente;
    }

    public void setPaciente(Paciente Paciente) {
        this.Paciente = Paciente;
    }
    
}
