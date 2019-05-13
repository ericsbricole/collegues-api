package dev.collegue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.collegue.model.Collegue;
import dev.collegue.model.InfosAuthentification;
import dev.collegue.repository.CollegueRepository;
import exception.CollegueNonTrouveException;
import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin
public class AuthentificationCtrl {

	@Value("${jwt.expires_in}")
	private Integer EXPIRES_IN;

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Value("${jwt.secret}")
	private String SECRET;

	@Autowired
	CollegueRepository collegueRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthentificationCtrl() {
	}

	@PostMapping(value = "/auth")
	public ResponseEntity<?> authentifier(@RequestBody InfosAuthentification infosAuthentification,
			HttpServletResponse response) throws CollegueNonTrouveException {
		UsernamePasswordAuthenticationToken userNamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				infosAuthentification.getEmail(), infosAuthentification.getPassword());

		Authentication authentication = authenticationManager.authenticate(userNamePasswordAuthenticationToken);

		User user = (User) authentication.getPrincipal();

		String rolesListe = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		Map<String, Object> supplementaryInfosToken = new HashMap<>();
		supplementaryInfosToken.put("roles", rolesListe);

		String tokenJWT = Jwts.builder().setSubject(user.getUsername()).addClaims(supplementaryInfosToken)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET).compact();

		Cookie authCookie = new Cookie(TOKEN_COOKIE, tokenJWT);
		authCookie.setHttpOnly(true);
		authCookie.setMaxAge(EXPIRES_IN * 1000);
		authCookie.setPath("/");
		response.addCookie(authCookie);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/me")
	public ResponseEntity<Collegue> me() throws CollegueNonTrouveException {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Collegue collegueCo = collegueRepository.findByEmail(email).orElseThrow( () -> new CollegueNonTrouveException("Le collegue connecte " + email + " n'a pas été retrouvé en base") );
		return ResponseEntity.status(HttpStatus.OK).body(collegueCo);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity mauvaiseInfosConnexion(BadCredentialsException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
