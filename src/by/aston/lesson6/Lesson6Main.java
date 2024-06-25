package by.aston.lesson6;

public class Lesson6Main {
    public static void main(String[] args) {
        Person[] persArray = new Person[5];
        persArray[0] = new Person("Ivanov Ivan", "Engineer",
                "ivivan@mailbox.com", "111111111", 30000, 25);
        persArray[1] = new Person("Petrov Petr", "Team Lead",
                "ppetrov@mailbox.com", "222222222", 50000, 32);
        persArray[2] = new Person("Kotov Nicolay", "Project Manager",
                "kotovnico@mailbox.com", "333333333", 100000, 38);
        persArray[3] = new Person("Sydorov Andrey", "QA Engineer",
                "sydandre@mailbox.com", "444444444", 400000, 28);
        persArray[4] = new Person("Myhin Vasiliy", "Buisness Analist",
                "myhinvas@mailbox.com", "555555555", 700000, 33);

        for (Person person : persArray) {
            person.printPersonInfo(person);
        }
        System.out.println();

        Park park = new Park("Paskevichey Park", 3);
        System.out.println(park.getName());
        System.out.println();

        park.addAttraction(0, "Big wheel", "14:00 - 18:00", 10.0);
        park.addAttraction(1, "Spring horse", "12:00 - 20:00", 5.0);
        park.addAttraction(2, "Carousel", "11:00 - 17:00", 7.0);

        park.printAttractions();

    }
}
