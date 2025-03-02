package es.cesguiro.daw1.bookstore.application;

import java.util.List;

public class PaginationUtil {

    private PaginationUtil() {
    }

    private static String generatePreviousPageLink(int page, int size, String resourcePath) {
        if (page > 1) {
            return resourcePath + "?page=" + (page - 1) + "&size=" + size;
        }
        return null;
    }

    private static String generateNextPageLink(int page, int size, int totalPages, String resourcePath) {
        if (page < totalPages) {
            return resourcePath + "?page=" + (page + 1) + "&size=" + size;
        }
        return null;
    }

    /**
     * Create a new PagedCollectionResponse
     *
     * @param data        List of items
     * @param page        Page number
     * @param pageSize    Page size
     * @param totalItems  Total number of items
     * @param totalPages  Total number of pages
     * @param resourcePath Resource path
     * @param <T>         Type of items
     * @return PagedCollectionResponse
     */
    public static <T> PageResponse<T> generatePageResponse(List<T> data, int page, int pageSize, long totalItems, int totalPages, String resourcePath) {
        return new PageResponse<>(
                data,
                page,
                pageSize,
                totalItems,
                totalPages,
                generatePreviousPageLink(page, pageSize, resourcePath),
                generateNextPageLink(page, pageSize, totalPages, resourcePath)
        );
    }
}
