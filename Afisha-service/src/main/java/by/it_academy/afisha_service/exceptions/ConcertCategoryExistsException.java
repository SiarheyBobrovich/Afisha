package by.it_academy.afisha_service.exceptions;

public class ConcertCategoryExistsException extends CategoryExistsException{

    public ConcertCategoryExistsException() {
        super("Такая категория уже существует");
    }
}
