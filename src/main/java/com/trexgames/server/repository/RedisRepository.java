package com.trexgames.server.repository;

import com.trexgames.server.entity.Mail;
import org.springframework.data.repository.CrudRepository;


public interface RedisRepository extends CrudRepository<Mail,String> {
}
