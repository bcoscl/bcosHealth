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
public class ProfileResponse {
    
    Profile Profile;
    status status;

    public Profile getProfile() {
        return Profile;
    }

    public status getStatus() {
        return status;
    }

    public void setProfile(Profile profile) {
        this.Profile = profile;
    }

    public void setStatus(status status) {
        this.status = status;
    }
    
}
