package beans.route;

import beans.Entity;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Dmitry
 */
public class Route extends Entity {

    private Station startStation;
    private Station finalStation;
    private int typeRoad;

    public Route() {
    }

    public Route(int id, Station startStation, Station fianlStation) {
        setId(id);
        this.startStation = startStation;
        this.finalStation = fianlStation;
//        setTypeRoad();
    }

    private int intWrite() {
        int i;
        try {
            i = new Scanner(System.in).nextInt();
            return i;
        } catch (InputMismatchException ime) {
            System.out.println("Not integer\nwrite again");
            i = intWrite();
            return i;
        }
    }

    private void setTypeRoad() {
        if (getStartStation().getTypeRoad() < getFinalStation().getTypeRoad()
                | getStartStation().getTypeRoad() == getFinalStation().getTypeRoad()) {
            this.typeRoad = getStartStation().getTypeRoad();
        } else {
            this.typeRoad = getFinalStation().getTypeRoad();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Route Id: " + getId();
    }

    /**
     *
     * @return
     */
    public String routeInfo() {
        String str = "Route ID: " + getId() + " {\n" + getStartStation().toString() + getFinalStation().toString();
        return str + '}';
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.getStartStation());
        hash = 37 * hash + Objects.hashCode(this.getFinalStation());
        hash = 37 * hash + this.getId();
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
        final Route other = (Route) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        if (!Objects.equals(this.startStation, other.startStation)) {
            return false;
        }
        if (!Objects.equals(this.finalStation, other.finalStation)) {
            return false;
        }
        return true;
    }

    /**
     * @return the startStation
     */
    public Station getStartStation() {
        return startStation;
    }

    /**
     * @param startStation the startStation to set
     */
    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    /**
     * @return the finalStation
     */
    public Station getFinalStation() {
        return finalStation;
    }

    /**
     * @param finalStation the finalStation to set
     */
    public void setFinalStation(Station finalStation) {
        this.finalStation = finalStation;
    }

}
