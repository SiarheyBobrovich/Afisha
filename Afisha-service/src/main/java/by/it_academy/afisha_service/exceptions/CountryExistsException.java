package by.it_academy.afisha_service.exceptions;

public class CountryExistsException extends CategoryExistsException{

    public CountryExistsException() {
        super("Такая страна уже существует");
    }
}
