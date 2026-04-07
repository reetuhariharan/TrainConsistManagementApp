import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class TrainValidationTest {

    // Regex constants based on your UC11 requirements
    private static final String TRAIN_ID_REGEX = "TRN-\\d{4}";
    private static final String CARGO_CODE_REGEX = "PET-[A-Z]{2}";

    @ParameterizedTest
    @DisplayName("Valid Train IDs should return true")
    @ValueSource(strings = {"TRN-1234", "TRN-0000", "TRN-9999"})
    void testValidTrainId(String input) {
        assertTrue(Pattern.matches(TRAIN_ID_REGEX, input),
                "Should be valid: " + input);
    }

    @ParameterizedTest
    @DisplayName("Invalid Train IDs should return false")
    @ValueSource(strings = {"trn-1234", "TRN-123", "TRN-12345", "ABC-1234", "TRN1234"})
    void testInvalidTrainId(String input) {
        assertFalse(Pattern.matches(TRAIN_ID_REGEX, input),
                "Should be invalid: " + input);
    }

    @ParameterizedTest
    @DisplayName("Valid Cargo Codes should return true")
    @ValueSource(strings = {"PET-AB", "PET-ZZ", "PET-XY"})
    void testValidCargoCode(String input) {
        assertTrue(Pattern.matches(CARGO_CODE_REGEX, input),
                "Should be valid: " + input);
    }

    @ParameterizedTest
    @DisplayName("Invalid Cargo Codes should return false")
    @ValueSource(strings = {"pet-ab", "PET-A", "PET-ABC", "OIL-AB", "PET12"})
    void testInvalidCargoCode(String input) {
        assertFalse(Pattern.matches(CARGO_CODE_REGEX, input),
                "Should be invalid: " + input);
    }
}