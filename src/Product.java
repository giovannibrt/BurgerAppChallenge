public abstract class Product {

    protected String type;
    protected double price;
    protected String[] validTypes = {"Default"};

    public Product() {
        this("Default");
    }

    public Product(String type) {
        this.type = type;
    }

    protected void setValidTypes(String[] validTypes) {
        this.validTypes = validTypes;
    }

    public void setType(String type) {
        if (isValidType(type)) {
            this.type = type;
            setPrice();
        } else {
            throw new IllegalArgumentException("Invalid type: " + type + " for " + this.getClass().getSimpleName() + ".\n" +
                    "Valid types are: " + String.join(", ", validTypes));
        }
    }

    private boolean isValidType(String type) {
        for (String validType : validTypes) {
            if (validType.equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    protected abstract void setPrice();

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

}
