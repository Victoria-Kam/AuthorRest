package com.example.hibernatetest.service;

import com.example.hibernatetest.entity.Author;
import com.example.hibernatetest.entity.Note;
import com.example.hibernatetest.entity.Role;
import com.example.hibernatetest.reposiroty.AuthorRepository;
import com.example.hibernatetest.reposiroty.Interface.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository repository;
    private final PasswordEncoder passwordEncoder;
   private final RoleService roleService;
   private final IAuthorRepository iAuthorRepository;


    @Autowired
    public AuthorService(AuthorRepository repository, PasswordEncoder passwordEncoder, RoleService roleService, IAuthorRepository iAuthorRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.iAuthorRepository = iAuthorRepository;
    }

    public Author register(Author author){
        Role role = roleService.findByName("ROLE_AUTHOR");
        author.setRole(role);
        author.setPassword(passwordEncoder.encode(author.getPassword())); // захешируем пароль чтобы в БД он не хранился
        return repository.save(author);
    }

   public Author findByNickName(String nickName){
        return iAuthorRepository.findByNickName(nickName);
    }

    public Optional<Author> findByNickNameAndPassword(String nickName, String password){
        Author author = iAuthorRepository.findByNickName(nickName);
        if(author != null && passwordEncoder.matches(password,author.getPassword())){ //проверяем хеши паролей
            return  Optional.of(author);
        }
        return  Optional.empty();
    }

    public List<Author> getAll(){
        return repository.getAll();
    }

    public Author getOne(long id){
        return repository.getOne(id);
    }

    public void save(Author author){
        repository.save(author);
    }

    public void update(Author author, long id){
        repository.update(author,id);
    }

    public void delete(long id){
        repository.delete(id);
    }

    public List<Note> getNotes(long id){
        return repository.getNotes(id);
    }
}
