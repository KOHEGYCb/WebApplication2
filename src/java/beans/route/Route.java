package beans.route;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Scanner;
import beans.transport.Bus;
import beans.transport.Tram;
import beans.transport.Transport;
import beans.transport.Trolleybus;
//import db.RouteWorker;
import utils.ArrStations;

/**
 *
 * @author Dmitry
 */
public class Route {

    private Station startStation;
    private Station finalStation;
    private int typeRoad;
    private int id;
//    private List<Transport> transport = new ArrayList<Transport>();

    
    public Route(int id, Station startStation, Station fianlStation){
        this.id = id;
        this.startStation = startStation;
        this.finalStation = fianlStation;
//        setTypeRoad();
    }
    
    /**
     *
     * @param stations
     */
    public Route(List<Station> stations) {
        initComp(stations);
    }

    /**
     *
     * @param stations
     * @param id
     */
    public Route(List<Station> stations, int id) {
        initComp(stations);
        this.id = id++;
//        new RouteWorker().addToDB(this);
    }

    private void initComp(List<Station> stations) {
        System.out.println(new ArrStations().writeShort(stations));
        int station1 = setStations(0, stations);
   
        int station2 = setStations(1, stations);

        if(station2 == station1){
            System.out.println("incorrect id");
            initComp(stations);
            return;
        }
        
        this.setStartStation(stations.get(station1-1));
        this.setFinalStation(stations.get(station2-1));
//        setTypeRoad();
    }

    private int intWrite(){
        int i;
        try{
            i = new Scanner(System.in).nextInt();
            return i;
        }catch(InputMismatchException ime){
            System.out.println("Not integer\nwrite again");
            i = intWrite();
            return i;
        }
    }
    private int setStations(int i, List<Station> stations){
        if (i == 0)
            System.out.print("Choose start station id: ");
        else
            System.out.print("Choose final station id: ");
        
        int station = intWrite();
        if (!new ArrStations().searchId(station, stations)){
            System.out.println("Statioon not found");
            station = setStations(i, stations);
        }
        return station;
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
        return "Route Id: " + id;
    }

    /**
     *
     * @return
     */
    public String routeInfo() {
        String str = "Route ID: " + id + " {\n" + getStartStation().toString() + getFinalStation().toString();
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

//    /**
//     * @return the typeRoad
//     */
//    public int getTypeRoad() {
//        return typeRoad;
//    }
//
//    /**
//     * @param typeRoad the typeRoad to set
//     */
//    public void setTypeRoad(int typeRoad) {
//        this.typeRoad = typeRoad;
//    }

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

//    /**
//     *
//     */
//    public void addTransport() {
//        Transport newTransport = null;
//        System.out.println("*****");
//        int type;
//        switch (typeRoad) {
//            case 0:
//                System.out.println("1) Add Bus");
//                newTransport = new Bus();
//                break;
//            case 1:
//                System.out.println(
//                        "1) Add Bus\n"
//                        + "2) Add Trolleybus");
//                try {
//                    type = new Scanner(System.in).nextInt();
//                    if (type == 1) {
//                        newTransport = new Bus();
//                    }
//                    if (type == 2) {
//                        newTransport = new Trolleybus();
//                    }
//                } catch (InputMismatchException ime) {
//                    System.out.println("Not integer");
//                }
//                break;
//            case 2:
//                System.out.println(
//                        "1) Add Bus\n"
//                        + "2) Add Trum");
//                try {
//                    type = new Scanner(System.in).nextInt();
//                    if (type == 1) {
//                        newTransport = new Bus();
//                    }
//                    if (type == 2) {
//                        newTransport = new Tram();
//                    }
//                } catch (InputMismatchException ime) {
//                    System.out.println("Not integer");
//                }
//                break;
//            case 3:
//                System.out.println(
//                        "1) Add Bus\n"
//                        + "2) Add Trolleybus\n"
//                        + "3) Add Trum");
//                try {
//                    type = new Scanner(System.in).nextInt();
//                    if (type == 1) {
//                        newTransport = new Bus();
//                    }
//                    if (type == 2) {
//                        newTransport = new Trolleybus();
//                    }
//                    if (type == 3) {
//                        newTransport = new Tram();
//                    }
//                } catch (InputMismatchException ime) {
//                    System.out.println("Not integer");
//                }
//                break;
//        }
//        System.out.println("*****");
//        if (newTransport == null) {
//            System.out.println("Transport not Add");
//            return;
//        }
//        getTransport().add(newTransport);
//        System.out.println("Transport Add");
//    }
//
//    /**
//     *
//     * @return
//     */
//    public String writeTransportTimetable() {
//        String str = "";
//        for (int i = 0; i < this.getTransport().size(); i++) {
//            str = str + getTransport().get(i).writeTimetable() + "\n";
//        }
//        return str;
//    }

//    /**
//     * @return the transports
//     */
//    public List<Transport> getTransport() {
//        return transport;
//    }
//
//    /**
//     * @param transports the transports to set
//     */
//    public void setTransport(List<Transport> transports) {
//        this.transport = transports;
//    }

//    /**
//     *
//     */
//    public void writeTransport() {
//        if (transport.isEmpty()) {
//            System.out.println("No Transport");
//            return;
//        }
//        System.out.println("Transport List");
//        ListIterator<Transport> listIterator = transport.listIterator();
//        while (listIterator.hasNext()) {
//            System.out.println("  " + listIterator.next());
//        }
//    }

//    /**
//     *
//     */
//    public void remuveTransport() {
//        writeTransport();
//        System.out.println("Write ID: ");
//        try {
//            int id = new Scanner(System.in).nextInt();
//            boolean isFound = false;
//            for (int i = 0; i < transport.size(); i++) {
//                if (transport.get(i).getId() == id) {
//                    transport.remove(i);
//                    isFound = true;
//                    break;
//                }
//            }
//            if (!isFound) {
//                System.out.println("Id not found");
//            }
//        } catch (InputMismatchException ime) {
//            System.out.println("Not integer");
//        }
//    }

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
