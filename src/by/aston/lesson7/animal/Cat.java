package by.aston.lesson7.animal;

public class Cat extends Animal {
    private boolean satiety;
    private static int catCount = 0;

    public boolean isSatiety() {
        return satiety;
    }

    public Cat(String name) {
        super(name, 200, 0);
        this.satiety = false;
        catCount++;
    }


    public static int getCatCount() {
        return catCount;
    }

    public void eat(Bowl bowl, int foodAmount) {
        if (bowl.getFoodAmount() < foodAmount) {
            System.out.println(this.getName() + " не поел. Недостаточно еды в миске");
        } else {
            bowl.setFoodAmount(bowl.getFoodAmount() - foodAmount);
            System.out.println(this.getName() + " поел");
            satiety = true;
        }
    }
}
