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
    private double initialPrice;
    private double salePrice;
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

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
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

    public E_product(String productName, int quantityPerPackage, double initialPrice, double salePrice, int stock, String image, E_category idCategory) {
        this.productName = productName;
        this.quantityPerPackage = quantityPerPackage;
        this.initialPrice = initialPrice;
        this.salePrice = salePrice;
        this.stock = stock;
        this.image = image;
        this.idCategory = idCategory;
    }

    public E_product(int idProduct, String productName, int quantityPerPackage, double initialPrice, double salePrice, int stock, String image, E_category idCategory) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.quantityPerPackage = quantityPerPackage;
        this.initialPrice = initialPrice;
        this.salePrice = salePrice;
        this.stock = stock;
        this.image = image;
        this.idCategory = idCategory;
    }

    
    /***** Metodo toString *****/
    @Override
    public String toString() {
        return "D_product{" + "idProduct=" + idProduct + ", productName=" + 
                productName + ", quantityPerPackage=" + quantityPerPackage +
                ", initialPrice=" + initialPrice + ", salePrice=" + salePrice + 
                ", stock=" + stock + ", image=" + image + ", idCategory=" + idCategory + '}';
    }
}
