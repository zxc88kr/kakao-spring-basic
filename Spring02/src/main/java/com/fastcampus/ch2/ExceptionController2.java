package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

class MyException extends RuntimeException {
	MyException(String msg) {
		super(msg);
	}

	MyException() {
		this("");
	}
}

@Controller
public class ExceptionController2 {
	@RequestMapping("/ex3")
	public String main() throws Exception {
		throw new MyException("���ܰ� �߻��߽��ϴ�.");
	}

	@RequestMapping("/ex4")
	public String main2() throws Exception {
		throw new NullPointerException("���ܰ� �߻��߽��ϴ�.");
	}
}