package groceryStore;

/**
 * This class represents Store.
 * The Store will have a name and can call the method of being able to add
 * a new section into the pre-existing store.
 *
 * @author Sam Q., Felix L, Jasper W.
 * @version 1.8
 */
import java.util.ArrayList;
import java.util.HashMap;

public class Store {
  private String name;
  private HashMap<String, Section> storeMap = new HashMap<>(); //section name to Section object
  private ArrayList<String> allSections = new ArrayList<>();
  private HashMap<String, Product> allProducts = new HashMap<>();
  private int orderNum = 1;

  public Store(String name){
    this.name = name;
  }

  public HashMap<String, Section> getStoreMap(){
    return storeMap;
  }

  /**
   * Adds a new section into a pre-existing Store store.
   *
   * @param    name        The name of the new section being added.
   */
  public void addSection(String name){
    Section section = new Section(this, name);
    storeMap.put(name, section);
    allSections.add(name);
  }

  public HashMap<String, Product> getAllProducts(){
    return allProducts;
  }

  public int getOrderNum(){
    return orderNum;
  }

  public void incrementOrderNum(){
    orderNum++;
  }

  public String getName(){
    return this.name;
  }

  public void setName(String name){
    this.name = name;
  }
}
