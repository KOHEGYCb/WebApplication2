package beans;

/**
 *
 * @author Dmitry
 */
public abstract class Entity {

    private int id;

    public Entity() {
    }

    public Entity(int id) {
        this.id = id;
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

}
