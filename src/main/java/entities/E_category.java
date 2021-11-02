/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author diaz1
 */
public class E_category {
      /***** Atributos *****/
    private int idCategory;
    private String nameC;
    private String descriptionC;

    
     /***** Metodos Setter and Getter *****/
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    public String getDescriptionC() {
        return descriptionC;
    }

    public void setDescriptionC(String descriptionC) {
        this.descriptionC = descriptionC;
    }
    

     /***** Metodos Constructores *****/
    public E_category() {
    }

    public E_category(String nameC, String descriptionC) {
        this.nameC = nameC;
        this.descriptionC = descriptionC;
    }

    public E_category(int idCategory, String nameC, String descriptionC) {
        this.idCategory = idCategory;
        this.nameC = nameC;
        this.descriptionC = descriptionC;
    }

    
    /***** Metodo toString *****/
   
    @Override
    public String toString() {
        return this.nameC;
    }
}
