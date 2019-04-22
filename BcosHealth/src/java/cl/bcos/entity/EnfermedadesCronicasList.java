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
public class EnfermedadesCronicasList {
     
    List<EnfermedadesCronicas> EnfermedadesCronicas;
    status status;

    public List<EnfermedadesCronicas> getEnfermedadesCronicas() {
        return EnfermedadesCronicas;
    }

    public status getStatus() {
        return status;
    }

    public void setEnfermedadesCronicas(List<EnfermedadesCronicas> enfermedadesCronicas) {
        this.EnfermedadesCronicas = enfermedadesCronicas;
    }

    public void setStatus(status status) {
        this.status = status;
    }
    
    
}
