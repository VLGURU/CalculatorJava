import java.util.Scanner;

class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Арифметические операции");
            System.out.println("2. Перевод между системами счисления");
            System.out.println("0. Выход");
            int choice = scanner.nextInt();

            if (choice == 0) {
                break;
            }

            switch (choice) {
                case 1:
                    performArithmeticOperations(scanner);
                    break;
                case 2:
                    performBaseConversions(scanner);
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    static void performArithmeticOperations(Scanner scanner) {
        System.out.println("Введите операцию (+, -, *, /):");
        char operation = scanner.next().charAt(0);

        System.out.println("Введите до 5 чисел (разделяйте пробелом, нажмите Enter для завершения):");
        double[] numbers = new double[5];
        int count = 0;

        // Считываем числа
        while (count < 5) {
            if (scanner.hasNextDouble()) {
                numbers[count] = scanner.nextDouble();
                count++;
            } else if (scanner.hasNextLine()) {
                // Если введена пустая строка, завершаем ввод
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    break;
                }
            }
        }

        // Проверяем, ввел ли пользователь хотя бы одно число
        if (count == 0) {
            System.out.println("Не введено ни одного числа.");
            return;
        }

        double result = 0;
        boolean validOperation = true;

        switch (operation) {
            case '+':
                result = add(numbers, count);
                break;
            case '-':
                result = subtract(numbers, count);
                break;
            case '*':
                result = multiply(numbers, count);
                break;
            case '/':
                result = divide(numbers, count);
                if (Double.isNaN(result)) {
                    validOperation = false;
                }
                break;
            default:
                System.out.println("Операция не найдена.");
                validOperation = false;
                break;
        }

        if (validOperation) {
            System.out.println("Результат: " + result);
        }
    }

    static double add(double[] numbers, int count) {
        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    static double subtract(double[] numbers, int count) {
        double result = numbers[0];
        for (int i = 1; i < count; i++) {
            result -= numbers[i];
        }
        return result;
    }

    static double multiply(double[] numbers, int count) {
        double product = 1;
        for (int i = 0; i < count; i++) {
            product *= numbers[i];
        }
        return product;
    }

    static double divide(double[] numbers, int count) {
        double result = numbers[0];
        for (int i = 1; i < count; i++) {
            if (numbers[i] == 0) {
                System.out.println("Делить на ноль нельзя.");
                return Double.NaN;
            }
            result /= numbers[i];
        }
        return result;
    }

    static void performBaseConversions(Scanner scanner) {
        System.out.println("Введите число:");
        int number = scanner.nextInt();
        
        System.out.println("Выберите систему счисления для перевода:");
        System.out.println("1. Двоичная");
        System.out.println("2. Восьмеричная");
        System.out.println("3. Шестнадцатеричная");
        int baseChoice = scanner.nextInt();
        switch (baseChoice) {
            case 1:
                System.out.println("Двоичное представление: " + Integer.toBinaryString(number));
                break;
            case 2:
                System.out.println("Восьмеричное представление: " + Integer.toOctalString(number));
                break;
            case 3:
                System.out.println("Шестнадцатеричное представление: " + Integer.toHexString(number).toUpperCase());
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }
}