package com.github.robbo1811.medication_reminder.services;


import com.github.robbo1811.medication_reminder.model.Role;
import com.github.robbo1811.medication_reminder.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleDao roleDao;

    public List<Role> findAll() {
        return roleDao.findAll();

    }
}
