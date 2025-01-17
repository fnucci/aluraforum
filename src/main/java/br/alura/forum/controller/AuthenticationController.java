package br.alura.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.alura.forum.config.security.service.TokenService;
import br.alura.forum.controller.dto.TokenDto;
import br.alura.forum.form.LoginForm;

@RestController
@RequestMapping("/auth")
@Profile(value = {"prd", "test"})
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
		
		UsernamePasswordAuthenticationToken authOnject = form.converter();
		
		try {
			Authentication auth = authManager.authenticate(authOnject);
			
			String token = tokenService.gerarToken(auth);
			
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		}catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
