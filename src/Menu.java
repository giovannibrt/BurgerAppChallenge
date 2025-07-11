
public class Menu {

    protected Burger burger;
    protected SideItem sideItem;
    protected Drink drink;

    public Menu() {
        this(new Burger(), new SideItem(), new Drink());
    }

    public Menu(Burger burger, SideItem sideItem, Drink drink) {
        this.burger = burger;
        this.sideItem = sideItem;
        this.drink = drink;
    }

    public void changeBurgerType(String type) {
        try {
            burger.setType(type);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addBurgerTopping(Topping topping) {
        burger.addTopping(topping);
    }
    
    public void changeSideItemType(String type) {
        try {
            sideItem.setType(type);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeDrinkType(String type) {
        try {
            drink.setType(type);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeDrinkSize(char size) {
        drink.setSize(size);
    }

    public void changeDrinkTypeAndSize(String type, char size) {
        changeDrinkSize(size);
        changeDrinkType(type);
    }

    public double calculateTotalPrice() {
        return burger.getPrice() + sideItem.getPrice() + drink.getPrice();
    }

    @Override
    public String toString() {
        return "Menu{" +
                "burger=" + burger +
                ", sideItem=" + sideItem +
                ", drink=" + drink +
                '}';
    }

}
