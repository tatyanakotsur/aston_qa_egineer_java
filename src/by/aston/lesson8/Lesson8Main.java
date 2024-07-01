package by.aston.lesson8;

public class Lesson8Main {
    public static void main(String[] args) {
        String[][] stringArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7k", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int sum = sumArrayElements(stringArray);
            System.out.println("Сумма элементов массива: " + sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static int sumArrayElements(String[][] stringArray) throws MyArraySizeException, MyArrayDataException {
        if (stringArray.length != 4 || stringArray[0].length != 4) {
            throw new MyArraySizeException("Неверный размер массива");
        }

        int sum = 0;
        for (int i = 0; i < stringArray.length; i++) {
            for (int j = 0; j < stringArray[i].length; j++) {
                try {
                    sum += Integer.parseInt(stringArray[i][j]);
                } catch (NumberFormatException ex) {
                    throw new MyArrayDataException("Неверные данные в ячейке [" + i + "][" + j + "]");
                }
            }
        }
        return sum;
    }
}





