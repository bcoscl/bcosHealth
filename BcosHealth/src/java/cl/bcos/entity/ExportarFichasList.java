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
public class ExportarFichasList {

    List<ExportarFichas> ExportarFichas;

    public void setExportarFichas(List<ExportarFichas> ExportarFichas) {
        this.ExportarFichas = ExportarFichas;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public List<ExportarFichas> getExportarFichas() {
        return ExportarFichas;
    }

    public status getStatus() {
        return status;
    }
    status status;

}
