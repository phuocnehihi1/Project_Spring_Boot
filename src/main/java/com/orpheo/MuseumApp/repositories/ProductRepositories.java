package com.orpheo.MuseumApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orpheo.MuseumApp.entities.Product;

public interface ProductRepositories extends JpaRepository<Product, Long>{

}
