import java.util.Scanner;

public class Calculator {
    private static final String MULTIPLICATION = "*";
    private static final String DIVISION = "/";
    private static final String ADDITION = "+";
    private static final String SUBTRACTION = "-";

    public static void main(String[] args) {
        System.out.println("Enter an expression...");
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine().trim();

        try {
            System.out.println(calc(expression));
        } catch (Exception a) {
            System.out.println(a.getMessage());
        }
    }

    public static String calc(String input) throws Exception {
        String[] values = input.split(" ");
        int result;
        int firstNumber;
        int secondNumber;
        String sing = values[1];

        if (validation(values)) {
            if (isRomanNumber(values[0])) {
                firstNumber = convertRomanToArabic(values[0]);
                secondNumber = convertRomanToArabic(values[2]);

                result = getResultOperation(firstNumber, secondNumber, sing);

                if (result < 0) {
                    throw new Exception("Roman numbers can't be negative.");
                }

                return convertArabicToRoman(result);

            } else if (!isRomanNumber(values[0])) {
                firstNumber = Integer.parseInt(values[0]);
                secondNumber = Integer.parseInt(values[2]);
                result = getResultOperation(firstNumber, secondNumber, sing);

                return String.valueOf(result);
            }
        }
        throw new Exception("All the numbers have to be only one type.");
    }

    private static boolean validation(String[] values) throws Exception {
        int amountValues = 3;
        int firstNumber;
        int secondNumber;

        if (values.length < amountValues || values.length > amountValues) {
            throw new IllegalArgumentException("Amount of operands and operators is less or more than 3.");
        }

        if (isRomanNumber(values[0]) && isRomanNumber(values[2])) {
            firstNumber = convertRomanToArabic(values[0]);
            secondNumber = convertRomanToArabic(values[2]);

            if (firstNumber < 1 || firstNumber > 10 || secondNumber < 1 || secondNumber > 10) {
                throw new Exception("Numbers have to be more than 0 and less than 11.");
            }
            return true;
        }

        if (!isRomanNumber(values[0]) && !isRomanNumber(values[2])) {
            firstNumber = Integer.parseInt(values[0]);
            secondNumber = Integer.parseInt(values[2]);

            if (firstNumber < 1 || firstNumber > 10 || secondNumber < 1 || secondNumber > 10) {
                throw new Exception("Numbers have to be more than 0 and less than 11.");
            }
            return true;
        }
        return  false;
    }

    private static boolean isRomanNumber(String value) {
        Number[] numbers = Number.values();

        for (Number number : numbers) {

            if (value.equals(number.toString())) {
                return true;
            }
        }
        return false;
    }

    private static int convertRomanToArabic(String value) throws Exception {
        Number[] numbers = Number.values();
        int maxNumber = 10;

        for (int i = 0; i < maxNumber; i++) {

            if (value.equals(numbers[i].toString())) {
                return numbers[i].getNumber();
            }
        }
        throw new Exception("Numbers have to be more than 0 and less than 11.");
    }

    private static String convertArabicToRoman(int value) throws Exception {
        Number[] numbers = Number.values();
        if (value == 0) {
            throw new Exception("The number doesn't have to be a zero.");
        }

        for (int i = 0; i < numbers.length; i++) {
            if (value == i + 1) {
                return numbers[i].toString();
            }
        }
        throw new Exception("Numbers have to be more than 0 and less than 11.");
    }

    private static int getResultOperation(int firstNumber, int secondNumber, String sign) throws Exception {
        return switch (sign) {
            case MULTIPLICATION -> firstNumber * secondNumber;
            case ADDITION -> firstNumber + secondNumber;
            case DIVISION -> firstNumber / secondNumber;
            case SUBTRACTION -> firstNumber - secondNumber;
            default -> throw new Exception("The operator has to be or \"+\" or \"-\" or \"/\" or \"*\".");
        };
    }
}