package com.orpheo.MuseumApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.orpheo.MuseumApp.dto.AuthRequest;
import com.orpheo.MuseumApp.dto.AuthResponse;
import com.orpheo.MuseumApp.dto.RegisterRequest;
import com.orpheo.MuseumApp.service.AuthService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
@Autowired
AuthService service;
@PostMapping("/register")
public ResponseEntity<AuthResponse> register(
    @RequestBody RegisterRequest request
) {
  return ResponseEntity.ok(service.register(request));
}
@PostMapping("/authenticate")
public ResponseEntity<AuthResponse> authenticate(
    @RequestBody AuthRequest request
) {
  return ResponseEntity.ok(service.authenticate(request));
}

@PostMapping("/refresh-token")
public void refreshToken(
    HttpServletRequest request,
    HttpServletResponse response
) throws IOException, StreamWriteException, DatabindException, java.io.IOException {
  service.refreshToken(request, response);
}
}
