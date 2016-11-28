package beans.timetable;

import enums.Weekdays;
import enums.DayPart;
import utils.IOTimetable;

/**
 *
 * @author Dmitry
 */
public class Timetable {

    private Weekdays weekdays;
    private DayPart dayPart;
    private int interval[][];
    /**
     *
     */
    public Timetable() {
        interval = new int[7][5];
        for (Weekdays weekday : Weekdays.values()) {
            for (DayPart dayPart : DayPart.values()) {
                switch (dayPart) {
                    case MORNING:
                        this.interval[weekday.getIndex() - 1][dayPart.getIndex() - 1] = 10;
                        break;
                    case AFTERNOON:
                        this.interval[weekday.getIndex() - 1][dayPart.getIndex() - 1] = 20;
                        break;
                    case EVENING:
                        this.interval[weekday.getIndex() - 1][dayPart.getIndex() - 1] = 15;
                        break;
                    case LATEEVENING:
                        this.interval[weekday.getIndex() - 1][dayPart.getIndex() - 1] = 30;
                        break;
                    case NIGHT:
                        this.interval[weekday.getIndex() - 1][dayPart.getIndex() - 1] = 60;
                        break;
                }
                if (weekday == Weekdays.SUNDAY | weekday == Weekdays.SATURDAY) {
                    this.interval[weekday.getIndex() - 1][dayPart.getIndex() - 1] = 15;
                }
            }
        }
    }

    public void outputFile(){
        new IOTimetable().outputTimetable(toString());
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String str = new IOTimetable().inputTimetable();
        if ("".equals(str))
            new IOTimetable().outputTimetable(makeString());
        str = new IOTimetable().inputTimetable();
        return str;
    }

    /**
     *
     * @param weekdays
     * @return
     */
    public String toString(Weekdays weekdays) {
        String str = "Timetable{" + "weekdays=" + weekdays + "\n";
        for (DayPart dayPart : DayPart.values()) {
            str = str + " dayPart: " + dayPart + " intterval: " + this.getInterval(weekdays.getIndex() - 1, dayPart.getIndex() - 1) + "\n";
        }

        return str + "}";
    }

    /**
     *
     * @return
     */
    private String makeString() {
        String str = "Timetable {\n";
        for(Weekdays weekdays : Weekdays.values()){
            str = str + "Weekday: " + weekdays + "\n";
            for(DayPart dayPart : DayPart.values()){
                int hour = dayPart.getStart();
                int minute = 0;
                int endHour = dayPart.getStop();
                int interval = this.interval[weekdays.getIndex()-1][dayPart.getIndex()-1];
                str = str + "  ";
                while (hour != endHour){
                    if(minute >= 60){
                        minute = minute - 60;
                        hour++;
                    }
                    if (hour >= 24){
                        hour = hour - 24;
                    }
                    str = str + hour + ":" + minute + "  ";
//                    System.out.println(hour + ":" + minute + "||" + interval);
                    minute = minute + interval;
                }
                str = str + "\n";
            }
        }
        return str;
    }

    /**
     * @return the weekdays
     */
    public Weekdays getWeekdays() {
        return weekdays;
    }

    /**
     * @return the dayPart
     */
    public DayPart getDayPart() {
        return dayPart;
    }

    /**
     * @return the interval
     */
    public int[][] getInterval() {
        return interval;
    }

    /**
     *
     * @param i
     * @param j
     * @return
     */
    public int getInterval(int i, int j) {
        return interval[i][j];
    }

    /**
     * @param weekdays the weekdays to set
     */
    public void setWeekdays(Weekdays weekdays) {
        this.weekdays = weekdays;
    }

    /**
     * @param dayPart the dayPart to set
     */
    public void setDayPart(DayPart dayPart) {
        this.dayPart = dayPart;
    }

    /**
     * @param interval the interval to set
     */
    public void setInterval(int[][] interval) {
        this.interval = interval;
    }
}
