package com.demo.soumaya.common.core.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.soumaya.common.core.entities.User;



@Repository
public interface IUserRepository extends CrudRepository<User, Long>{
	
	
	Optional<User> findByLoginIgnoreCase(String login);

}
