package com.kingname.springtestdemo.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
public class SampleControllerTest {

//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    public void hello() throws Exception {
//        mockMvc.perform(get("/hello"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Hello~kingname"))
//                .andDo(print());
//    }

//    @Autowired
//    TestRestTemplate testRestTemplate;
//
//    @MockBean
//    SampleService mockSampleService;
//
//    @Test
//    public void hello() throws Exception {
//        when(mockSampleService.getName()).thenReturn("kingname");
//
//        String result = testRestTemplate.getForObject("/hello", String.class);
//        assertThat(result).isEqualTo("Hello~kingname");
//    }

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    SampleService mockSampleService;

    @Test
    public void hello() throws Exception {
        when(mockSampleService.getName()).thenReturn("kingname");

        webTestClient.get().uri("/hello").exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello~kingname");
    }
}
