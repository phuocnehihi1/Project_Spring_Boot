package com.orpheo.MuseumApp.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.orpheo.MuseumApp.repositories.TokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogOutService implements LogoutHandler {
	@Autowired
	TokenRepository tokenRepo;
	 @Override
	  public void logout(
	      HttpServletRequest request,
	      HttpServletResponse response,
	      Authentication authentication
	  ) {
	    final String authHeader = request.getHeader("Authorization");
	    final String jwt;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	      return;
	    }
	    jwt = authHeader.substring(7);
	    var storedToken = tokenRepo.findByToken(jwt)
	        .orElse(null);
	    if (storedToken != null) {
	      storedToken.setExpired(true);
	      storedToken.setRevoked(true);
	      tokenRepo.save(storedToken);
	      SecurityContextHolder.clearContext();
	    }
	  }
}
