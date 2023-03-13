package br.dev.diego.coffeeshop.controllers;

import br.dev.diego.coffeeshop.models.requests.user.UserInsertRequest;
import br.dev.diego.coffeeshop.models.responses.user.UserFullResponse;
import br.dev.diego.coffeeshop.services.UserService;
import br.dev.diego.coffeeshop.utils.GeneralUtilities;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService service;

    @GetMapping
    public ResponseEntity<Page<UserFullResponse>> getAllUsers(Pageable pageable) {
        Page<UserFullResponse> users = service.findAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/find-by-username/{username}")
    public ResponseEntity<UserFullResponse> getByUsername(@PathVariable String username) {
        UserFullResponse user = service.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<UserFullResponse> getByEmail(@PathVariable String email) {
        UserFullResponse user = service.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{role}")
    public ResponseEntity<UserFullResponse> save(
            @RequestBody @Valid UserInsertRequest request,
            @PathVariable String role) {
        UserFullResponse obj = service.insertUser(request, role);
        URI uri = GeneralUtilities.toUri("/{id}", obj.id());
        log.info("Usuário id: {} cadastrado com sucesso.", obj.id());
        return ResponseEntity.created(uri).body(obj);
    }

}
