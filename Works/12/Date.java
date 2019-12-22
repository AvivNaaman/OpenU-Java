/**
 * a class representing a date.
 * @version 2020a
 * @author Aviv Naaman
 */
public class Date {
    /* Constants */
    // Default values
    private final int DEFAULT_YEAR = 2000;
    private final int DEFAULT_MONTH = 1;
    private final int DEFAULT_DAY = 1;
    // first day of month, first month of year
    private final int FIRST_DAY = 1;
    private final int FIRST_MONTH = 1;
    // meximum/minimum values
    private final int MAX_YEAR = 9999;
    private final int MIN_YEAR = 1000;
    private final int MIN_DAY = 1;
    // Months
    private final int JANUARY = 1;
    private final int FEBRUARY = 2;
    private final int DECEMBER = 12;
    
    private int _day;
    private int _month;
    private int _year;
    
    /* Constructors */
    /**
     * creates a new Date object if the date is valid, otherwise creates the date 1/1/2000
     * @param day the day in the month (1-31)
     * @param month the month in the year (1-12)
     * @param year the year (4 digits)
     */
    public Date(int day, int month, int year)
    {
        if (!isDateValid(day, month, year))
        { 
            // If not valid, set to defaults
            this._day = DEFAULT_DAY;
            this._month = DEFAULT_MONTH;
            this._year = DEFAULT_YEAR;
        }
        else 
        {
            // else initialize by all given params.
            this._day = day;
            this._month = month;
            this._year = year;
        }
    }
    /**
     * copy constructor
     * @param other the date to be copied
     */
    public Date(Date other) 
    {
        // Copy Constructor
        // Get values from other date to copy
        int day = other.getDay(),
            month = other.getMonth(),
            year = other.getYear();
        // and assign them
        this._day = day;
        this._month = month;
        this._year = year;
    }
    
    /* Getters */
    /**
     * gets the day 
     * @return the day
     */
    public int getDay()
    {
        return this._day;
    }
    
    /**
     * gets the month 
     * @return the month
     */
    public int getMonth()
    {
        return this._month;
    }
    
    /**
     * gets the year 
     * @return the year
     */
    public int getYear()
    {
        return this._year;
    }
    
    /* Setters */
    /**
     * sets the day (only if date remains valid) 
     * @param dayToSet the day value to be set
     */
    public void setDay(int dayToSet) 
    {
        // set only if date after changing the day is valid
        if (isDateValid(dayToSet, this._month, this._year))
            this._day = dayToSet;
    }
    
    /**
     * sets the month (only if date remains valid) 
     * @param monthToSet the month value to be set
     */
    public void setMonth(int monthToSet) 
    {
        // set only if date after changing the month is valid
        if (isDateValid(this._day, monthToSet, this._year))
            this._month = monthToSet;
    }
    
    /**
     * sets the year (only if date remains valid) 
     * @param yearToSet the year value to be set
     */
    public void setYear(int yearToSet) 
    {
        // set only if date after changing the year is valid
        if (isDateValid(this._day, this._month, yearToSet))
            this._year = yearToSet;
    }
    
    /* Methods */
    /**
     * check if 2 dates are the same 
     * @param other the date to compare this date to 
     * @return true if the dates are the same
     */
    public boolean equals(Date other)
    {
        // compare all instance varriables, return whether all same or not:
        return other.getDay() == this._day &&
                other.getMonth() == this._month &&
                other.getYear() == this._year;
    }
    
    /**
     * check if this date is before other date 
     * @param other date to compare this date to 
     * @return true if this date is before other date
     */
    public boolean before(Date other)
    {
        // if year smaller => true
        if (this._year < other.getYear())
            return true;
        // or if year equal
        else if (this._year == other.getYear()) 
        {
            // and month smaller then it's before
            if (this._month < other.getMonth())
                return true;
            // and if month equals
            else if (this._month == other.getMonth())
            {
                // and day's smaller
                if (this._day < other.getDay())
                {
                    return true;
                }
            }
        }
        // otherwise - if no TRUE returned, return false.
        return false;
    }
    
    /**
     * check if this date is after other date 
     * @param date to compare this date to 
     * @return true if this date is after other date
     */
    public boolean after(Date other)
    {
        // this after other = other before this
        return other.before(this);
    }
    
