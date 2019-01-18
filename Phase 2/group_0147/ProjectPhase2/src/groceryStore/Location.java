package groceryStore;

/**
 * This class Location. This is the class that shows where
 * specific items are in their sections, aisles and/or
 * subsection.
 *
 * @author Sam Q., Felix L, Jasper W.
 * @version 1.8
 */

public class Location {
    private String section;
    private String subsection;
    private int aisle;

    public Location(String section, String subsection){
        this.section = section;
        this.subsection = subsection;
        this.aisle = 0;
    }
    public String getSection(){
        return section;
    }

    public String getSubsection(){
        return this.subsection;
    }

    public int getAisle(){
        return aisle;
    }

    public void setAisle(int num){
        aisle = num;
    }
}
