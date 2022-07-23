package by.it_academy.afisha.exceptions;

public class CountryNotFoundException extends IllegalStateException{

    public CountryNotFoundException() {
        super("Страна не найдена: проверьте uuid страны");
    }
}