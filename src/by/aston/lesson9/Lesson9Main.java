package by.aston.lesson9;

import java.util.*;

public class Lesson9Main {

    public static void main(String[] args) {

        String[] wordsArray = {"яблоко", "банан", "киви",
                "виноград", "банан", "киви", "апельсин", "киви",
                "банан", "яблоко", "апельсин", "банан"};

        List<String> wordsList = new ArrayList<>(Arrays.asList(wordsArray));
        System.out.println("Исходный массив: " + wordsList);
        Set<String> wordsSet = new HashSet<>(wordsList);
        System.out.println("Список уникальных слов: " + wordsSet);

        Map<String, Integer> wordCountMap = new HashMap<>();
        for (String word : wordsList) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        System.out.println("Количество встреч каждого слова:");
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Петров", "8882288822");
        phoneBook.add("Иванов", "3333344444");
        phoneBook.add("Петров", "2222222222");
        phoneBook.add("Сидоров", "4444444444");
        phoneBook.add("Петров", "2525252525");

        System.out.println();
        System.out.println("Номера телефона Петрова: " + phoneBook.get("Петров"));
    }

}
