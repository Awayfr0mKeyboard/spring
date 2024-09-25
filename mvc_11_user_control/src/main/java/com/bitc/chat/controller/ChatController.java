package com.bitc.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

	@GetMapping("chat")
	public void chat() {}
	
	@GetMapping("userChat")
	public void userChat() {}
	
}
