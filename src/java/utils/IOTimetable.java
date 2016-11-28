package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmitry
 */
public class IOTimetable {

    public String inputTimetable() {
        String str = "";
        try {
            FileInputStream fis = new FileInputStream("src/netCracker/files/timetable.t");
            boolean eof = false;
            while (!eof) {
                int ch = fis.read();

                if (ch == -1) {
                    eof = true;
                    break;
                }
                str = str + (char) ch;
            }
            fis.close();

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            return "";
        } catch (IOException ex) {
            System.out.println("Input error");
        }
        return str;
    }

    public void outputTimetable(String string) {
        try {
            FileOutputStream fos = new FileOutputStream("src/netCracker/files/timetable.t");
            for (int i = 0; i < string.length(); i++) {
                fos.write(string.charAt(i));
            }
            fos.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found");
        } catch (IOException ioe) {
            System.out.println("Output error");
        }
    }

}
