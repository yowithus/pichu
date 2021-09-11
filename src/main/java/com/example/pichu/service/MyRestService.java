package com.example.pichu.service;

import reactor.core.publisher.Mono;

public interface MyRestService {

	Mono<String> test();
}
