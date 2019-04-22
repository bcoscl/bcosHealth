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
public class ExamenesList {

    List<Examenes> Examenes;
    status status;

    public List<Examenes> getExamenes() {
        return Examenes;
    }

    public status getStatus() {
        return status;
    }

    public void setExamenes(List<Examenes> Examenes) {
        this.Examenes = Examenes;
    }

    public void setStatus(status status) {
        this.status = status;
    }
    
    

}
