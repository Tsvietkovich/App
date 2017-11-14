package com.tsvietkovich;


import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Hello world!
 *
 */
public class App {
    static long currentSystemTimeInMillis = System.currentTimeMillis();
    static GregorianCalendar calendar = new GregorianCalendar(2017, Calendar.MONTH, 9);
    static Date hireDay = calendar.getTime();

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" +  hireDay);

    }
}
