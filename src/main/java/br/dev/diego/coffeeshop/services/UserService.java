package br.dev.diego.coffeeshop.services;

import br.dev.diego.coffeeshop.models.requests.user.UserInsertRequest;
import br.dev.diego.coffeeshop.models.responses.user.UserFullResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserFullResponse> findAllUsers(Pageable pageable);

    UserFullResponse insertUser(UserInsertRequest request, String role);

    UserFullResponse findByUsername(String username);

    UserFullResponse findByEmail(String mail);

}
