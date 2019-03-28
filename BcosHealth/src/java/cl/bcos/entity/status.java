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
public class status {
    public Integer statusCode;
    public String name;
    public String message;
    public String code;
    public String stack;

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public String getStack() {
        return stack;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }
    

    
    
}
