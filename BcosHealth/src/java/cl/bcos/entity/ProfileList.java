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
public class ProfileList {

    List<Profile> Profile;
    status status;

    public void setProfiles(List<Profile> Profile) {
        this.Profile = Profile;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public List<Profile> getProfiles() {
        return Profile;
    }

    public status getStatus() {
        return status;
    }

}
