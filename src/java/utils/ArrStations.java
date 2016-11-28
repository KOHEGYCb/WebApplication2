package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import beans.route.Station;

/**
 *
 * @author Dmitry
 */
public class ArrStations implements Serializable{

    /**
     * @return the stations
     */
    public Object deser() {
        Object obj = null;
        try {
            FileInputStream fis = new FileInputStream("src/netCracker/files/stations.t");
            ObjectInputStream ois = new ObjectInputStream(fis);
            obj = ois.readObject();
            fis.close();
            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            return new ArrayList<Station>();
        } catch (IOException ex) {
            System.out.println("Error read/write");
            System.exit(1);
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
            System.exit(2);
        }
        return obj;
    }

    /**
     *
     * @param stations
     */
    public void ser(List<Station> stations) {
        try {
            FileOutputStream fos = new FileOutputStream("src/netCracker/files/stations.t");
            ObjectOutputStream ofs = new ObjectOutputStream(fos);
            ofs.writeObject(stations);
            fos.close();
            ofs.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Error read/write");
        }
    }

    /**
     *
     * @param stations
     * @return
     */
    public List<Station> addStation(List<Station> stations) {
        System.out.print("*****\n"
                + "Write name: ");
        String str = new StringWrite().inputString();
        if (str == null) {
            System.out.println("Station not created");
            return stations;
        }
        stations.add(new Station(stations.size() + 1, str));
        System.out.println("Station created\n*****");
        return stations;
    }

    /**
     *
     * @param stations
     * @return
     */
    public String toString(List<Station> stations) {
        String str = "List Stations: \n";
        ListIterator<Station> listIterator = stations.listIterator();
        while (listIterator.hasNext()) {
            str = str + "  " + listIterator.next().writeAll();
        }
        return str;
    }

    /**
     *
     * @param stations
     * @return
     */
    public List<Station> remuveStation(List<Station> stations) {
        System.out.print("*****\n"
                + "Write id: ");
        try {
            int id = new Scanner(System.in).nextInt();
            boolean find = false;
            for (int i = 0; i < stations.size(); i++) {
                if (stations.get(i).getId() == id) {
                    find = true;
                    stations.remove(i);
                    for (int j = i; j < stations.size(); j++) {
                        stations.get(j).setId(j + 1);
                    }
                }
            }
            if (!find) {
                System.out.println("Id not found\nStation not remuved\n*****");
                return stations;
            }
        } catch (InputMismatchException ime) {
            System.out.println("Not integer\nStation not remuved\n*****");
            return stations;
        }
        System.out.println("Station remuved\n*****");
        return stations;
    }

    /**
     *
     * @param stations
     * @return
     */
    public List<Station> renameStation(List<Station> stations) {
        System.out.print("*****\n"
                + "Write id: ");
        try {
            int id = new Scanner(System.in).nextInt();
            boolean find = false;
            int i;
            for (i = 0; i < stations.size(); i++) {
                if (stations.get(i).getId() == id) {
                    find = true;
                    break;
                }
            }
            if (!find) {
                System.out.println("Id not found\nStation not reamed\n*****");
                return stations;
            }
            System.out.print("Station found\nWrite new name: ");
            String str = new StringWrite().inputString();
            if (str == null) {
                System.out.println("Station not created");
                return stations;
            }
            stations.get(i).setName(str);
        } catch (InputMismatchException ime) {
            System.out.println("Not integer\nStation not renamed\n*****");
            return stations;
        }
        System.out.println("Station renamed\n*****");
        return stations;
    }

    public String writeShort(List<Station> stations) {
        String str = "List Stations: \n";
        ListIterator<Station> listIterator = stations.listIterator();
        while (listIterator.hasNext()) {
            str = str + "  " + listIterator.next().writeShort();
        }
        return str;
    }

    public boolean searchId(int id, List <Station> stations){
        ListIterator <Station> listIterator = stations.listIterator();
        while (listIterator.hasNext()) {
            if(listIterator.next().getId() == id)
                return true;
        }
        return false;
    }
}
