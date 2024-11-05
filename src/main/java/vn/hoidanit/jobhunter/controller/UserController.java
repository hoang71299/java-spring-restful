package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  public String deleteNewUser(@PathVariable("id") long hoidanit) {
    this.userService.handleDeleteUser(hoidanit);
    return "hello";
  }
}
