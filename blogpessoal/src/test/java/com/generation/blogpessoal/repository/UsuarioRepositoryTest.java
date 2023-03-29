package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {
		usuarioRepository.deleteAll();
		usuarioRepository.save(
				new Usuario(0L, "João da Silva", "joao@email.com.br", "123456789", "https://i.imgur.com/FETvs20.jpg"));
		usuarioRepository.save(new Usuario(0L, "Manula de Silva", "manuela@email.com.br", "123456789",
				"https://i.imgur.com/NtyGneo.jpg"));
		usuarioRepository.save(new Usuario(0L, "Adriana da Silva", "adriana@email.com.br", "123456789",
				"https://i.imgur.com/mB3VM2N.jpg"));
		usuarioRepository.save(
				new Usuario(0L, "Paulo Antunes", "paulo@email.com.br", "123456789", "https://i.imgur.com/JK7kUFU.jpg"));

	}

	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornaUmUsuario() {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));

	}

	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornoTresUsuario() {

		List<Usuario> listaDeUsuario = usuarioRepository.findAllByNomeContainigIgnoreCase("Silva");
		assertEquals(3, listaDeUsuario.size());
		assertTrue(listaDeUsuario.get(0).getUsuario().equals("Joao da Silva"));
		assertTrue(listaDeUsuario.get(1).getUsuario().equals("Manulea da Silva"));
		assertTrue(listaDeUsuario.get(2).getUsuario().equals("Adriana da Silva"));

	}
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
}
