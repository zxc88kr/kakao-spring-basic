package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController2 {
	@RequestMapping("/ex3")
	public String main() throws Exception {
		throw new Exception("���ܰ� �߻��߽��ϴ�.");
	}

	@RequestMapping("/ex4")
	public String main2() throws Exception {
		throw new NullPointerException("���ܰ� �߻��߽��ϴ�.");
	}
}