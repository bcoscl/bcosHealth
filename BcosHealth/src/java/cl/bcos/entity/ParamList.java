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
public class ParamList {

    List<Param> Param;
    status status;

    public void setParam(List<Param> Param) {
        this.Param = Param;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public List<Param> getParam() {
        return Param;
    }

    public status getStatus() {
        return status;
    }

}
