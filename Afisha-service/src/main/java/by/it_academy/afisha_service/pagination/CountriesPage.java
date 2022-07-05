package by.it_academy.afisha_service.pagination;

import by.it_academy.afisha_service.dao.entity.Country;

import java.util.List;

public class CountriesPage {
    private final int page;
    private final int size;
    private final int totalPages;
    private final long totalElements;
    private final boolean first;
    private final int numberOfElements;
    private final boolean last;
    private final List<Country> content;

    public CountriesPage(int page,
                         int size,
                         int totalPages,
                         long totalElements,
                         boolean first,
                         int numberOfElements,
                         boolean last,
                         List<Country> content) {
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
        this.numberOfElements = numberOfElements;
        this.last = last;
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public boolean isLast() {
        return last;
    }

    public List<Country> getContent() {
        return content;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {
        private int page;
        private int size;
        private int totalPages;
        private long totalElements;
        private boolean first;
        private int numberOfElements;
        private boolean last;
        private List<Country> content;

        public Builder setPage(int page) {
            this.page = page;
            return this;
        }

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Builder setTotalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Builder setTotalElements(long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public Builder setFirst(boolean first) {
            this.first = first;
            return this;
        }

        public Builder setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
            return this;
        }

        public Builder setLast(boolean last) {
            this.last = last;
            return this;
        }

        public Builder setContent(List<Country> content) {
            this.content = content;
            return this;
        }

        public CountriesPage build() {
            return new CountriesPage(page, size, totalPages,totalElements, first, numberOfElements, last, content);
        }
    }
}
