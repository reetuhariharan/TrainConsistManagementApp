import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @DisplayName("UC10: Calculate Total Seating Capacity")
    void testTotalSeatingCapacity() {
        List<Bogie> bogies = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56)
        );

        int total = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        assertEquals(128, total, "Sum should be 72 + 56 = 128");
    }

    @Test
    @DisplayName("UC12: Safety - Cylindrical MUST carry Petroleum")
    void testSafetyCompliance_Valid() {
        List<Bogie> bogies = List.of(
                new Bogie("G1", "Cylindrical", "Petroleum")
        );

        boolean isSafe = bogies.stream()
                .allMatch(b -> !b.getType().equalsIgnoreCase("Cylindrical") ||
                        b.getCargo().equalsIgnoreCase("Petroleum"));

        assertTrue(isSafe, "Cylindrical with Petroleum should be safe");
    }

    @Test
    @DisplayName("UC12: Safety - Cylindrical carrying Coal should FAIL")
    void testSafetyCompliance_Invalid() {
        List<Bogie> bogies = List.of(
                new Bogie("G1", "Cylindrical", "Coal")
        );

        boolean isSafe = bogies.stream()
                .allMatch(b -> !b.getType().equalsIgnoreCase("Cylindrical") ||
                        b.getCargo().equalsIgnoreCase("Petroleum"));

        assertFalse(isSafe, "Cylindrical with Coal is a safety violation");
    }
}