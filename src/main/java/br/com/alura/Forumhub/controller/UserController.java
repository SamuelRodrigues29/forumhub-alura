package br.com.alura.Forumhub.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private service.UserService usuarioService;

    @PostMapping("")
    @Transactional
    public ResponseEntity<String> cadastrarUsuario(@RequestBody @Valid dto.request.UserDto userDTO,
                                                   UriComponentsBuilder uriComponentsBuilder) {
        Long userId = usuarioService.saveUser(userDTO);
        var uri = uriComponentsBuilder.path("/usuario/{id}")
                .buildAndExpand(userId).toUri();
        return ResponseEntity.created(uri)
                .body("Usuário registrado com sucesso. Id: " + userId);
    }

    @GetMapping("")
    public ResponseEntity<Page<dto.response.UserIdDto>> listarUsuarios(Pageable pageable) {
        Page<dto.response.UserIdDto> usersPage = usuarioService.getAllUsers(pageable);
        return ResponseEntity.ok(usersPage);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<String> atualizarUsuario(
            @PathVariable Long userId,
            @RequestBody dto.response.UserIdDto usuarioInfo) {

        usuarioService.updateUser(userId, usuarioInfo);
        return ResponseEntity.ok("Usuário atualizado com sucesso.");

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUsuarios(@PathVariable Long userId) {

        usuarioService.deleteUser(userId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<dto.response.UserDetailsDto> detalharUsuario(@PathVariable Long id) {
        Optional<dto.response.UserDetailsDto> detalheOptional = usuarioService.detalharUsuario(id);

        return detalheOptional
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}

