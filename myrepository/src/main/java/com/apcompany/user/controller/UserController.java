package com.apcompany.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apcompany.user.pojo.TUser;
import com.apcompany.user.service.TUserServiceI;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private TUserServiceI userService;

	@RequestMapping("/index")
	public String index(Model model) {
		List<TUser> lstUsers = userService.getAllUser();
		model.addAttribute("lstUsers", lstUsers);
		return "index";
	}

	@RequestMapping("/single")
	public String single(Model model) {

		return "single";
	}

	@RequestMapping("/blog")
	public String blog(Model model) {

		return "blog";
	}

	@RequestMapping("/contact")
	public String contact(Model model) {

		return "contact";
	}

	@RequestMapping("/elements")
	public String elements(Model model) {

		return "elements";
	}

	@RequestMapping("/portfolio-1")
	public String portfolio1(Model model) {

		return "portfolio-1";
	}

	@RequestMapping("/portfolio-2")
	public String portfolio2(Model model) {

		return "portfolio-2";
	}

	@RequestMapping("/hello")
	public String helloWorld(Model model) {

		return "hello";
	}

}
