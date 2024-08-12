package test.group.crudFeign.customExceptions;

public class PersonNotFoundException extends NotFoundException {
    public PersonNotFoundException() {
        super("person not found");
    }

    public PersonNotFoundException(String message) {
        super(message);
    }
}
