package com.example.demo;

//test

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Collections;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoteController.class)
@AutoConfigureMockMvc
public class NoteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NoteRepository noteRepository;

	@Test
	public void testGetAllNotes() throws Exception {
		Note note = new Note();
		note.setId(1L);
		note.setTitle("Test Note");
		note.setContent("This is a test note");

		when(noteRepository.findAll()).thenReturn(Collections.singletonList(note));

		mockMvc.perform(MockMvcRequestBuilders.get("/notes"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].title").value("Test Note"))
				.andExpect(jsonPath("$[0].content").value("This is a test note"));
	}
}
