package users.service.impl;

import users.entity.Users;
import users.repository.UsersRepository;
import users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alex on 1/11/16.
 */

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users addUser(Users user) {
        Users savedUser = usersRepository.saveAndFlush(user);

        return savedUser;
    }

    @Override
    public void delete(int id) {
        usersRepository.delete(id);
    }

    @Override
    public Users getByLogin(String login) {
        return usersRepository.findByLogin(login);
    }

    @Override
    public Users getById(int id) {
        return usersRepository.findById(id);
    }

    @Override
    public Users editUser(Users user) {
        return usersRepository.saveAndFlush(user);
    }

    @Override
    public List<Users> getAll() {
        return usersRepository.findAll();
    }
}
