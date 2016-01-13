package users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.stereotype.Controller;

import users.entity.Users;
import users.service.UsersService;

/**
 * Created by alex on 1/12/16.
 */

@Controller
@RequestMapping("/")
public class SpringController {

    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<Users>> listAllUsers() {
        List<Users> users = usersService.getAll();
        if(users.isEmpty()){
            return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> getUser(@PathVariable("id") int id) {
        Users user = usersService.getById(id);
        if(user == null) return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Users user, UriComponentsBuilder ucBuilder) {
        usersService.addUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Users> updateUser(@PathVariable("id") int id, @RequestBody Users user) {
        Users currentUser = usersService.getById(id);
        if(currentUser == null) return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);

        currentUser.setLogin(user.getLogin());
        currentUser.setPassword(user.getPassword());
        currentUser.setFio(user.getFio());
        currentUser.setEmail(user.getEmail());

        usersService.editUser(currentUser);
        return new ResponseEntity<Users>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Users> deleteUser(@PathVariable("id") int id) {
        Users user = usersService.getById(id);
        if(user == null) return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);

        usersService.delete(id);

        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
    }
}
