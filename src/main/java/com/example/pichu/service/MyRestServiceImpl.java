package com.example.pichu.service;

import com.example.pichu.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class MyRestServiceImpl implements MyRestService {

	@Override
	public Mono<String> test() {
		return Mono.zip(getMock1(), getMock2(), getMock3())
				.flatMap(tup -> {
					final String combinedNames = String.format("%s & %s & %s", tup.getT1().getName(), tup.getT2().getName(), tup.getT3().getName());
					return Mono.just(combinedNames);
				});
	}

	private Mono<ResponseDto> getMock1() {
		log.info("Calling mock1 api");

		return WebClient.create()
				.get()
				.uri("https://run.mocky.io/v3/f338cf3a-a3f5-44db-a153-4e91a2f02645")
				.retrieve()
				.bodyToMono(ResponseDto.class)
				.onErrorResume(this::doOnError);
	}

	private Mono<ResponseDto> getMock2() {
		log.info("Calling mock2 api");

		return WebClient.create()
				.get()
				.uri("https://run.mocky.io/v3/78303caf-bad4-4c86-baf2-b2777d43e848")
				.retrieve()
				.bodyToMono(ResponseDto.class)
				.onErrorResume(this::doOnError);
	}

	private Mono<ResponseDto> getMock3() {
		log.info("Calling mock3 api");

		return WebClient.create()
				.get()
				.uri("https://run.mocky.io/v3/78303caf-bad4-4c86-baf2-b2777d43e84")
				.retrieve()
				.bodyToMono(ResponseDto.class)
				.onErrorResume(this::doOnError);
	}

	private Mono<ResponseDto> doOnError(Throwable err) {
		log.error(err.getMessage(), err);
		return Mono.just(new ResponseDto("Error"));
	}
}
