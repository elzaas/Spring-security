package com.workshhop.elza.controller;

import com.workshhop.elza.JwtUtils;
import com.workshhop.elza.dao.UserDao;
import com.workshhop.elza.dto.AuthentificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final JwtUtils jwtUtils;
 @PostMapping("/authenticate")
    public ResponseEntity<String> authentificate(
            @RequestBody AuthentificationRequest request)
    {
        System.out.println("gcfv");
        authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );
    final UserDetails user = userDao.findUsersByEmail(request.getEmail());
    if (user != null) {
      return ResponseEntity.ok(jwtUtils.generateToken(user));
    }
    return ResponseEntity.status(400).body("Bad request");
  }
}
