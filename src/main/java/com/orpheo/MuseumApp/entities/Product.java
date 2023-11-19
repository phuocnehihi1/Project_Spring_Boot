package com.orpheo.MuseumApp.entities;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private long idProduct;
	
	
	@Column(name = "price")
	private double price;
	
	
	@Size(min = 1, max = 256)
	@Column(name = "productName")
	private String productName;
	
	@Column(name = "quatity")
	private int quatity;
	
	
	@Size(max = 256)
	@Column(name = "updateDate")
	private String updateDate;
	
	@Size(max = 256)
	@Column(name = "createDate")
	private String createDate;

}
