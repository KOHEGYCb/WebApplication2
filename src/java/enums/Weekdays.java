package enums;

/**
 *
 * @author Dmitry
 */
public enum Weekdays {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);
    
    private int index;

    /**
     * 
     * @param index 
     */
    Weekdays(int index) {
        this.index = index;
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
    
    
}
