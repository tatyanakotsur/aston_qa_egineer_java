package by.aston.lesson7.animal;

public class Bowl {
    private int foodAmount;

    public Bowl(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public void addFood(int amount) {
        foodAmount += amount;
        System.out.println("В миску добавлено " + amount + " еды");
    }

}
