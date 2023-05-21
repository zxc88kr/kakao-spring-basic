package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YoilTellerMVC5 {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, BindingResult result) {
		System.out.println("result=" + result);
		System.out.println("error=" + result.getFieldError());

		return "yoilError";
	}

	@RequestMapping("/getYoilMVC5")
	public String main(MyDate date, BindingResult result) {
		System.out.println("result=" + result);
		if (!isValid(date))
			return "yoilError";
		return "yoil";
	}

	private @ModelAttribute("yoil") char getYoil(MyDate date) {
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return " 일월화수목금토".charAt(dayOfWeek);
	}

	private boolean isValid(MyDate date) {
		if (date.getYear() == -1 || date.getMonth() == -1 || date.getDay() == -1)
			return false;

		return (1 <= date.getMonth() && date.getMonth() <= 12) && (1 <= date.getDay() && date.getDay() <= 31);
	}
}