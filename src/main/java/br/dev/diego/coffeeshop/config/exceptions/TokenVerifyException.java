package br.dev.diego.coffeeshop.config.exceptions;

public class TokenVerifyException extends RuntimeException {

    public TokenVerifyException(String message) {
        super(message);
    }
}
