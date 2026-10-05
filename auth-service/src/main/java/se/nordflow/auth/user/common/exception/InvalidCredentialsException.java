package se.nordflow.auth.user.common.exception;

// Same message for wrong email and wrong password,
// so nobody can find out which emails are registered
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Wrong email or password");
    }
}
