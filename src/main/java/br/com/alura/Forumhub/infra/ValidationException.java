package br.com.alura.Forumhub.infra;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super (message);
    }
}
