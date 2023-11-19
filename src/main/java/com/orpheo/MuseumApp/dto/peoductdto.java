package com.orpheo.MuseumApp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class peoductdto {
	private long idProduct;
	private String createDate;
	private String image;
	
	
	@NotNull(message ="Price must not be null")
	@Min(value = 0, message = "Price >= 0")
	private double price;
	
	@NotEmpty(message = "productName must not be empty")
	private String productName;
	
	@NotNull(message = "quantity must not be null")
	@Min(value = 0, message = "quantity >-1")
	private int quantity;

}
