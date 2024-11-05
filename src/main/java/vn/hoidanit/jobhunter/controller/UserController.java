package vn.hoidanit.jobhunter.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;

@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  // @GetMapping("/user/create")
  @PostMapping("/user")
  public User createNewUser(@RequestBody User postManUser) {
    User ericUser = this.userService.handleSaveUser(postManUser);
    return ericUser;
  }

  @DeleteMapping("/user/{id}")
  public String deleteNewUser(@PathVariable("id") long id) {
    this.userService.handleDeleteUser(id);
    return "hello";
  }

  @GetMapping("/user/{id}")
  public User getUserById(@PathVariable("id") long id) {
    User user = this.userService.fetchUserById(id);
    return user;
  }

  @GetMapping("/user/")
  public List<User> fetchAllUser() {
    List<User> users = this.userService.fetchAllUser();
    return users;
  }

  @PutMapping("/user")
  public User updateUser(@RequestBody User user) {
    User ericUser = this.userService.handleUpdateUser(user);
    return ericUser;
  }
}
