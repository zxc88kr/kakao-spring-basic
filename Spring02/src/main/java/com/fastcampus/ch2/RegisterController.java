package com.fastcampus.ch2;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
	@GetMapping("/register/add")
	public String add() {
		return "registerForm";
	}

	@PostMapping("/register/save")
	public String save(User user, Model model) throws Exception {
		if (!isValid(user)) {
			String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8");
			
			model.addAttribute("msg", msg);
			return "redirect:/register/add";
		}
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}