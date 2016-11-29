package beans.transport;

import beans.Entity;
import interfaces.Writer;

/**
 *
 * @author Dmitry
 */
public abstract class Transport extends Entity implements Writer {

    private int id;
//    private Timetable timetable;
    private int number;
    private int type;
    private int idRoute;
    private int idDriver;

    /**
     *
     * @param number
     * @param typeTransport
     * @param idRoute
     * @param idDriver
     */
    public Transport(int number, int typeTransport, int idRoute, int idDriver) {
//        timetable = new Timetable();
        this.number = number;
        this.type = typeTransport;
        this.idRoute = idRoute;
        this.idDriver = idDriver;
    }

    /**
     * @return the id of some Transport
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set new Transport id
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return a hashCode
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        return hash;
    }

    /**
     *
     * @param obj which will be compared
     * @return the comparison result
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
        final Transport other = (Transport) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

//    /**
//     * @return the timetable
//     */
//    public Timetable getTimetable() {
//        return timetable;
//    }
//
//    /**
//     * @param timetable the timetable to set
//     */
//    public void setTimetable(Timetable timetable) {
//        this.timetable = timetable;
//    }
    /**
     *
     * @return
     */
    public String writeTimetable() {
        return "Transport";
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @return the type
     */
    public String getStringType() {
        switch (this.type) {
            case 0:
                return "Bus";
            case 1:
                return "Trollybus";
            case 2:
                return "Tram";
            default:
                return null;
        }
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public abstract String writeAll();

    @Override
    public abstract String writeShort();

    /**
     * @return the idRoute
     */
    public int getIdRoute() {
        return idRoute;
    }

    /**
     * @param idRoute the idRoute to set
     */
    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    /**
     * @return the idDriver
     */
    public int getIdDriver() {
        return idDriver;
    }

    /**
     * @param idDriver the idDriver to set
     */
    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }

}
