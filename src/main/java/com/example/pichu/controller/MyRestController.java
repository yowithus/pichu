package com.example.pichu.controller;

import com.example.pichu.constant.ApiConstant;
import com.example.pichu.service.MyRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MyRestController {

	@Autowired
	private MyRestService myRestService;

	@PostMapping(ApiConstant.V1_TEST)
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<String> test() {
		return myRestService.test();
	}
}