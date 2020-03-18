/**
 * 
 */
package com.group3.courseenrollment.controller;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author pc
 *
 */
@SpringJUnitWebConfig
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class OfferingControllerTests {
	MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	
	@Test
	public void getAllOfferings() throws Exception {
		// @formatter:off
			// is 200
			MvcResult foundResult = mockMvc.perform(get("/offerings")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();
			
			MvcResult notFoundResult = mockMvc.perform(get("/offerings")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json"))
					.andReturn();
			
			if(notFoundResult.getResponse().getStatus() == 404 || foundResult.getResponse().getStatus() == 200) {
				assertTrue(true);
			}else {
				fail();
			}
			
		// @formatter:on

	}

	
}
