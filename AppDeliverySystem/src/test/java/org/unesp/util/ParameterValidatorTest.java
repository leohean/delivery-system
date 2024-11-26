package org.unesp.util;

import org.junit.jupiter.api.Test;
import org.unesp.exceptions.InvalidParameterCountException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParameterValidatorTest {

    @Test
    void shouldThrowExceptionForFiveParameters() {
        String[] args = {"S5", "C3", "P50", "A5", "Z50"};

        assertThrows(InvalidParameterCountException.class, () -> {
            new ParameterValidator(args);
        });
    }

    @Test
    void shouldThrowExceptionForTreeParameters() {
        String[] args = {"S5", "C3", "P50"};

        assertThrows(InvalidParameterCountException.class, () -> {
            new ParameterValidator(args);
        });
    }

    @Test
    void shouldValidateIfTheValueHasIdentifierAndInteger() {
        String[] args = {"S5", "3", "P50", "A5"};

        assertThrows(IllegalArgumentException.class, () -> {
            new ParameterValidator(args);
        });
    }

    @Test
    void shouldValidateIfPIsGreaterThanAThatIsGreaterThanC() {
        String[] args = {"S5", "C7", "P1", "A4"};

        assertThrows(IllegalArgumentException.class, () -> {
            new ParameterValidator(args);
        });
    }

    @Test
    void mapShouldHaveFourValidValues() {
        String[] args = {"S5", "C3", "P50", "A5"};
        var parameterValidator = new ParameterValidator(args);

        Map<Character, Integer> parametersMap = parameterValidator.getParametersMap();
        assertEquals(4, parametersMap.size());
    }

    @Test
    void mapShouldReturnFiftyToKeyP() {
        String[] args = {"S5", "C3", "P50", "A5"};
        var parameterValidator = new ParameterValidator(args);
        Map<Character, Integer> parametersMap = parameterValidator.getParametersMap();

        assertEquals(50, parametersMap.get('P'));
    }

    @Test
    void mapShouldReturnFiveToKeyS() {
        String[] args = {"S5", "C3", "P50", "A5"};
        var parameterValidator = new ParameterValidator(args);
        Map<Character, Integer> parametersMap = parameterValidator.getParametersMap();

        assertEquals(5, parametersMap.get('S'));
    }

    @Test
    void mapShouldReturnTreeToKeyC() {
        String[] args = {"S5", "C3", "P50", "A5"};
        var parameterValidator = new ParameterValidator(args);
        Map<Character, Integer> parametersMap = parameterValidator.getParametersMap();

        assertEquals(3, parametersMap.get('C'));
    }

    @Test
    void mapShouldReturnFiveToKeyA() {
        String[] args = {"S5", "C3", "P50", "A5"};
        var parameterValidator = new ParameterValidator(args);
        Map<Character, Integer> parametersMap = parameterValidator.getParametersMap();

        assertEquals(5, parametersMap.get('A'));
    }

}