package br.dev.diego.coffeeshop.services.impl;

import br.dev.diego.coffeeshop.models.entities.User;
import br.dev.diego.coffeeshop.models.requests.user.UserInsertRequest;
import br.dev.diego.coffeeshop.models.responses.user.UserFullResponse;
import br.dev.diego.coffeeshop.repositories.UserRepository;
import br.dev.diego.coffeeshop.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Override
    public Page<UserFullResponse> findAllUsers(Pageable pageable) {
        return repository.findAll(pageable).map(UserFullResponse::new);
    }

    @Override
    public UserFullResponse insertUser(UserInsertRequest request, String role) {
        return new UserFullResponse(repository.save(new User(request, role)));
    }

    @Override
    public UserFullResponse findByUsername(String username) {
        User user = repository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("FindByUsuario - Usuário [" + username + "] não encontrado."));
        return new UserFullResponse(user);
    }

    @Override
    public UserFullResponse findByEmail(String mail) {
        User user = repository.findByEmail(mail).orElseThrow(() ->
                new UsernameNotFoundException("FindByEmail - E-mail: [" + mail + "] não encontrado."));
        return new UserFullResponse(user);
    }
}
