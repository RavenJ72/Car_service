package web.development.services.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("Could not find user with this parameters.");
    }
}
