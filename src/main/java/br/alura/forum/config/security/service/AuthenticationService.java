package br.alura.forum.config.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.alura.forum.model.Usuario;
import br.alura.forum.repository.UsuarioRepository;

@Service
public class AuthenticationService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		
		if(usuario.isPresent())
		{
			return usuario.get();
		}
		
		throw new UsernameNotFoundException("Dados invalidos.");
	}

}
