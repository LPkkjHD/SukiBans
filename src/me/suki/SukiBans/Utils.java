package me.suki.SukiBans;

import net.md_5.bungee.api.ProxyServer;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static void executeAsync(Runnable run){
        ProxyServer.getInstance().getScheduler().runAsync(BanManager.getInstance(), run);
    }

    public static String translateLongToString(long millis){
        String toReturn = "";
        int years, months ,days, hours, minutes, seconds;
        GregorianCalendar now = new GregorianCalendar();
        GregorianCalendar time = new GregorianCalendar();
        now.setTimeInMillis(System.currentTimeMillis());
        time.setTimeInMillis(millis);
        years = time.get(Calendar.YEAR) - now.get(Calendar.YEAR);
        months = time.get(Calendar.MONTH) - now.get(Calendar.MONTH);
        days = time.get(Calendar.DAY_OF_YEAR) - now.get(Calendar.DAY_OF_YEAR);
        hours = time.get(Calendar.WEEK_OF_YEAR) - now.get(Calendar.WEEK_OF_YEAR);
        minutes = time.get(Calendar.MINUTE) - now.get(Calendar.MINUTE);
        seconds = time.get(Calendar.SECOND) - now.get(Calendar.SECOND);
        if(years > 0){
            if(years == 1){
                toReturn = toReturn + years + " " + MessageManager.getTimeYear();
            } else {
                toReturn = toReturn + years + " " + MessageManager.getTimeYears();
            }
        }
        if(months > 0){
            if(months == 1){
                toReturn = toReturn + months + " " + MessageManager.getTimeMonth();
            } else {
                toReturn = toReturn + months + " " + MessageManager.getTimeMonths();
            }
        }
        if(days > 0){
            if(days == 1){
                toReturn = toReturn + days + " " + MessageManager.getTimeDay();
            } else {
                toReturn = toReturn + days + " " + MessageManager.getTimeDays();
            }
        }
        if(hours > 0){
            if(hours == 1){
                toReturn = toReturn + hours + " " + MessageManager.getTimeHour();
            } else {
                toReturn = toReturn + hours + " " + MessageManager.getTimeHours();
            }
        }
        if(minutes > 0){
            if(minutes == 1){
                toReturn = toReturn + minutes + " " + MessageManager.getTimeMinute();
            } else {
                toReturn = toReturn + minutes + " " + MessageManager.getTimeMinute();
            }
        }
        if(seconds > 0){
            if(seconds == 1){
                toReturn = toReturn + seconds + " " + MessageManager.getTimeSecond();
            } else {
                toReturn = toReturn + seconds + " " + MessageManager.getTimeSeconds();
            }
        }
        return toReturn;
    }

	private static final Pattern timePattern = Pattern.compile("(?:([0-9]+)\\s*y[a-z]*[,\\s]*)?(?:([0-9]+)\\s*mo[a-z]*[,\\s]*)?(?:([0-9]+)\\s*w[a-z]*[,\\s]*)?(?:([0-9]+)\\s*d[a-z]*[,\\s]*)?(?:([0-9]+)\\s*h[a-z]*[,\\s]*)?(?:([0-9]+)\\s*m[a-z]*[,\\s]*)?(?:([0-9]+)\\s*(?:s[a-z]*)?)?", 2);
	  public static long parseDuration(String durationStr) {
			    Matcher m = timePattern.matcher(durationStr);
			    int years = 0;
			    int months = 0;
			    int weeks = 0;
			    int days = 0;
			    int hours = 0;
			    int minutes = 0;
			    int seconds = 0;
			    boolean found = false;
			    while (m.find()) {
			      if ((m.group() != null) && (!m.group().isEmpty()))
			      {
			        for (int i = 0; i < m.groupCount(); i++) {
			          if ((m.group(i) != null) && (!m.group(i).isEmpty()))
			          {
			            found = true;
			            break;
			          }
			        }
			        if (found)
			        {
			          if ((m.group(1) != null) && (!m.group(1).isEmpty())) {
			            years = Integer.parseInt(m.group(1));
			          }
			          if ((m.group(2) != null) && (!m.group(2).isEmpty())) {
			            months = Integer.parseInt(m.group(2));
			          }
			          if ((m.group(3) != null) && (!m.group(3).isEmpty())) {
			            weeks = Integer.parseInt(m.group(3));
			          }
			          if ((m.group(4) != null) && (!m.group(4).isEmpty())) {
			            days = Integer.parseInt(m.group(4));
			          }
			          if ((m.group(5) != null) && (!m.group(5).isEmpty())) {
			            hours = Integer.parseInt(m.group(5));
			          }
			          if ((m.group(6) != null) && (!m.group(6).isEmpty())) {
			            minutes = Integer.parseInt(m.group(6));
			          }
			          if ((m.group(7) != null) && (!m.group(7).isEmpty())) {
			            seconds = Integer.parseInt(m.group(7));
			          }
			        }
			      }
			    }
			    if (!found) {
			    	return Long.MIN_VALUE;
			    }
			    Calendar c = new GregorianCalendar();
			    if (years > 0) {
			      c.add(1, years);
			    }
			    if (months > 0) {
			      c.add(2, months);
			    }
			    if (weeks > 0) {
			      c.add(3, weeks);
			    }
			    if (days > 0) {
			      c.add(5, days);
			    }
			    if (hours > 0) {
			      c.add(11, hours);
			    }
			    if (minutes > 0) {
			      c.add(12, minutes);
			    }
			    if (seconds > 0) {
			      c.add(13, seconds);
			    }
			    return c.getTimeInMillis() - System.currentTimeMillis();
			  }
}
