package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class YoilTellerMVC3 {
	@RequestMapping("/getYoilMVC3")
	public ModelAndView main(MyDate date) {
		System.out.println("date = " + date);

		ModelAndView mv = new ModelAndView();

		if (!isValid(date)) {
			mv.setViewName("yoilError");
			return mv;
		}

		char yoil = getYoil(date);

		mv.addObject("myDate", date);
		mv.addObject("yoil", yoil);

		mv.setViewName("yoil");

		return mv;
	}

	private char getYoil(MyDate date) {
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return " 일월화수목금토".charAt(dayOfWeek);
	}

	private boolean isValid(MyDate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}

	private boolean isValid(int year, int month, int day) {
		if (year == -1 || month == -1 || day == -1)
			return false;

		return (1 <= month && month <= 12) && (1 <= day && day <= 31);
	}
}