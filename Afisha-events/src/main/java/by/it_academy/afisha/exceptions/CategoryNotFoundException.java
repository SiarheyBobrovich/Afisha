package by.it_academy.afisha.exceptions;

public class CategoryNotFoundException extends IllegalStateException{
    public CategoryNotFoundException() {
        super("Категория не найдена: проверьте uuid категории");
    }
}
