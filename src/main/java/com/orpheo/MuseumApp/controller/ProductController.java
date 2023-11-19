package com.orpheo.MuseumApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orpheo.MaseumApp.servirce.IProductService;

@RestController
@RequestMapping("/api/v1/auth/products")
public class ProductController {
	@Autowired
	private IProductService iProductService;

}
