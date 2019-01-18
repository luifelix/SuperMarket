/**
 * This class represents the interface for the Store.
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
  static HashMap<String, Section> storeMap = new HashMap<>(); //section name to Section object
  private static ArrayList<String> allSections = new ArrayList<>();

  public Store(String name){
    this.name = name;
  }

  /**
   * Adds a new section into a pre-existing Store store.
   *
   * @param    name        The name of the new section being added.
   * @return   void
   */

  public static void addSection(String name){
    Section section = new Section(name);
    storeMap.put(name, section);
    allSections.add(name);
  }

  public String getName(){
    return this.name;
  }

  public void setName(String name){
    this.name = name;
  }
}
