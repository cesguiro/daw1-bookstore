package es.cesguiro.daw1.bookstore.util.pagination;

import es.cesguiro.daw1.bookstore.util.exception.Error400;
import es.cesguiro.daw1.bookstore.util.exception.Error500;

import java.util.List;

public record Page<T>(
        List<T> data,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {

    public Page(List<T> data, int pageNumber, int pageSize, long totalElements) {
        this(
                validateDataSize(data, pageSize),
                validatePageNumber(pageNumber),
                validatePageSize(pageSize),
                totalElements,
                (int) Math.ceil((double) totalElements / pageSize)
        );
    }

    private static <T> List<T> validateDataSize(List<T> data, int pageSize) {
        if (data == null) {
            throw new Error500("Data cannot be null");
        }
        if (data.size() > pageSize) {
            throw new Error500("Data size cannot be greater than page size");
        }
        return data;
    }

    private static int validatePageNumber(int pageNumber) {
        if (pageNumber < 1) {
            throw new Error400("Page number cannot be less than one");
        }
        return pageNumber;
    }

    private static int validatePageSize(int pageSize) {
        if (pageSize <= 0) {
            throw new Error400("Page size must be greater than zero");
        }
        return pageSize;
    }
}
