package business;
import entities.E_category;
import data.D_category;
import java.util.ArrayList;

/**
 *
 * @author LilJade
 */
public class B_categories {
    
    D_category data = new D_category();
    
    public ArrayList<E_category> B_listCategories() {
        ArrayList<E_category> list = data.showCategory();
        
        if (list == null) {
            System.out.println("Error al cargar la lista de categorias!");
            return null;
        }
        
        return list;
    }
    
    public void B_insertCategory(E_category category) {
        data.insertarCategoria(category);
    }
    
    public void B_updateCategory(E_category category) {
        data.updateCategoty(category);
    }
    
    public void B_deleteCategory(E_category category) {
        data.deletecategoria(category);
    }
}
