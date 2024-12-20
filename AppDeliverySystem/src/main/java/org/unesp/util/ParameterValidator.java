package org.unesp.util;

import org.unesp.exceptions.InvalidParameterCountException;

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
        checkIfThereIsAParameterZero();
        checkIfValuesAreValid();
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
        if (!value.matches("^[SCPA]\\d+$")) {
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

    private void checkIfValuesAreValid() {
        if (!(this.parametersMap.get('P') > this.parametersMap.get('A')
                && this.parametersMap.get('A') > this.parametersMap.get('C'))) {
            throw new IllegalArgumentException("Os valores informados devem seguir a seguinte regra: 'P >> A >> C'");
        }
    }

    private void checkIfThereIsAParameterZero() {
        if (this.parametersMap.get('P') == 0 || this.parametersMap.get('A') == 0
                || this.parametersMap.get('A') == 0 || this.parametersMap.get('C') ==0 ) {
            throw new IllegalArgumentException("Nenhum dos parâmetros de entrada pode ser zero");
        }
    }
}

