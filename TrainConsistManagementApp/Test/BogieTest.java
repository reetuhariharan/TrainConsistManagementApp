import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BogieTest {

    private List<Bogie> bogies;

    @BeforeEach
    void setUp() {
        // Initialize with a moderate dataset for functional testing
        bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 80));
    }

    @Test
    void testResultsAreIdentical() {
        // 1. Loop Result
        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.getCapacity() > 60) {
                loopResult.add(b);
            }
        }

        // 2. Stream Result
        List<Bogie> streamResult = bogies.stream()
                .filter(b -> b.getCapacity() > 60)
                .collect(Collectors.toList());

        // Assertions
        assertEquals(loopResult.size(), streamResult.size(), "Both methods should find the same number of bogies.");
        assertEquals(loopResult.get(0).getName(), streamResult.get(0).getName(), "The content should match.");
    }

    @Test
    void testTimingIntegrity() {
        // Verifies that nanoTime is capturing valid durations
        long start = System.nanoTime();

        // Minor operation
        bogies.stream().filter(b -> b.getCapacity() > 10).collect(Collectors.toList());

        long end = System.nanoTime();
        long duration = end - start;

        assertTrue(duration > 0, "Execution time should be a positive value.");
    }

    @Test
    void testPerformanceWithLargeDataset() {
        // Scales up the data to mimic the Main class scenario
        List<Bogie> largeData = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            largeData.add(new Bogie("Sleeper", 72));
        }

        // Ensure stream can process large data without throwing exceptions
        assertDoesNotThrow(() -> {
            List<Bogie> result = largeData.stream()
                    .filter(b -> b.getCapacity() > 60)
                    .collect(Collectors.toList());
            assertFalse(result.isEmpty());
        });
    }
}