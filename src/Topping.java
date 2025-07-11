public class Topping extends Product {

    private static final String[] VALID_TYPES = {
        "Lettuce", "Tomato", "Cheese", "Bacon",
        "Onion", "Pickles", "Jalapenos", "Avocado",
        "Mushrooms", "BBQ Sauce", "Ketchup", "Mustard",
        "Hot Sauce", "Ranch Dressing", "Mayo"
    };

    public Topping() {
        this("Lettuce");
    }

    public Topping(String type) {
        super(type);
        setPrice();
        setValidTypes(VALID_TYPES);
    }

    @Override
    public String toString() {
        return "Topping{" +
                "type='" + getType() + '\'' +
                ", price=" + getPrice() +
                '}';
    }

    @Override
    protected final void setPrice() {
        switch (this.getType()) {
            case "Lettuce":
                this.price = 0.50;
                break;
            case "Tomato":
                this.price = 0.75;
                break;
            case "Cheese":
                this.price = 1.00;
                break;
            case "Bacon":
                this.price = 1.50;
                break;
            case "Onion":
                this.price = 0.25;
                break;
            case "Pickles":
                this.price = 0.30;
                break;
            case "Jalapenos":
                this.price = 0.40;
                break;
            case "Avocado":
                this.price = 1.20;
                break;
            case "Mushrooms":
                this.price = 0.60;
                break;
            case "BBQ Sauce":
                this.price = 0.70;
                break;
            case "Ketchup":
                this.price = 0.20;
                break;
            case "Mustard":
                this.price = 0.20;
                break;
            case "Hot Sauce":
                this.price = 0.50;
                break;
            case "Ranch Dressing":
                this.price = 0.80;
                break;
            case "Mayo":
                this.price = 0.20;
            default:
                this.price = 0.0; // Default price for unknown topping
                break;
        }
    }

}
