package users.service;

import users.entity.Users;
import java.util.List;

/**
 * Created by alex on 1/11/16.
 */
public interface UsersService {

    Users addUser(Users user);
    void delete(int id);
    Users getByLogin(String login);
    Users getById(int id);
    Users editUser(Users user);
    List<Users> getAll();

}
