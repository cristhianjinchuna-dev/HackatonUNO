package org.example.hackaton_01.account.application;

import org.example.hackaton_01.account.domain.User;
import org.example.hackaton_01.account.dto.UserInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.hackaton_01.account.infrastructure.UserRepository;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final UserRepository userRepository;

    public AccountController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Request-level authorization: cualquier usuario autenticado (USER o ADMIN) puede acceder.
    // Ver reglas en SecurityConfig.filterChain().
    @GetMapping("/private")
    public ResponseEntity<UserInfoDto> getPrivateInfo() {
        User user = currentAccount();
        return ResponseEntity.ok(new UserInfoDto(user.getUsername(), user.getRole().name()));
    }

    // Request-level authorization: solo ADMIN puede acceder.
    // Ver reglas en SecurityConfig.filterChain().
    @GetMapping("/admin")
    public ResponseEntity<UserInfoDto> getAdminInfo() {
        User user = currentAccount();
        return ResponseEntity.ok(new UserInfoDto(user.getUsername(), user.getRole().name()));
    }

    // Method-level authorization con @PreAuthorize.
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDto> getById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(new UserInfoDto(user.getUsername(), user.getRole().name()));
    }

    private User currentAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) auth.getPrincipal();
        return (User) principal;
    }
}
