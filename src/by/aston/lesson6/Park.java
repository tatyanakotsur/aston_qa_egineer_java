package by.aston.lesson6;

public class Park {
    private String name;
    private Attraction[] attractions;

    public Park(String name, int size) {
        this.name = name;
        this.attractions = new Attraction[size];
    }

    public String getName() {
        return name;
    }

    public void addAttraction(int index, String name, String workingHours, double cost) {
        attractions[index] = new Attraction(name, workingHours, cost);
    }

    public void printAttractions() {
        for (Attraction attraction : attractions) {
            if (attraction != null) {
                System.out.println("Attraction: " + attraction.getName());
                System.out.println("Working Hours: " + attraction.getWorkingHours());
                System.out.println("Cost: " + attraction.getCost());
                System.out.println();
            }
        }
    }

    private class Attraction {
        private String name;
        private String workingHours;
        private double cost;

        public Attraction(String name, String workingHours, double cost) {
            this.name = name;
            this.workingHours = workingHours;
            this.cost = cost;
        }

        public String getName() {
            return name;
        }

        public String getWorkingHours() {
            return workingHours;
        }

        public double getCost() {
            return cost;
        }
    }
}
