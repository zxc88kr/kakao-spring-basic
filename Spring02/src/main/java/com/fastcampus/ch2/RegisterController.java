package com.fastcampus.ch2;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
//	@GetMapping("/register/add")
	@RequestMapping(value="/register/add", method={RequestMethod.GET, RequestMethod.POST})
	public String add() {
		return "registerForm";
	}

//	@PostMapping("/register/save")
	@RequestMapping(value="/register/save", method=RequestMethod.POST)
	public String save(User user, Model model) throws Exception {
		if (!isValid(user)) {
			String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8");
			
			model.addAttribute("msg", msg);
			return "forward:/register/add";
		}
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return false;
	}
}