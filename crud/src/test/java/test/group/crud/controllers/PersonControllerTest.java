package test.group.crud.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import test.group.crud.dto.PersonCreateRequest;
import test.group.crud.dto.PersonUpdateRequest;
import test.group.crud.entities.Person;
import test.group.crud.services.PersonService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {
    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testPerson() throws Exception {
        Person person = new Person(1L, "test", "testSurname", 10, "Male");
        String json = objectMapper.writeValueAsString(person);
        when(personService.create(any(PersonCreateRequest.class))).thenReturn(person);
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.surname").value("testSurname"))
                .andExpect(jsonPath("$.age").value(10))
                .andExpect(jsonPath("$.gender").value("Male"));
    }

    @Test
    void testGet() throws Exception {
        Person person = new Person(1L, "test", "testSurname", 10, "Male");
        when(personService.get(1L)).thenReturn(person);
        mockMvc.perform(get("/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.surname").value("testSurname"))
                .andExpect(jsonPath("$.age").value(10))
                .andExpect(jsonPath("$.gender").value("Male"));
        verify(personService, times(1)).get(1L);
    }

    @Test
    void testUpdate() throws Exception {
        PersonUpdateRequest personUpdateRequest = new PersonUpdateRequest("testName", "testGender");
        String personUpdateRequestJson = objectMapper.writeValueAsString(personUpdateRequest);

        Person updatedPerson = new Person(1L, "test", "testSurname", 10, "Male");
        when(personService.update(any(PersonUpdateRequest.class), eq(1L))).thenReturn(updatedPerson);

        mockMvc.perform(patch("/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(personUpdateRequestJson))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.surname").value("testSurname"))
                .andExpect(jsonPath("$.age").value(10))
                .andExpect(jsonPath("$.gender").value("Male"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
