public class SideItem extends Product {

    private static final String[] VALID_TYPES = {
        "Fries", "Onion Rings", "Salad", "Ice Cream"
    };
    
    public SideItem() {
        this("Fries");
    }
    
    public SideItem(String type) {
        super(type);
        setPrice();
        setValidTypes(VALID_TYPES);
    }

    @Override
    public String toString() {
        return "SideItem{" +
                "type='" + type + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    protected final void setPrice() {
        switch (this.type) {
            case "Fries":
                this.price = 2.50;
                break;
            case "Onion Rings":
                this.price = 3.00;
                break;
            case "Salad":
                this.price = 2.00;
                break;
            case "Ice Cream":
                this.price = 3.50;
                break;
            default:
                this.price = 0.0; // Default price for unknown type
                break;
        }
    }
}
