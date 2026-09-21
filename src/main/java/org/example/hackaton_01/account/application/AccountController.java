package org.example.hackaton_01.account.application;

import org.example.hackaton_01.account.domain.Account;
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
import org.example.hackaton_01.account.infrastructure.AccountRepository;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Request-level authorization: cualquier usuario autenticado (USER o ADMIN) puede acceder.
    // Ver reglas en SecurityConfig.filterChain().
    @GetMapping("/private")
    public ResponseEntity<UserInfoDto> getPrivateInfo() {
        Account account = currentAccount();
        return ResponseEntity.ok(new UserInfoDto(account.getUsername(), account.getRole().name()));
    }

    // Request-level authorization: solo ADMIN puede acceder.
    // Ver reglas en SecurityConfig.filterChain().
    @GetMapping("/admin")
    public ResponseEntity<UserInfoDto> getAdminInfo() {
        Account account = currentAccount();
        return ResponseEntity.ok(new UserInfoDto(account.getUsername(), account.getRole().name()));
    }

    // Method-level authorization con @PreAuthorize.
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDto> getById(@PathVariable Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(new UserInfoDto(account.getUsername(), account.getRole().name()));
    }

    private Account currentAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) auth.getPrincipal();
        return (Account) principal;
    }
}
