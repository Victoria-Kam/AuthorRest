package com.example.hibernatetest.service;

import com.example.hibernatetest.entity.Author;
import com.example.hibernatetest.entity.Role;
import com.example.hibernatetest.reposiroty.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final IAuthorRepository repository;
    private final PasswordEncoder passwordEncoder;
   private final RoleService roleService;


    @Autowired
    public AuthorService(IAuthorRepository repository, PasswordEncoder passwordEncoder, RoleService roleService, IAuthorRepository iAuthorRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public Author register(Author author){
        Role role = roleService.findByName("AUTHOR");
        author.setRole(role);
        author.setPassword(passwordEncoder.encode(author.getPassword())); // захешируем пароль чтобы в БД он не хранился
        return repository.save(author);
    }

   public Author findByNickName(String nickName){
        return repository.findByNickName(nickName);
    }

    public Optional<Author> findByNickNameAndPassword(String nickName, String password){
        Author author = repository.findByNickName(nickName);
        if(author != null && passwordEncoder.matches(password,author.getPassword())){ //проверяем хеши паролей
            return  Optional.of(author);
        }
        return  Optional.empty();
    }

    public List<Author> getAll(){
        return repository.findAll();
    }

    public Optional<Author> getOne(long id){
        return repository.findById(id);
    }

    @Transactional
    public void save(Author author){
        repository.save(author);
    }

    @Transactional
    public void update(Author author){
        repository.save(author);
    }

    @Transactional
   public void delete(Author author){
        repository.delete(author);
    }

}
