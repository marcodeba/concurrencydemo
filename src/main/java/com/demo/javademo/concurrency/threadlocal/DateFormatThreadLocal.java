package com.demo.javademo.concurrency.threadlocal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DateFormatThreadLocal {
    //private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ThreadLocal<DateFormat> dateFormatThreadLocal = new ThreadLocal<>();

    private static DateFormat getDateFormat() {
        DateFormat dateFormat = dateFormatThreadLocal.get();
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormatThreadLocal.set(dateFormat);
        }
        return dateFormat;
    }

    public static Date parse(String strDate) throws ParseException {
        //return sdf.parse(strDate);
        return getDateFormat().parse(strDate);
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            service.execute(() -> {
                try {
                    System.out.println(parse("2021-05-30 20:12:30"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
