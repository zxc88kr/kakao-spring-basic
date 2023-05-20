package com.fastcampus.ch2;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

public class MethodCall3 {
	public static void main(String[] args) throws Exception {
		Map map = new HashMap();
		map.put("year", "2021");
		map.put("month", "10");
		map.put("day", "1");

		Model model = null;
		Class clazz = Class.forName("com.fastcampus.ch2.YoilTellerMVC");
		Object obj = clazz.newInstance();
		
		Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
		
		Parameter[] paramArr = main.getParameters();
		Object[] argArr = new Object[main.getParameterCount()];
		
		for (int i = 0; i < paramArr.length; i++) {
			String paramName = paramArr[i].getName();
			Class paramType = paramArr[i].getType();
			Object value = map.get(paramName);
			
			if (paramType == Model.class) {
				argArr[i] = model = new BindingAwareModelMap();
			} else if (value != null) {
				argArr[i] = convertTo(value, paramType);
			}
		}
		System.out.println("paramArr=" + Arrays.toString(paramArr));
		System.out.println("argArr=" + Arrays.toString(argArr));
		
		String viewName = (String)main.invoke(obj, argArr);
		System.out.println("viewName=" + viewName);
		
		System.out.println("[after] model=" + model);
		
		render(model, viewName);
	}
	
	private static Object convertTo(Object value, Class type) {
		if (type == null || value == null || type.isInstance(value))
			return value;
		
		if (String.class.isInstance(value) && type == int.class) {
			return Integer.valueOf((String)value);
		} else if (String.class.isInstance(value) && type == double.class) {
			return Double.valueOf((String)value);
		}
		
		return value;
	}
	
	private static void render(Model model, String viewName) throws IOException {
		String result = "";
		
		Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/" + viewName + ".jsp"), "utf-8");
		
		while (sc.hasNextLine())
			result += sc.nextLine() + System.lineSeparator();
		
		Map map = model.asMap();
		
		Iterator it = map.keySet().iterator();
		
		while (it.hasNext()) {
			String key = (String)it.next();
			
			result = result.replace("${" + key + "}", "" + map.get(key));
		}
		
		System.out.println(result);
	}
}