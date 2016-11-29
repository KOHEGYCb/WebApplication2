package beans.route;

import beans.Entity;
import java.io.Serializable;
import java.util.Objects;
import interfaces.Writer;

/**
 *
 * @author Dmitry
 */
public class Station extends Entity implements Serializable, Writer{
    
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
        setId(id);
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
        setId(id);
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
        hash = 29 * hash + getId();
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
        if (getId() != other.getId()) {
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
        return "Station {\n     id: " + getId() + "\n     name: " + name + "\n     type road: " + typeRoad + "\n  }\n";
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
        return "Station { id: " + getId() + "; name: " + name + "  }\n";
    }
    
    
    
}
