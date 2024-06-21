package br.com.alura.Forumhub.dto.response;

public record UserIdDto(Long id,
                        String name,
                        String email,
                        boolean status) {

    public UserIdDto(Long id, String name, String email) {
        this(id, name, email, false);
    }

}
