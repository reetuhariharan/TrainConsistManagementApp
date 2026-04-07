import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BogieTest {

    private List<Bogie> bogieList;

    @BeforeEach
    void setUp() {

        bogieList = Arrays.asList(
                new Bogie("B1", 72),
                new Bogie("B2", 65),
                new Bogie("B3", 80),
                new Bogie("B4", 70)
        );
    }

    @Test
    void testFilter_CapacityGreaterThanThreshold() {
        int threshold = 70;

        List<Bogie> filteredList = bogieList.stream()
                .filter(b -> b.getCapacity() > threshold)
                .collect(Collectors.toList());

        assertEquals(2, filteredList.size());
        assertTrue(filteredList.stream()
                .allMatch(b -> b.getCapacity() > threshold));
    }

    @Test
    void testFilter_CapacityEqualToThreshold() {
        int threshold = 70;

        List<Bogie> filteredList = bogieList.stream()
                .filter(b -> b.getCapacity() > threshold)
                .collect(Collectors.toList());

        assertFalse(filteredList.stream()
                .anyMatch(b -> b.getCapacity() == threshold));
    }

    @Test
    void testFilter_NoBogiesMatching() {
        int highThreshold = 100;

        List<Bogie> filteredList = bogieList.stream()
                .filter(b -> b.getCapacity() > highThreshold)
                .collect(Collectors.toList());

        assertTrue(filteredList.isEmpty(),
                "The filtered list should be empty.");
    }

    @Test
    void testFilter_OriginalListUnchanged() {
        int initialSize = bogieList.size();

        bogieList.stream()
                .filter(b -> b.getCapacity() > 70)
                .collect(Collectors.toList());

        assertEquals(initialSize, bogieList.size(),
                "The original list should remain unchanged.");
    }
}