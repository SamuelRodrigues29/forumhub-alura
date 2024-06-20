package dto.request;

public record UserDto(@NotBlank String nome,
                      @NotBlank @Email String email,
                      @NotBlank String senha) {
}
