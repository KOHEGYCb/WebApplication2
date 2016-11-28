package enums;

/**
 *
 * @author Dmitry
 */
public enum DayPart {
    MORNING(1, 6, 10),
    AFTERNOON(2, 10, 16),
    EVENING(3, 16, 21),
    LATEEVENING(4, 21, 1),
    NIGHT(5, 1, 6);
    
    private int index;
    private int start;
    private int stop;

    /**
     * 
     * @param index
     * @param start
     * @param stop 
     */
    DayPart(int index, int start, int stop) {
        this.index = index;
        this.start = start;
        this.stop = stop;
    }

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @return the stop
     */
    public int getStop() {
        return stop;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @param start the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * @param stop the stop to set
     */
    public void setStop(int stop) {
        this.stop = stop;
    }
   
}
