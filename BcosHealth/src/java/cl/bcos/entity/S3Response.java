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
public class S3Response {
    
    S3Config S3;
    status status;

    public void setS3(S3Config S3) {
        this.S3 = S3;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public S3Config getS3() {
        return S3;
    }

    public status getStatus() {
        return status;
    }
    
     
    
}
