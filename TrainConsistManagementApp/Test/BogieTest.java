import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BogieTest {

    @Test
    void testTotalSeatingCapacity_MultipleBogies() {
        // Arrange
        List<Bogie> bogies = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("First Class", 24),
                new Bogie("Sleeper", 70)
        );

        // Act
        int total = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        // Assert
        assertEquals(222, total, "Total capacity should be the sum of all bogies");
    }

    @Test
    void testTotalSeatingCapacity_EmptyList() {
        List<Bogie> bogies = new ArrayList<>();

        int total = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        assertEquals(0, total, "Capacity of an empty train should be 0");
    }

    @Test
    void testTotalSeatingCapacity_SingleBogie() {
        List<Bogie> bogies = List.of(new Bogie("Sleeper", 72));

        int total = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        assertEquals(72, total);
    }

    @Test
    void testOriginalListUnchanged() {
        List<Bogie> bogies = new ArrayList<>(List.of(new Bogie("Sleeper", 72)));
        int originalSize = bogies.size();

        // Perform stream operation
        bogies.stream().map(Bogie::getCapacity).reduce(0, Integer::sum);

        assertEquals(originalSize, bogies.size(), "Stream operations should not modify the source list");
    }
}