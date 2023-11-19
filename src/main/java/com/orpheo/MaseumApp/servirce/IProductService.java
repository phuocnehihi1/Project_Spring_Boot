package com.orpheo.MaseumApp.servirce;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.orpheo.MuseumApp.dto.Productdto;

@Service
public interface IProductService {
	
	ResponseEntity<?> addProduct(Productdto productdto);
	ResponseEntity<?> updateProduct(Long id, Productdto productdto);
	ResponseEntity<?> deteProduct(Long id);
	ResponseEntity<?> getAppProduct();
	ResponseEntity<?> findByproduct(long id, Productdto productdto);
	
}
