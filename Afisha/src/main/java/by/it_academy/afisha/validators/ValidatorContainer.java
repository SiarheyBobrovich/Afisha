package by.it_academy.afisha.validators;

import by.it_academy.afisha.validators.api.IValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ValidatorContainer {

     private final Map<Class<?> ,IValidator<?>> classIValidatorMap;

    public ValidatorContainer() {
        classIValidatorMap = new HashMap<>();
    }

    public <T> ValidatorContainer addValidator(Class<T> c , IValidator<T> validator) {
        classIValidatorMap.put(c, validator);
        return this;
    }

    public <T> IValidator<T> getValidatorByClass(T o) {
        IValidator<T> iValidator = (IValidator<T>) classIValidatorMap.get(o.getClass());

        if (iValidator == null) {
            throw new NoSuchElementException("Не найден валидатор для класаа: " + o.getClass());
        }

        return iValidator;
    }
}
