package com.comcast.crm.generic.databaseutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.testng.annotations.Test;

public class Javautilty {
	public int getRandomNumber() {
		Random r=new Random();
		int random = r.nextInt(1000);
		return random;
		
	}
	public String getSystemDate() {
		Date d=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		 String date = sim.format(d);
	return date;
		
	}
	
	public String getrequiredDate(int days) {
		Date d=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		 String date = sim.format(d);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,days);
		String reqdate = sim.format(cal.getTime());
		System.out.println(reqdate);
		return reqdate;
		
		
	}

}
