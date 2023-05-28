package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, Model m) {
		m.addAttribute("ex", ex);
		return "error";
	}

	@ExceptionHandler(NullPointerException.class)
	public String catcher2(Exception ex, Model m) {
		m.addAttribute("ex", ex);
		return "error";
	}

	@RequestMapping("/ex")
	public String main() throws Exception {
		throw new Exception("���ܰ� �߻��߽��ϴ�.");
	}

	@RequestMapping("/ex2")
	public String main2() throws Exception {
		throw new NullPointerException("���ܰ� �߻��߽��ϴ�.");
	}
}