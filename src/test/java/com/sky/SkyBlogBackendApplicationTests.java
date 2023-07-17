package com.sky;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sky.repositories.UserRepo;

@SpringBootTest
class SkyBlogBackendApplicationTests {

	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest() {
		String className = this.userRepo.getClass().getName();
		String pkgName = this.userRepo.getClass().getPackageName();
		System.out.println(className);
		System.out.println(pkgName);
	}

}
