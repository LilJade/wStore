/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author diaz1
 */
public class E_users {
    
    
      /***** Atributos *****/
    private int idUser;
    private String firstName;
    private String lastName;
    private String email;
    private String pass;

    
    /***** Metodos Setter and Getter *****/
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    
    /***** Metodos Constructores *****/
    public E_users() {
    }

    public E_users(String firstName, String lastName, String email, String pass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pass = pass;
    }

    public E_users(int idUser, String firstName, String lastName, String email, String pass) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pass = pass;
    }

    
        /***** Metodo toString *****/
    @Override
    public String toString() {
        return "D_users{" + "idUser=" + idUser + ", firstName=" + firstName + 
                ", lastName=" + lastName + ", email=" + email + ", pass=" + pass + '}';
    }
    
}
