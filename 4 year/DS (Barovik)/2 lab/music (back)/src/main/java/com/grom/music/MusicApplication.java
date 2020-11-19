package com.grom.music;

import com.grom.music.common.GlobalConfig;
import com.grom.music.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(GlobalConfig.class)
public class MusicApplication {
	@Autowired
	static PersonService service;
	public static void main(String[] args) {
		SpringApplication.run(MusicApplication.class, args);
	}
}
