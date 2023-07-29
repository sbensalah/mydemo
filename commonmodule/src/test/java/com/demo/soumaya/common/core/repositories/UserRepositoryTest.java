package com.demo.soumaya.common.core.repositories;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

import java.util.Optional;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;

import com.demo.soumaya.common.core.entities.Password;
import com.demo.soumaya.common.core.entities.User;




@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { RepositoryTestConfig.class })
@Transactional
@Rollback(false)
public class UserRepositoryTest {
	
	
	@Autowired
	private IUserRepository userRepo;
	
	@Test
	public void testCreate() {
		User u = new User();
		u.setLogin("login1");
		u.setFirstname("firstname");
		u.setLastname("lastname");
		Password pwd = new Password();
		pwd.setPassword("pwd1!");
		u.setPwd(pwd);
		User createUser = userRepo.save(u);
		assertEquals("User save is rejected ",u, createUser);
	}
	
	@Test
	public void testUpdate() {
		User u = new User();
		u.setLogin("login2");
		u.setFirstname("firstname");
		u.setLastname("lastname");
		Password pwd = new Password();
		pwd.setPassword("pwd1!");
		u.setPwd(pwd);
		User createUser = userRepo.save(u);
		assertEquals("User save is rejected ",u, createUser);
		
		u = createUser;
		u.setFirstname("firstnameUpdate");
		User userUpdated = userRepo.save(u);
		Assert.assertNotNull(userUpdated);
		Assert.assertNotNull(userUpdated.getId());
	}
	
	@Test
	public void testDelete() {
		User u = new User();
		u.setLogin("login3");
		u.setFirstname("firstname");
		u.setLastname("lastname");
		Password pwd = new Password();
		pwd.setPassword("pwd1!");
		u.setPwd(pwd);
		User createUser = userRepo.save(u);
		Assert.assertNotNull(createUser);
		Assert.assertNotNull(createUser.getId());
	
		userRepo.delete(u);
		Optional<User> oUser = userRepo.findById(createUser.getId());
		Assert.assertFalse(oUser.isPresent());
	}
	
	@Test
	public void testfindByLoginIgnoreCase() {
		User u = new User();
		u.setLogin("login4");
		u.setFirstname("firstname");
		u.setLastname("lastname");
		Password pwd = new Password();
		pwd.setPassword("pwd1!");
		u.setPwd(pwd);
		User createUser = userRepo.save(u);
		Assert.assertNotNull(createUser);
		Assert.assertNotNull(createUser.getLogin());

		Optional<User> oUser = userRepo.findByLoginIgnoreCase(createUser.getLogin());
		Assert.assertTrue(oUser.isPresent());
		Assert.assertEquals(u, oUser.get());
	}
	
}
