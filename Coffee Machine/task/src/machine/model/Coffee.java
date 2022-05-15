package machine.model;

public enum Coffee {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);

    private final int waterRequired;
    private final int milkRequired;
    private final int coffeeBeansRequired;
    private final int price;

    Coffee(int waterRequired, int milkRequired, int coffeeBeansRequired, int price) {
        this.waterRequired = waterRequired;
        this.milkRequired = milkRequired;
        this.coffeeBeansRequired = coffeeBeansRequired;
        this.price = price;
    }

    public int getWaterRequired() {
        return waterRequired;
    }

    public int getMilkRequired() {
        return milkRequired;
    }

    public int getCoffeeBeansRequired() {
        return coffeeBeansRequired;
    }

    public int getPrice() {
        return price;
    }
}
