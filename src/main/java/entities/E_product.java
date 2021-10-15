/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author diaz1
 */
public class E_product {
    
    
     /***** Atributos *****/
    private int idProduct;
    private String productName;
    private int quantityPerPackage;
    private double packageInitialPrice;
    private double unityInitialPrice;
    private double packageSalePrice;
    private double unitySalePrice;
    private int stock;
    private String image;
    private E_category idCategory;

    
    /***** Metodos Setter and Getter *****/
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantityPerPackage() {
        return quantityPerPackage;
    }

    public void setQuantityPerPackage(int quantityPerPackage) {
        this.quantityPerPackage = quantityPerPackage;
    }

    public double getPackageInitialPrice() {
        return packageInitialPrice;
    }

    public void setPackageInitialPrice(double packageInitialPrice) {
        this.packageInitialPrice = packageInitialPrice;
    }

    public double getUnityInitialPrice() {
        return unityInitialPrice;
    }

    public void setUnityInitialPrice(double unityInitialPrice) {
        this.unityInitialPrice = unityInitialPrice;
    }

    public double getPackageSalePrice() {
        return packageSalePrice;
    }

    public void setPackageSalePrice(double packageSalePrice) {
        this.packageSalePrice = packageSalePrice;
    }

    public double getUnitySalePrice() {
        return unitySalePrice;
    }

    public void setUnitySalePrice(double unitySalePrice) {
        this.unitySalePrice = unitySalePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public E_category getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(E_category idCategory) {
        this.idCategory = idCategory;
    }

    
    /***** Metodo Constructor *****/
    public E_product() {
    }

    public E_product(String productName, int quantityPerPackage, double packageInitialPrice, double unityInitialPrice, double packageSalePrice, double unitySalePrice, int stock, String image, E_category idCategory) {
        this.productName = productName;
        this.quantityPerPackage = quantityPerPackage;
        this.packageInitialPrice = packageInitialPrice;
        this.unityInitialPrice = unityInitialPrice;
        this.packageSalePrice = packageSalePrice;
        this.unitySalePrice = unitySalePrice;
        this.stock = stock;
        this.image = image;
        this.idCategory = idCategory;
    }

    public E_product(int idProduct, String productName, int quantityPerPackage, double packageInitialPrice, double unityInitialPrice, double packageSalePrice, double unitySalePrice, int stock, String image, E_category idCategory) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.quantityPerPackage = quantityPerPackage;
        this.packageInitialPrice = packageInitialPrice;
        this.unityInitialPrice = unityInitialPrice;
        this.packageSalePrice = packageSalePrice;
        this.unitySalePrice = unitySalePrice;
        this.stock = stock;
        this.image = image;
        this.idCategory = idCategory;
    }

    
    /***** Metodo toString *****/
    @Override
    public String toString() {
        return "D_product{" + "idProduct=" + idProduct + ", productName=" + 
                productName + ", quantityPerPackage=" + quantityPerPackage +
                ", packageInitialPrice=" + packageInitialPrice + ", unityInitialPrice=" +
                unityInitialPrice + ", packageSalePrice=" + packageSalePrice + 
                ", unitySalePrice=" + unitySalePrice + ", stock=" + stock +
                ", image=" + image + ", idCategory=" + idCategory + '}';
    }
}
