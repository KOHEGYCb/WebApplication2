package beans.route;

import java.io.Serializable;
import java.util.Objects;
import interfaces.Writer;

/**
 *
 * @author Dmitry
 */
public class Station implements Serializable, Writer{
    
    private int id;
    private String name;
    private int typeRoad;
    
    /**
     *
     */
    public Station (){
        
    }
    
    public Station(String name){
        this.name = name;
        this.typeRoad = new java.util.Random().nextInt(4);
    }
    
    /**
     *
     * @param id
     * @param name
     */
    public Station(int id, String name){
        this.id = id;
        this.name = name;
        this.typeRoad = new java.util.Random().nextInt(4);
    }
    
    /**
     *
     * @param id
     * @param name
     * @param typeRoad
     */
    public Station(int id, String name, int typeRoad){
        this.id = id;
        this.name = name;
        this.typeRoad = typeRoad;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Station other = (Station) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Station {\n     id: " + id + "\n     name: " + name + "\n     type road: " + typeRoad + "\n  }\n";
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the typeRoad
     */
    public int getTypeRoad() {
        return typeRoad;
    }

    /**
     * @param typeRoad the typeRoad to set
     */
    public void setTypeRoad(int typeRoad) {
        this.typeRoad = typeRoad;
    }

    @Override
    public String writeAll() {
        return toString();
    }

    @Override
    public String writeShort() {
        return "Station { id: " + id + "; name: " + name + "  }\n";
    }
    
    
    
}
