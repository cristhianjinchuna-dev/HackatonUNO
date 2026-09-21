package org.example.hackaton_01.Service;

import org.example.hackaton_01.DTOS.SignUpRequest;
import org.example.hackaton_01.DTOS.TokenResponse;
import org.example.hackaton_01.Model.JwtService;
import org.example.hackaton_01.account.domain.Account;
import org.example.hackaton_01.account.infrastructure.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Lógica de registro (signup) y login (signin).
 * Ver diagrama "Generar Token" en las diapositivas de Security.
 *
 * NOTA: los métodos devuelven un TokenResponse placeholder para que el proyecto compile
 * y arranque sin haber implementado autenticación todavía. Completa los TODO para que
 * signup/signin funcionen de verdad.
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registra una nueva cuenta con la contraseña hasheada y devuelve un token válido.
     */
    public TokenResponse signUp(SignUpRequest request) {
        Account account = userRepository.save(
                new Account(
                        request.getEmail(),
                        passwordEncoder.encode(request.getPassword()),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getRole()
                )
        );
        var token = jwtService.generateToken(account);
        return new TokenResponse(token);
    }

    /**
     * Autentica las credenciales del usuario y devuelve un token válido.
     */
    public TokenResponse signIn(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        var account = userRepository.findByEmail(username).orElseThrow();
        var token = jwtService.generateToken(account);
        return new TokenResponse(token);
    }
}
