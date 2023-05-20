package com.fastcampus.ch2;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

public class MethodCall2 {
	public static void main(String[] args) throws Exception {
		Class clazz = Class.forName("com.fastcampus.ch2.YoilTellerMVC");
		Object obj = clazz.newInstance();
		
		Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
		
		Model model = new BindingAwareModelMap();
		System.out.println("[before] model=" + model);
		
		String viewName = (String)main.invoke(obj, new Object[]{2021, 10, 1, model});
		System.out.println("viewName=" + viewName);
		
		System.out.println("[after] model=" + model);
		
		render(model, viewName);
	}
	
	static void render(Model model, String viewName) throws IOException {
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