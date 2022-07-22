package by.it_academy.afisha_service.utils;

import by.it_academy.afisha_service.dao.entity.AbstractClassifier;

import java.time.LocalDateTime;
import java.util.UUID;

public class DefaultParamsUtil {

    private DefaultParamsUtil() {}

    public static void setDefaultParams(AbstractClassifier classifiers) {
        if (classifiers != null) {
            classifiers.setUuid(UUID.randomUUID());
            classifiers.setDtCreate(LocalDateTime.now());
            classifiers.setDtUpdate(classifiers.getDtCreate());
        }
    }
}
