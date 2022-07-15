package by.it_academy.afisha_service.pagination;

import by.it_academy.afisha_service.dto.ResponseCountryDto;

import java.util.List;

public class ResponseCountryPage extends ResponsePage<ResponseCountryDto>{

    public ResponseCountryPage(int page,
                               int size,
                               int totalPages,
                               long totalElements,
                               boolean first,
                               int numberOfElements,
                               boolean last,
                               List<ResponseCountryDto> content) {
        super(page, size, totalPages, totalElements,
                first, numberOfElements, last, content
        );
    }
}
