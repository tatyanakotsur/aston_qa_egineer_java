package by.aston.lesson5;

public class Lesson5Main {

    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        System.out.println(checkSumRange(5, 8));
        checkNumberSign(0);
        System.out.println(checkIfPositiveNumber(-10));
        printWord("java", 3);
        System.out.println(checkIfIsLeapYear(1988));
        invertArray(new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0});
        System.out.println();
        fillArray();
        System.out.println();
        task12();
        System.out.println();
        System.out.println();
        task13(4);
        System.out.println();
        for (int i : task14(5, 10)) {
            System.out.print(i + " ");
        }
    }

    static void printThreeWords() {
        System.out.println("Orange\nBanana\nApple");
    }

    static void checkSumSign() {
        int a = 5;
        int b = -8;
        if (a + b >= 0) {
            System.out.println("\nСумма положительная");
        } else {
            System.out.println("\nСумма отрицательная");
        }
    }

    static void printColor() {
        int value = 50;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    static void compareNumbers() {
        int a = 3;
        int b = 7;
        if (a >= b) {
            System.out.println("a >= b");
        } else System.out.println("a < b");
    }

    static boolean checkSumRange(int a, int b) {
        int sum = a + b;
        if (sum > 10 && sum <= 20) {
            return true;
        }
        return false;
    }

    static void checkNumberSign(int number) {
        if (number >= 0) {
            System.out.println("Положительное число");
        } else {
            System.out.println("Отрицательное число");
        }
    }

    static boolean checkIfPositiveNumber(int number) {
        if (number >= 0) {
            return true;
        } else {
            return false;
        }
    }

    static void printWord(String word, int numberOfTimes) {
        for (int i = 1; i <= numberOfTimes; i++) {
            System.out.print(word + " ");
        }
    }

    static boolean checkIfIsLeapYear(int year) {
        if (year % 100 == 0 && year % 400 != 0) {
            return false;
        }
        return year % 4 == 0;
    }

    static void invertArray(int[] array) {
        int i = 0;
        while (i < array.length) {
            if (array[i] == 0) {
                array[i] = 1;
            } else if (array[i] == 1) {
                array[i] = 0;
            }
            System.out.print(array[i] + " ");
            i++;
        }
    }

    static void fillArray() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    static void task12() {
        int[] arr = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    static void task13(int len) {
        int[][] arr = new int[len][len];
        for (int i = 0; i < len; i++) {
            arr[i][i] = 1;
            arr[len - 1 - i][i] = 1;
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int[] task14(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }

}

