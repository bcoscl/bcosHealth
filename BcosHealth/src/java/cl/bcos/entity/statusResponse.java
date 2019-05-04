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
public class statusResponse {
    String numuser;
    status status;

    public void setNumuser(String numuser) {
        this.numuser = numuser;
    }

    public String getNumuser() {
        return numuser;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public status getStatus() {
        return status;
    }
}
