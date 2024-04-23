package in.vvm.FileBatchOperations.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class AppController {
	
	@GetMapping("/")
	public String blank() {
		return "redirect:/Home";
	}

	@GetMapping(value = "/login")
	public String login() {
		log.info("Get Login");
		return "Login";
	}

	@GetMapping(value = "/Home")
	public String home() {
		log.info("Get Home");
		return "form";
	}
}