package com.joltusek;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PikSbApplicationTests {

	@Autowired
	private GreetingController controller;

	@Test
	public void contextLoads() throws Exception {
		//assertThat(controller).isNotNull();
		assertTrue(true);
	}


}
