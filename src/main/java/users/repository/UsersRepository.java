package users.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by alex on 1/11/16.
 */
public interface UsersRepository extends JpaRepository<Users, Integer>{

    Users findByLogin(String login);

    Users findById(int id);

}
