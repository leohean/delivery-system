package org.unesp.util;

import org.unesp.exceptions.InvalidParameterCountException;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class ParameterValidator {
    private final String[] args;

    private final Map<Character, Integer> parametersMap;

    public ParameterValidator(String[] args) {
        if (!hasFourParameters(args)) {
            throw new InvalidParameterCountException("O número de parâmetros passados na execução do programa é inválido.");
        }
        this.args = args;
        parametersMap = new HashMap<>();
        insertValuesIntoMap();
    }

    public Map<Character, Integer> getParametersMap() {
        return this.parametersMap;
    }

    private void insertValuesIntoMap() {
        char identifier;
        int number;

        for (String arg : args) {
            arg = arg.toUpperCase().trim();
            if (isTheValueValid(arg)) {
                number = convertStringToInteger(arg);
                identifier = getIdentifierFromString(arg);

                parametersMap.put(identifier, number);
            }
        }
    }

    private boolean hasFourParameters(String[] args) {
        return args.length == 4;
    }

    private boolean isTheValueValid(String value) {
        if (!value.matches("^[A-Z]\\d+$")) {
            throw new IllegalArgumentException("O parâmetro '"
                    + value
                    + "' é um valor inválido.");
        }
        return true;
    }

    private int convertStringToInteger(String arg) throws NumberFormatException {
        return Integer.parseInt(arg.substring(1));
    }

    private char getIdentifierFromString(String arg) {
        return arg.charAt(0);
    }
}
