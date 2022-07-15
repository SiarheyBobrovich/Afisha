package by.it_academy.afisha_service.pagination;

import by.it_academy.afisha_service.dto.ResponseCategoryDto;

import java.util.List;

public class ResponseCategoryPage extends ResponsePage<ResponseCategoryDto>{

    public ResponseCategoryPage(int page,
                                int size,
                                int totalPages,
                                long totalElements,
                                boolean first,
                                int numberOfElements,
                                boolean last,
                                List<ResponseCategoryDto> content) {
        super(page, size, totalPages, totalElements,
                first, numberOfElements, last, content
        );
    }
}
