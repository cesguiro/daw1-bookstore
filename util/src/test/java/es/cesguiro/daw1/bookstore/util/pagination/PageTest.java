package es.cesguiro.daw1.bookstore.util.pagination;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PageTest {

    @Test
    @DisplayName("Test total pages with 0 elements should return 0")
    void testTotalPagesWithZeroElements() {
        Page<String> page = new Page<>(Collections.emptyList(), 1, 10, 0);
        assertEquals(0, page.totalPages(), "Total pages should be 0");
    }

    @Test
    @DisplayName("Test total pages with 1 element should return 1")
    void testTotalPagesWithOneElement() {
        Page<String> page = new Page<>(List.of("element"), 1, 10, 1);
        assertEquals(1, page.totalPages(), "Total pages should be 1");
    }

    @Test
    @DisplayName("Test total pages with 10 elements and page size 10 should return 1")
    void testTotalPagesWithTenElementsAndPageSizeTen() {
        Page<String> page = new Page<>(List.of("element1", "element2", "element3", "element4", "element5", "element6", "element7", "element8", "element9", "element10"), 1, 10, 10);
        assertEquals(1, page.totalPages(), "Total pages should be 1");
    }

    @Test
    @DisplayName("Test negative page should throw RuntimeException")
    void testNegativePage() {
        assertThrows(RuntimeException.class, () -> new Page<>(List.of("element"), -1, 10, 100));
    }

    @Test
    @DisplayName("Test negative page size should throw RuntimeException")
    void testNegativePageSize() {
        assertThrows(RuntimeException.class, () -> new Page<>(List.of("element"), 1, -1, 1));
    }

    @Test
    @DisplayName("Test empty data with non-zero total elements")
    void testEmptyDataWithNonZeroTotalElements() {
        Page<String> page = new Page<>(Collections.emptyList(), 1, 10, 20);
        assertEquals(2, page.totalPages(), "Total pages should be calculated based on totalElements");
    }

    @Test
    @DisplayName("Test total elements 0 with page size 1 should return 0 total pages")
    void testZeroTotalElementsWithPageSizeOne() {
        Page<String> page = new Page<>(Collections.emptyList(), 1, 1, 0);
        assertEquals(0, page.totalPages(), "Total pages should be 0");
    }

    @Test
    @DisplayName("Test total elements 1 with page size 1 should return 1 total page")
    void testOneTotalElementWithPageSizeOne() {
        Page<String> page = new Page<>(List.of("element"), 1, 1, 1);
        assertEquals(1, page.totalPages(), "Total pages should be 1");
    }

    @Test
    @DisplayName("Test large total elements with page size")
    void testLargeTotalElements() {
        Page<String> page = new Page<>(Collections.emptyList(), 1, 10, 1_000_000);
        assertEquals(100_000, page.totalPages(), "Total pages should be correctly calculated for large inputs");
    }

    @Test
    @DisplayName("Test data size exceeds page size should throw RuntimeException")
    void testDataSizeExceedsPageSize() {
        assertThrows(RuntimeException.class,
                () -> new Page<>(List.of("element1", "element2", "element3"), 1, 2, 3));
    }

    @Test
    @DisplayName("Test data size equals page size should not throw exception")
    void testDataSizeEqualsPageSize() {
        Page<String> page = new Page<>(List.of("element1", "element2"), 1, 2, 2);
        assertEquals(1, page.totalPages(), "Total pages should be 1");
    }

    @Test
    @DisplayName("Test data size less than page size should not throw exception")
    void testDataSizeLessThanPageSize() {
        Page<String> page = new Page<>(List.of("element1"), 1, 2, 1);
        assertEquals(1, page.totalPages(), "Total pages should be 1");
    }

    @Test
    @DisplayName("Test null data should throw RuntimeException")
    void testNullData() {
        assertThrows(RuntimeException.class, () -> new Page<>(null, 1, 10, 10));
    }

}