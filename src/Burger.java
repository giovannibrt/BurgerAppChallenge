
import java.util.ArrayList;
import java.util.List;

public class Burger extends Product {

    protected List<Topping> toppings;
    protected int maxToppings;
    private static final String[] VALID_TYPES = {
        "Classic", "Cheeseburger", "Bacon", "Bacon Cheeseburger",
        "Double", "Chicken", "Veggie"
    };

    public Burger() {
        this("Classic");
    }

    public Burger(String type) {
        super(type);
        setPrice();
        setValidTypes(VALID_TYPES);
        this.maxToppings = 3; // Default maximum toppings
        this.toppings = new ArrayList<>(); // Initialize with an empty list
    }

    @Override
    public String toString() {
        return "'" + type + "', price=" + price +
            ", toppings:" + toppings;
    }

    @Override
    protected final void setPrice() {
        switch (this.type) {
            case "Classic":
                this.price = 5.00;
                break;
            case "Cheeseburger":
                this.price = 6.00;
                break;
            case "Bacon":
                this.price = 6.50;
                break;
            case "Bacon Cheeseburger":
                this.price = 7.50;
                break;
            case "Double":
                this.price = 8.00;
                break;
            case "Chicken":
                this.price = 6.50;
                break;
            case "Veggie":
                this.price = 4.50;
                break;
            default:
                this.price = 0.0; // Default price for unknown type
                break;
        }
    }

    public void addTopping(Topping topping) {
        if (toppings.size() < maxToppings) {
            toppings.add(topping);
        } else {
            System.out.println("Maximum toppings reached. Cannot add more.");
        }
    }

    @Override
    public double getPrice() {
        double toppingPrice = toppings.stream().mapToDouble(Topping::getPrice).sum();
        return this.price + toppingPrice;
    }
}
