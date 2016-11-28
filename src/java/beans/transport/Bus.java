package beans.transport;

/**
 *
 * @author Dmitry
 */
public class Bus extends Transport {

    private int number;
    private int idDriver;
    /**
     *
     * @param number
     * @param idDriver
     * @param idRoute
     */
    public Bus(int number, int idDriver, int idRoute) {
        super(number, 0, idRoute, idDriver);
        this.idDriver = idDriver;
    }

    /**
     *
     * @return the short description of Bus
     */
    @Override
    public String toString() {
        return "Bus{ ID: " + this.getId() + "}";
    }

//    /**
//     *
//     * @return
//     */
//    @Override
//    public String writeTimetable() {
//        return "BUS " + getTimetable().toString();
//    }

    /**
     *
     * @return
     */
    @Override
    public String writeAll() {
        return "Bus{ ID: " + this.getId() + " Number: " + getNumber() + "}";
    }

    /**
     *
     * @return
     */
    @Override
    public String writeShort() {
        return "Bus{ ID: " + this.getId() + "}";
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
