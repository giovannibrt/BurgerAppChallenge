public class DeluxeMenu extends Menu {

    public DeluxeMenu() {
        super(new DeluxeBurger("Double"), new SideItem("Fries"), new Drink("Coke", 'L'));
    }

    public DeluxeMenu(DeluxeBurger burger, SideItem sideItem, Drink drink) {
        super(burger, sideItem, drink);
    }

    @Override
    public String toString() {
        return "DeluxeMenu{" +
                "burger=" + burger +
                ", sideItem=" + sideItem +
                ", drink=" + drink +
                '}';
    }

    @Override
    public double calculateTotalPrice() {
        // Deluxe menu has a fixed price for the burger, side item, and drink
        return 15.00;
    }

    public void changeDeluxeBurgerType(String type) {
        try {
            ((DeluxeBurger) burger).setType(type);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