    /**
     * calculates the difference in days between two dates 
     * @param other the date to calculate the difference between 
     * @return the number of days between the dates
     */
    public int difference(Date other)
    {
        // get this date days count from start of the christian counting
        int thisDayCount = calculateDate(this._day, this._month, this._year);
        // and get the other date days count
        int otherDayCount = calculateDate(other.getDay(), other.getMonth(), other.getYear());
        // and subtract. doesn't matter who bigger, difference is the same so just turn it to 
        // positive (absolute value)
        return Math.abs(thisDayCount - otherDayCount);
    }
    
    /**
     * returns a String that represents this date 
     * @return String that represents this date in the following format: day/month/year for example: 02/03/1998
     */
    public String toString() 
    {
        // if day or month are 1-digit, format them to 2-digit (add a "0" at the end) 
        return (this._day < 10 ? "0" : "") + this._day + "/" + 
            (this._month < 10 ? "0" : "") + this._month + "/" + 
            this._year; 
    }
    
    /**
     * calculate the date of tomorrow 
     * @return the date of tomorrow
     */
    public Date tomorrow()
    {
        // get current values, so we don't change instance vars.
        int month = this._month;
        int day = this._day;
        int year = this._year;
        // if last day of month
        if (day == getMaxMonthDays(month, this._year))
        {
            // and last month of year (december)
            if (month == DECEMBER)
            {
                //next year
                year++;
                month = FIRST_MONTH;
            }
            // otherwise, next month
            else month++;
            // anyway day should be the first
            day = FIRST_DAY;
        }
        // if not, just next day.
        else 
        {
            day++;
        }
        // returning new date object - we don't want to cause any kind of aliasing.
        return new Date(day, month, year);
    }
    
    /**
     * calculate the day of the week that this date occurs on 0-Saturday 1-Sunday 2-Monday etc. 
     * @return the day of the week that this date occurs on
     */
    public int dayInWeek()
    {
        // calculate day in week by given formula.
        int D = this._day, M = this._month, C = this._year / 100;
        int Y = this._year % 100;
        // if month before march, treat it as the 13rd or 14th motnh of previous year.
        if (M == JANUARY || M == FEBRUARY) {
            M += 12;
            C = (this._year-1) / 100;
            Y = (this._year-1) %100;
        }
        // partial formula, without modulus at the end:
        int baseDay = (D + (26*(M+1)) / 10 + Y + Y / 4 + C / 4 -2*C);
        // if negative, calculate and return modulus with floorMod (for negatives)
        if (baseDay < 0)
            return Math.floorMod(baseDay, 7);
        // otherwise, normal modulus by 7:
        else
            return baseDay % 7;
    }
    
    /* Private methods */
    
    // Given method: no change was made:
    // computes the day number since the beginning of the Christian counting of years
    private int calculateDate (int day, int month, int year) 
    {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);
    }
    
    // returns whether a year is leap or not
    private boolean isLeap(int year) 
    {
        // Let's check if the year is multipication of 4, 100 and 400:
        boolean isFourMulti = year % 4 == 0;
        boolean isHundredMulti = year % 100 == 0;
        boolean is400Multi = year % 400 == 0;
        // Leap year is when year value is a multipication of four and not of 100, 
        // or when it's a multipication of 400:
        return (isFourMulti && !isHundredMulti) || is400Multi;
    }
    
    // Returns the days count by month and year.
    private int getMaxMonthDays(int month, int year)
    {
        // if February, on leap year 29 while on normal year 28.
        if (month == FEBRUARY) 
        {
            if (isLeap(year)) 
                return 29;
            else
                return 28;
        }
        // Days by months: from January to July, except February, odd month has 31 days, others have 30.
        // from August to December, odd months has 30 days, others have 31.
        else if ((month < 8 && month % 2 == 0) ||
                (month > 7 && month % 2 == 1))
        {
            return 30;
        }
        // Otherwise, the month has 31 days.
        else
            return 31;
    }
    
    // returns whether the combination of the days, months and years is valid.
    // first check values of each, then check by maximum day amount of month.
    private boolean isDateValid(int day, int month, int year) {
        // first, check if day, month and year value is valid by constants:
        // if not, don't continue testing.
        if (day <= MIN_DAY-1 || month < JANUARY || month > DECEMBER || year < MIN_YEAR || year > MAX_YEAR)
            return false;
        // Get and store maximum days in current month:
        int maxDays = getMaxMonthDays(month, year);
        // Return whether given day isn't too big for month.
        return day <= maxDays;
    }
}