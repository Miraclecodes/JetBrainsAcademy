package machine.service;

import machine.model.Coffee;
import machine.State;
import machine.model.Supply;

import static machine.model.Coffee.*;
import static machine.State.*;

public class Machine {

    private Supply water;
    private Supply milk;
    private Supply coffeeBeans;
    private Supply cups;
    private Supply money;

    private State state;


    public Machine(int water, int milk, int coffeeBeans, int cups, int money) {
        this.water = new Supply(water);
        this.milk = new Supply(milk);
        this.coffeeBeans = new Supply(coffeeBeans);
        this.cups = new Supply(cups);
        this.money = new Supply(money);
    }

    public void turnOn() {
        setState(ON);
    }

    public void checkState() {
        switch (this.state) {
            case ON:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            case BUY:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                break;
            case FILL_WATER:
                System.out.println("Write how many ml of water you want to add:");
                break;
            case FILL_MILK:
                System.out.println("Write how many ml of milk you want to add:");
                break;
            case FILL_COFFEE_BEANS:
                System.out.println("Write how many grams of coffee beans you want to add:");
                break;
            case FILL_CUPS:
                System.out.println("Write how many disposable cups you want to add:");
                break;
            case OFF:
                break;
        }
    }

    public void inputCommand(String input) {
        switch (state) {
            case ON:
                chooseAction(input);
                break;
            case BUY:
                buy(input);
                break;
            case FILL_WATER:
                fillWater(input);
                break;
            case FILL_MILK:
                fillMilk(input);
                break;
            case FILL_COFFEE_BEANS:
                fillCoffeeBeans(input);
                break;
            case FILL_CUPS:
                fillCups(input);
                break;
            case OFF:
                break;
        }
    }

    private void chooseAction(String option) {
        switch (option) {
            case "buy":
                setState(BUY);
                break;
            case "fill":
                fill();
                break;
            case "take":
                take();
                break;
            case "remaining":
                showState();
                break;
            case "exit":
                setState(OFF);
                break;
            default:
                System.out.println("Please enter a valid option");
                break;
        }
    }

    private void buy(String option) {
        Coffee coffee = null;
        switch (option) {
            case "1":
                coffee = ESPRESSO;
                break;
            case "2":
                coffee = LATTE;
                break;
            case "3":
                coffee = CAPPUCCINO;
                break;
            case "back":
                break;
            default:
                System.out.println("Select a valid option");
                break;
        }
        if (coffee != null && canMakeCoffee(coffee)) {
            makeCoffee(coffee);
        }
        setState(ON);
    }

    private boolean canMakeCoffee(Coffee coffee) {
        if (milk.getAmount() < coffee.getMilkRequired()) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        if (water.getAmount() < coffee.getWaterRequired()) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (coffeeBeans.getAmount() < coffee.getCoffeeBeansRequired()) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        }
        if (money.getAmount() < coffee.getPrice()) {
            System.out.println("Sorry, not enough money!");
            return false;
        }
        if (cups.getAmount() < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        }
        System.out.println("I have enough resources, making you a coffee!");
        return true;
    }

    private void makeCoffee(Coffee coffee) {
        water.use(coffee.getWaterRequired());
        milk.use(coffee.getMilkRequired());
        coffeeBeans.use(coffee.getCoffeeBeansRequired());
        money.add(coffee.getPrice());
        cups.use(1);
    };

    private void fill() {
        setState(FILL_WATER);
    }

    private void fillWater(String amount) {
        int waterAmount = Integer.parseInt(amount);
        water.add(waterAmount);
        setState(FILL_MILK);
    }

    private void fillMilk(String amount) {
        int milkAmount = Integer.parseInt(amount);
        milk.add(milkAmount);
        setState(FILL_COFFEE_BEANS);

    }

    private void fillCoffeeBeans(String amount) {
        int coffeeBeanAmount = Integer.parseInt(amount);
        coffeeBeans.add(coffeeBeanAmount);
        setState(FILL_CUPS);
    }

    private void fillCups(String amount) {
        int cupsAmount = Integer.parseInt(amount);
        cups.add(cupsAmount);
        setState(ON);
    }

    private void take() {
        String takeAmount = String.format("I gave you $%d",money.getAmount());
        money.use(money.getAmount());
        System.out.println(takeAmount);
    }

    private void showState() {
        String waterState = String.format("%d ml of water", water.getAmount());
        String milkState = String.format("%d ml of milk", milk.getAmount());
        String coffeeBeanState = String.format("%d g of coffee beans", coffeeBeans.getAmount());
        String cupState = String.format("%d disposable cups", cups.getAmount());
        String moneyState = String.format("$%d of money", money.getAmount());

        System.out.println("This coffee machine has:");
        System.out.println(waterState);
        System.out.println(milkState);
        System.out.println(coffeeBeanState);
        System.out.println(cupState);
        System.out.println(moneyState);
    }

    private void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }
}
