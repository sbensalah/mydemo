package com.demo.soumaya.common.core.repositories;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableJpaRepositories("com.demo.soumaya.common.core.repositories")
@EntityScan("com.demo.soumaya.common.core.entities")
public class RepositoryTestConfig {

}
