package com.fastcampus.ch2;

import java.net.URLEncoder;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
	@InitBinder
	public void toBinding(WebDataBinder binder) {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy/mm/dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));
	}

//	@GetMapping("/register/add")
	@RequestMapping(value = "/register/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add() {
		return "registerForm";
	}

//	@PostMapping("/register/save")
	@RequestMapping(value = "/register/save", method = RequestMethod.POST)
	public String save(User user, BindingResult result, Model model) throws Exception {
		/*
		 * UserValidator userValidator = new UserValidator();
		 * userValidator.validate(user, result);
		 * 
		 * if (result.hasErrors()) { return "registerForm"; }
		 */

		if (!isValid(user)) {
			String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8");

			model.addAttribute("msg", msg);
			return "redirect:/register/add"; // 신규회원 가입화면으로 이동(redirect)
		}

		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}