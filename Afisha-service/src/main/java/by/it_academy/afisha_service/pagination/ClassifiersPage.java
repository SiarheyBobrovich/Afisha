package by.it_academy.afisha_service.pagination;

import java.util.List;

public class ClassifiersPage<T> {
    private final int page;
    private final int size;
    private final int totalPages;
    private final long totalElements;
    private final boolean first;
    private final int numberOfElements;
    private final boolean last;
    private final List<T> content;

    public ClassifiersPage(int page,
                           int size,
                           int totalPages,
                           long totalElements,
                           boolean first,
                           int numberOfElements,
                           boolean last,
                           List<T> content) {
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

    public List<T> getContent() {
        return content;
    }
}
