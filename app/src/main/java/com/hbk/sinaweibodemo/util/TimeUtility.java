package com.hbk.sinaweibodemo.util;

import com.hbk.sinaweibodemo.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * User: qii
 * Date: 12-8-28
 */
public class TimeUtility {

    private static int MILL_MIN = 1000 * 60;
    private static int MILL_HOUR = MILL_MIN * 60;
    private static int MILL_DAY = MILL_HOUR * 24;

    private static String JUST_NOW = GlobalContext.getInstance().getString(R.string.justnow);
    private static String MIN = GlobalContext.getInstance().getString(R.string.min);
    private static String HOUR = GlobalContext.getInstance().getString(R.string.hour);
    private static String DAY = GlobalContext.getInstance().getString(R.string.day);
    private static String MONTH = GlobalContext.getInstance().getString(R.string.month);
    private static String YEAR = GlobalContext.getInstance().getString(R.string.year);

    private static String YESTER_DAY = GlobalContext.getInstance().getString(R.string.yesterday);
    private static String THE_DAY_BEFORE_YESTER_DAY = GlobalContext.getInstance().getString(R.string.the_day_before_yesterday);
    private static String TODAY = GlobalContext.getInstance().getString(R.string.today);

    private static String DATE_FORMAT = GlobalContext.getInstance().getString(R.string.date_format);
    private static String YEAR_FORMAT = GlobalContext.getInstance().getString(R.string.year_format);

    private static Calendar msgCalendar = null;
    private static SimpleDateFormat baseFormat = null;
    private static SimpleDateFormat dayFormat = null;
    private static SimpleDateFormat dateFormat = null;
    private static SimpleDateFormat yearFormat = null;


    private TimeUtility() {

    }



    public static String getListTime(String dateString){

        if (baseFormat == null)
            baseFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss Z yyyy", Locale.UK);

        long now = System.currentTimeMillis();
        long msg = 0;
        try {
            msg = baseFormat.parse(dateString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar nowCalendar = Calendar.getInstance();

        if (msgCalendar == null)
            msgCalendar = Calendar.getInstance();

        msgCalendar.setTimeInMillis(msg);

        long calcMills = now - msg;

        long calSeconds = calcMills / 1000;

        if (calSeconds < 60) {
            return JUST_NOW;
        }

        long calMins = calSeconds / 60;

        if (calMins < 60) {

            return new StringBuilder().append(calMins).append(" ").append(MIN).toString();
        }

        long calHours = calMins / 60;

        if (calHours < 24 && isSameDay(nowCalendar, msgCalendar)) {
            if (dayFormat == null)
                dayFormat = new SimpleDateFormat("HH:mm");

            String result = dayFormat.format(msgCalendar.getTime());
            return new StringBuilder().append(TODAY).append(" ").append(result).toString();

        }


        long calDay = calHours / 24;

        if (calDay < 31) {
            if (isYesterDay(nowCalendar, msgCalendar)) {
                if (dayFormat == null)
                    dayFormat = new SimpleDateFormat("HH:mm");

                String result = dayFormat.format(msgCalendar.getTime());
                return new StringBuilder(YESTER_DAY).append(" ").append(result).toString();

            } else if (isTheDayBeforeYesterDay(nowCalendar, msgCalendar)) {
                if (dayFormat == null)
                    dayFormat = new SimpleDateFormat("HH:mm");

                String result = dayFormat.format(msgCalendar.getTime());
                return new StringBuilder(THE_DAY_BEFORE_YESTER_DAY).append(" ").append(result).toString();

            } else {
                if (dateFormat == null)
                    dateFormat = new SimpleDateFormat(DATE_FORMAT);

                String result = dateFormat.format(msgCalendar.getTime());
                return new StringBuilder(result).toString();
            }
        }

        long calMonth = calDay / 31;

        if (calMonth < 12 && isSameYear(nowCalendar, msgCalendar)) {
            if (dateFormat == null)
                dateFormat = new SimpleDateFormat(DATE_FORMAT);

            String result = dateFormat.format(msgCalendar.getTime());
            return new StringBuilder().append(result).toString();

        }
        if (yearFormat == null)
            yearFormat = new SimpleDateFormat(YEAR_FORMAT);
        String result = yearFormat.format(msgCalendar.getTime());
        return new StringBuilder().append(result).toString();


    }

    private static boolean isSameHalfDay(Calendar now, Calendar msg) {
        int nowHour = now.get(Calendar.HOUR_OF_DAY);
        int msgHOur = msg.get(Calendar.HOUR_OF_DAY);

        if (nowHour <= 12 & msgHOur <= 12) {
            return true;
        } else if (nowHour >= 12 & msgHOur >= 12) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isSameDay(Calendar now, Calendar msg) {
        int nowDay = now.get(Calendar.DAY_OF_YEAR);
        int msgDay = msg.get(Calendar.DAY_OF_YEAR);

        return nowDay == msgDay;
    }

    private static boolean isYesterDay(Calendar now, Calendar msg) {
        int nowDay = now.get(Calendar.DAY_OF_YEAR);
        int msgDay = msg.get(Calendar.DAY_OF_YEAR);

        return (nowDay - msgDay) == 1;
    }

    private static boolean isTheDayBeforeYesterDay(Calendar now, Calendar msg) {
        int nowDay = now.get(Calendar.DAY_OF_YEAR);
        int msgDay = msg.get(Calendar.DAY_OF_YEAR);

        return (nowDay - msgDay) == 2;
    }

    private static boolean isSameYear(Calendar now, Calendar msg) {
        int nowYear = now.get(Calendar.YEAR);
        int msgYear = msg.get(Calendar.YEAR);

        return nowYear == msgYear;
    }


}
