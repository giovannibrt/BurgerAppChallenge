public class Drink extends Product {

    private char size; // 'S' for Small, 'M' for Medium, 'L' for Large
    private static final String[] VALID_TYPES = {
        "Coke", "Pepsi", "Sprite", "Fanta",
        "Lemonade", "Iced Tea", "Water", "Coffee",
        "Hot Chocolate", "Orange Juice", "Apple Juice"
    };

    public Drink() {
        this("Coke", 'S'); // Default drink is Coke with size Small
    }

    public Drink(String type, char size) {
        super(type);
        this.size = size;
        setPrice();
        setValidTypes(VALID_TYPES);
    }

    public void setType(String type, char size) {
        this.size = size;
        super.setType(type);
    }
    public void setSize(char size) {
        setType(this.type, size); // Keep the current type, but run the super method that updates the price
    }

    public char getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "type='" + type + '\'' +
                ", size=" + size +
                ", price=" + price +
                '}';
    }

    @Override
    protected final void setPrice() {
        switch (this.size) {
            case 'S':
                this.price = 1.50;
                break;
            case 'M':
                this.price = 2.00;
                break;
            case 'L':
                this.price = 2.50;
                break;
            default:
                this.price = 0.0; // Default price for unknown size
                break;
        }
    }

}
