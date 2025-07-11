public class DeluxeBurger extends Burger {

    public DeluxeBurger() {
        this("Classic");
    }

    public DeluxeBurger(String type) {
        super(type);
        this.maxToppings = 5; // Deluxe burgers can have more toppings
    }

    @Override
    public String toString() {
        return "DeluxeBurger{" +
                "type='" + getType() + '\'' +
                ", price=" + getPrice() +
                ", toppings=" + toppings +
                '}';
    }

    // @Override
    // public double getPrice() {
    //     return 16.00; // Return a fixed price for Deluxe burgers menus
    // }
}
