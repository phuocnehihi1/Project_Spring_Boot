package com.orpheo.MuseumApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orpheo.MuseumApp.ENUM.TokenType;
import com.orpheo.MuseumApp.dto.AuthRequest;
import com.orpheo.MuseumApp.dto.AuthResponse;
import com.orpheo.MuseumApp.dto.RegisterRequest;
import com.orpheo.MuseumApp.entities.Token;
import com.orpheo.MuseumApp.entities.User;
import com.orpheo.MuseumApp.repositories.TokenRepository;
import com.orpheo.MuseumApp.repositories.UserRepository;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	TokenRepository tokenRepo;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	private final JwtService jwtService = new JwtService();
	@Autowired
	AuthenticationManager authenticationManager;

	
	public AuthResponse register(RegisterRequest request) {
	    var user = new User(1,request.getFirstname(),request.getLastname(),request.getEmail()
	    		,passwordEncoder.encode(request.getPassword()),request.getRole());
//	        .firstname(request.getFirstname())
//	        .lastname(request.getLastname())
//	        .email(request.getEmail())
//	        .password(passwordEncoder.encode(request.getPassword()))
//	        .role(request.getRole())
//	        .build();
	    var savedUser = userRepo.save(user);
	    var jwtToken = jwtService.generateToken(user);
	    var refreshToken = jwtService.generateRefreshToken(user);
	    saveUserToken(savedUser, jwtToken);
//	    return AuthResponse.builder()
//	        .accessToken(jwtToken)
//	            .refreshToken(refreshToken)
//	        .build();
	    return new AuthResponse(jwtToken, refreshToken);
	  }

	  public AuthResponse authenticate(AuthRequest request) {
	    authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(
	            request.getEmail(),
	            request.getPassword()
	        )
	    );
	    var user = userRepo.findByEmail(request.getEmail())
	        .orElseThrow();
	    var jwtToken = jwtService.generateToken(user);
	    var refreshToken = jwtService.generateRefreshToken(user);
	    revokeAllUserTokens(user);
	    saveUserToken(user, jwtToken);
//	    return AuthResponse.builder()
//	        .accessToken(jwtToken)
//	            .refreshToken(refreshToken)
//	        .build();
	    return new AuthResponse(jwtToken, refreshToken);
	  }

	  private void saveUserToken(User user, String jwtToken) {
		  var token = new Token(1,jwtToken,TokenType.BEARER,false,false,user);
//	    var token = Token.builder()
//	        .user(user)
//	        .token(jwtToken)
//	        .tokenType(TokenType.BEARER)
//	        .expired(false)
//	        .revoked(false)
//	        .build();
	    tokenRepo.save(token);
	  }

	  private void revokeAllUserTokens(User user) {
	    var validUserTokens = tokenRepo.findAllValidTokenByUser(user.getId());
	    if (validUserTokens.isEmpty())
	      return;
	    validUserTokens.forEach(token -> {
	      token.setExpired(true);
	      token.setRevoked(true);
	    });
	    tokenRepo.saveAll(validUserTokens);
	  }

	  public void refreshToken(
	          HttpServletRequest request,
	          HttpServletResponse response
	  ) throws IOException, StreamWriteException, DatabindException, java.io.IOException {
	    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	    final String refreshToken;
	    final String userEmail;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	      return;
	    }
	    refreshToken = authHeader.substring(7);
	    userEmail = jwtService.extractUsername(refreshToken);
	    if (userEmail != null) {
	      var user = this.userRepo.findByEmail(userEmail)
	              .orElseThrow();
	      if (jwtService.isTokenValid(refreshToken, user)) {
	        var accessToken = jwtService.generateToken(user);
	        revokeAllUserTokens(user);
	        saveUserToken(user, accessToken);
//	        var authResponse = AuthResponse.builder()
//	                .accessToken(accessToken)
//	                .refreshToken(refreshToken)
//	                .build();
	        var authResponse = new AuthResponse(accessToken,refreshToken);
	        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
	      }
	    }
	  }


}
