package vn.hoidanit.jobhunter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.hoidanit.jobhunter.domain.dto.LoginDTO;
import vn.hoidanit.jobhunter.domain.dto.ResLoginDTO;
import vn.hoidanit.jobhunter.util.SecurityUtil;

@RestController
public class AuthController {
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final SecurityUtil securityUtil;

  public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, SecurityUtil securityUtil) {
    this.authenticationManagerBuilder = authenticationManagerBuilder;
    this.securityUtil = securityUtil;
  }

  @PostMapping("/login")
  public ResponseEntity<ResLoginDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
    // Nạp input gồm username/password vào Security
    // Nạp input gồm username/password vào Security
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        loginDTO.getUsername(), loginDTO.getPassword());
    // xác thực người dùng => cần viết hàm loadUserByUsername
    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    // create a token
    String accessToken = this.securityUtil.createToken(authentication);

    ResLoginDTO res = new ResLoginDTO();
    res.setAccessToken(accessToken);
    return ResponseEntity.ok().body(res);
  }
}