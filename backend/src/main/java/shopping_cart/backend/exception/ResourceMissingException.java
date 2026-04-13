package shopping_cart.backend.exception;

public class ResourceMissingException extends RuntimeException {

    public ResourceMissingException(String message) {
        super(message);
    }
}
