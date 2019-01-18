/**
 * This class represents the Section class.
 * Each Section will have a name and can call the various methods
 * in the class. This includes adding a product to the section,
 * and checking whether or not an item is in the particular section.
 *
 * @author Sam Q., Felix L, Jasper W.
 * @version 1.8
 */
import java.util.ArrayList;
import java.util.HashMap;

public class Section {
  private String name;
  private HashMap<String ,HashMap<String, Product>> section = new HashMap<>(); //subsection name to subsection
  static HashMap<String, Product> allProducts = new HashMap<>(); //UPC to Product

  public Section(String name){
    this.name = name;
  }

  /**
   * Edits a Hash Map and adds a product to a given subsection with the given UPC
   * as the key and the product as the value.
   *
   * @param    product     The product to be added into the hash map.
   * @return   void
   */

  public void addProduct(Product product){
    section.get(product.getSubsection()).put(product.getUPC(), product);
    allProducts.put(product.getUPC(), product);
  }

  public void addSubsection(String subsection){
    HashMap<String, Product> sub = new HashMap<>(); //UPC to Product
    section.put(subsection, sub);
  }

  /**
   * Returns with a boolean whether a product is found in a particular section, by checking whether it is found in the
   * subsection of the section.
   *
   * @param    product     The Product to be searched if found in a certain subsection.
   * @return   boolean     Returns whether or not is it found.
   */
  public boolean inSection(Product product){
    return section.get(product.getSubsection()).containsKey(product.getUPC());
  }

  public HashMap<String ,HashMap<String, Product>> getSection(){
    return section;
  }

  public HashMap<String, Product> getSubsection(String subsection){
    return section.get(subsection);
  }

  public String getName(){
    return this.name;
  }

  public void setName(String name){
    this.name = name;
  }

}
