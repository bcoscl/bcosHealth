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
public class UserInformation {
    
    public String name;// nombre
    public String LastName;// apellido
    public String numUser; // numero de usuario
    public String Roles;// roles separados por comas
    public String email; // email de contacto
    public String phoneNumber; // celular
    public String officeCode; // oficina - sucursal - codigo
    public String officeName; // oficina - sucursal - nombre
    public String companyCode;// codigo de empresa
    public String companyName; // empresa nombre

    public UserInformation() {
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return LastName;
    }

    public String getNumUser() {
        return numUser;
    }

    public String getRoles() {
        return Roles;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public String getOfficeName() {
        return officeName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setNumUser(String numUser) {
        this.numUser = numUser;
    }

    public void setRoles(String Roles) {
        this.Roles = Roles;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
}
