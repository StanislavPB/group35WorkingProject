package org.group35workingproject.controller;

import lombok.RequiredArgsConstructor;
import org.group35workingproject.dto.authDto.AuthRequest;
import org.group35workingproject.dto.authDto.AuthResponse;
import org.group35workingproject.service.auth.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity<AuthResponse> authenticateManager(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getManagerName(),
                        authRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String managerNameFromRequest = authRequest.getManagerName();

        String jwt = jwtTokenProvider.createToken(managerNameFromRequest);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @GetMapping
    public ResponseEntity<?> authenticateAndGetToken(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // поверить что текущий пользователь (тот, данные которого лежат в контексте) - не анонимный

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: No token generated for anonymous user");
        } else {
            String username = authentication.getName();
            String token = jwtTokenProvider.createToken(username);
            return ResponseEntity.ok(new AuthResponse(token));
        }

    }

}
