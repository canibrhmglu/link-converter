package com.trendyol.linkconverter.controller;

import com.trendyol.linkconverter.entity.DeepLink;
import com.trendyol.linkconverter.entity.WebUrl;
import com.trendyol.linkconverter.service.LinkConverterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping(path="/linkConverter")
@AllArgsConstructor
public class LinkConverterController {
	
	private final LinkConverterService linkConverterService;
	
	@GetMapping
	public ResponseEntity<String> doGet() {
		return new ResponseEntity<>("Url Converter Application.", HttpStatus.OK);
	}
	
	@PostMapping(path="/deepLink")
	public ResponseEntity<String> createDeepLink(@RequestBody WebUrl webUrl) throws URISyntaxException{
		return new ResponseEntity<>(linkConverterService.createDeepLink(webUrl.getLink()), HttpStatus.OK);
	}

	@PostMapping(path="/webUrl")
	public ResponseEntity<String> createDeepLink(@RequestBody DeepLink deepLink) throws URISyntaxException{
		return new ResponseEntity<>(linkConverterService.createWebUrl(deepLink.getLink()), HttpStatus.OK);
	}

}
