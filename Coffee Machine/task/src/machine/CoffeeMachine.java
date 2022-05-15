package machine;

import machine.service.Machine;

import java.util.Scanner;

public class CoffeeMachine {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Machine coffeeMachine = new Machine(400, 540, 120, 9, 550);
        coffeeMachine.turnOn();

        while (true) {
            coffeeMachine.checkState();
            coffeeMachine.inputCommand(scanner.nextLine());
            if (coffeeMachine.getState() == State.OFF) {
                break;
            }
        }
    }


}