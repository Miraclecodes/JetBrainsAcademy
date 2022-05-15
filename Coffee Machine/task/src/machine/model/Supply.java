package machine.model;

public class Supply {

    private int amount;

    public Supply(int amount) {
        this.amount = amount;
    }

    public void add(int addAmount) {
        this.amount += addAmount;
    }
    public void use(int useAmount) {
        this.amount -= useAmount;
    }

    public int getAmount() {
        return this.amount;
    }
}
