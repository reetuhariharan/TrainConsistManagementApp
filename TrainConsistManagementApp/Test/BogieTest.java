import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;

class BogieGroupingTest {

    @Test
    void testGrouping_CorrectlyCategorizesByName() {
        // Arrange
        List<Bogie> bogies = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("Sleeper", 70)
        );

        // Act
        Map<String, List<Bogie>> grouped = bogies.stream()
                .collect(Collectors.groupingBy(Bogie::getName));

        // Assert
        assertEquals(2, grouped.size(), "Should have 2 distinct groups");
        assertTrue(grouped.containsKey("Sleeper"));
        assertTrue(grouped.containsKey("AC Chair"));
        assertEquals(2, grouped.get("Sleeper").size(), "Sleeper group should have 2 bogies");
        assertEquals(1, grouped.get("AC Chair").size(), "AC Chair group should have 1 bogie");
    }

    @Test
    void testGrouping_EmptyList_ReturnsEmptyMap() {
        // Arrange
        List<Bogie> bogies = Collections.emptyList();

        // Act
        Map<String, List<Bogie>> grouped = bogies.stream()
                .collect(Collectors.groupingBy(Bogie::getName));

        // Assert
        assertTrue(grouped.isEmpty(), "Grouping an empty list should return an empty map");
    }

    @Test
    void testGrouping_MaintainsDataIntegrity() {
        // Arrange
        Bogie s1 = new Bogie("Sleeper", 72);
        List<Bogie> bogies = List.of(s1);

        // Act
        Map<String, List<Bogie>> grouped = bogies.stream()
                .collect(Collectors.groupingBy(Bogie::getName));

        // Assert
        Bogie result = grouped.get("Sleeper").get(0);
        assertEquals(72, result.getCapacity(), "Bogie capacity should remain the same after grouping");
    }

    @Test
    void testGrouping_OriginalListIsUnchanged() {
        // Arrange
        List<Bogie> bogies = new ArrayList<>(List.of(new Bogie("Sleeper", 72)));
        int originalSize = bogies.size();

        // Act
        bogies.stream().collect(Collectors.groupingBy(Bogie::getName));

        // Assert
        assertEquals(originalSize, bogies.size(), "The stream operation should not modify the source list");
    }
}