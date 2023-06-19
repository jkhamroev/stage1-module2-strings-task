package com.epam.mjc;

import com.epam.mjc.MethodSignature;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        int bracketOpen = signatureString.indexOf('(');
        int bracketClose = signatureString.indexOf(')');

        if (bracketOpen < bracketClose - 1) {
            String argumentsString = signatureString.substring(bracketOpen + 1, bracketClose);
            String[] argumentsPair = argumentsString.split(", ");
            for (String pair : argumentsPair) {
                String[] temp = pair.split(" ");
                String type = temp[0];
                String name = temp[1];
                arguments.add(new MethodSignature.Argument(type, name));
            }
        }

        String[] firstParts = signatureString.substring(0, bracketOpen).split(" ");
        if (firstParts.length == 2) {
            String name = firstParts[1];
            MethodSignature signature = new MethodSignature(name, arguments);
            signature.setReturnType(firstParts[0]);
            return signature;
        }

        String name = firstParts[2];
        MethodSignature signature = new MethodSignature(name, arguments);
        signature.setReturnType(firstParts[1]);
        signature.setAccessModifier(firstParts[0]);
        return signature;
    }
}
