package lt.techin.lectureone.controller;

import lt.techin.lectureone.external.OpenLibraryClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private OpenLibraryClient openLibraryClientMock;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @Test
    void getBookByNamePath() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/bookName")
                        .param("bookName", "Colour of Magic")
        ).andExpect(
                status().isOk()
        );

        verify(openLibraryClientMock, times(1)).lookupBookByName(stringCaptor.capture());
        assertEquals("Colour of Magic", stringCaptor.getValue());
    }



}