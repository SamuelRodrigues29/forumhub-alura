package br.com.alura.Forumhub.service;

import br.com.alura.Forumhub.domain.User;
import br.com.alura.Forumhub.dto.request.UserDto;
import br.com.alura.Forumhub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserService {

    private final UserRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Long saveUser(UserDto userDTO) {


        if (userDTO.email() == null || userDTO.password() == null) {
            throw new IllegalArgumentException("Login and senha fields are required.");
        }


        if (usuarioRepository.existsByEmail(userDTO.email())) {
            throw new IllegalStateException("User with this login already exists.");
        }


        User user = new User();
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());

        user.setPassword(passwordEncoder.encode(userDTO.password()));


        user.setStatus(true);

        // Save user to repository
        User savedUser = usuarioRepository.save(user);
        return savedUser.getId();
    }

    public Page<dto.response.UserIdDto> getAllUsers(Pageable pageable) {
        Page<User> usersPage = usuarioRepository.findAll(pageable);
        return usersPage.map(user -> new dto.response.UserIdDto(user.getId(), user.getName(), user.getEmail(), user.isStatus()));
    }

    @Transactional
    public void updateUser(Long userId, dto.response.UserIdDto detalhamentoUserDto) {
        // Check if user exists
        Optional<User> optionalUser = usuarioRepository
                .findById(userId);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException(
                    "Usuário não encontrado");
        }

        // Get the existing user
        User existingUser = optionalUser.get();

        // Update user fields
        existingUser.setName(detalhamentoUserDto.nome());
        existingUser.setEmail(detalhamentoUserDto.email());

        // Safely handle status update using Optional methods
        existingUser.setStatus(detalhamentoUserDto.status() ? detalhamentoUserDto.status() : existingUser.isStatus());

        // Save the updated user
        usuarioRepository.save(existingUser);
    }


    @Transactional
    public void deleteUser(Long userId) {
        // Check if user exists
        Optional<User> optionalUser = usuarioRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User not found with ID: " + userId);
        }

        // Get the existing user
        User existingUser = optionalUser.get();

        // Set status to false
        existingUser.setStatus(false);

        // Save the updated user
        usuarioRepository.save(existingUser);
    }

    public User findByEmail(String email) {
        User usuario = (User) usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
        return usuario;
    }

    public Optional<User> detalharUsuario(Long id) {
        Optional<User> optionalUser = usuarioRepository.findById(id)
                .filter(User::isStatus);
        return optionalUser.map(this::mapToDetailsDto);
    }


    private User mapToDetailsDto(User user) {

        return user;
    }

    public Page<dto.response.UserIdDto> getAllUsers(Pageable pageable) {

        return getAllUsers(pageable);
    }
}

