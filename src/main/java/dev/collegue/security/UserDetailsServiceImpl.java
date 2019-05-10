package dev.collegue.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.collegue.model.Collegue;
import dev.collegue.repository.CollegueRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CollegueRepository collegueRepo;

	public UserDetailsServiceImpl() {
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Collegue collegue = collegueRepo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Pas de collegue d'email " + username + " trouvé"));

		return new User(collegue.getEmail(), collegue.getPassword(),
				collegue.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
	}

}
