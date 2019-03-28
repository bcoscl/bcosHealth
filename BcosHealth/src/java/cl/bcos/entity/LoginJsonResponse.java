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
public class LoginJsonResponse {
    
    public String login;
    public status status;
    public String token;
    public UserInformation infoUser;

    public void setInfoUser(UserInformation infoUser) {
        this.infoUser = infoUser;
    }

    public UserInformation getInfoUser() {
        return infoUser;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public status getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }

    
}
