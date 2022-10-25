package com.example.hibernatetest.reposiroty;

import com.example.hibernatetest.entity.Author;
import com.example.hibernatetest.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAuthorRepository extends JpaRepository<Author,Long>{

    Author findByNickName(String nickName);
}
