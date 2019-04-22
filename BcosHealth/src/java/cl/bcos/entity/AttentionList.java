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
public class AttentionList {
    
    List<Attention> Attention;
    status status;

    public List<Attention> getAttention() {
        return Attention;
    }

    public status getStatus() {
        return status;
    }

    public void setAttention(List<Attention> Attention) {
        this.Attention = Attention;
    }

    public void setStatus(status status) {
        this.status = status;
    }
    
    
    
}
