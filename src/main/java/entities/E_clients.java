/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author diaz1
 */
public class E_clients {
    
    /***** Atributos *****/
    private int idClient;
    private String firstName;
    private String lastName;
    private String numberphone;

    
    /***** Metodos Setter and Getter *****/
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
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

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    
    
    /***** Metodo Constructor *****/
    public E_clients() {
    }

    public E_clients(String firstName, String lastName, String numberphone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberphone = numberphone;
    }

    public E_clients(int idClient, String firstName, String lastName, String numberphone) {
        this.idClient = idClient;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberphone = numberphone;
    }

    
    
    /***** Metodo toString *****/
    @Override
    public String toString() {
        return "E_clients{" + "idClient=" + idClient + ", firstName=" + firstName +
                ", lastName=" + lastName + ", numberphone=" + numberphone + '}';
    }
}
