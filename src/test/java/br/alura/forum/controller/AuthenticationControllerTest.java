package br.alura.forum.controller;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthenticationControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@Test
	public void autenticarTest() throws Exception {
		URI uri = new URI("/auth");
		String jsonRawBody = "{\"email\":\"invalido@gmail.com\",\"senha\":\"1234567890\"}";
		mock.perform(MockMvcRequestBuilders
				.post(uri)
				.content(jsonRawBody)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(HttpStatus.BAD_REQUEST.value()));
	}

}
