package com.example.hibernatetest.reposiroty.Interface;

import com.example.hibernatetest.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorRepository extends JpaRepository<Author,Integer>{

    Author findByNickName(String nickName);

}
