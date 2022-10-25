package com.example.hibernatetest.service;

import com.example.hibernatetest.entity.Role;
import com.example.hibernatetest.reposiroty.Interface.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final IRoleRepository iRoleRepository;

    @Autowired
    public RoleService(IRoleRepository iRoleRepository) {
        this.iRoleRepository = iRoleRepository;
    }

    public Role findByName(String name){
        return  iRoleRepository.findByName(name);
    }
}
