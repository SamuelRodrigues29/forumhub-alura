package dto.response;

public record UserIdDto(Long id,
                        String nome,
                        String email,
                        boolean status) {

    public UserIdDto(Long id, String nome, String email) {
        this(id, nome, email, false);
    }

}
