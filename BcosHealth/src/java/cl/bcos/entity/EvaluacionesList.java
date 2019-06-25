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
public class EvaluacionesList {

    List<Evaluaciones> Evaluaciones;
    status status;

    public void setEvaluaciones(List<Evaluaciones> Evaluaciones) {
        this.Evaluaciones = Evaluaciones;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public List<Evaluaciones> getEvaluaciones() {
        return Evaluaciones;
    }

    public status getStatus() {
        return status;
    }

}
