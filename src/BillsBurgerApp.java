import java.util.ArrayList;

public class BillsBurgerApp {

    public record Order(ArrayList<Menu> menus) {

        private double getTotal() {
            double orderTotal = 0;
            for (var m : menus) orderTotal += m.calculateTotalPrice();
            return orderTotal;
        }
    }

    private static ArrayList<Order> dailyOrders;

    public static void main(String[] args) throws Exception {
        System.out.println("\nStarting Bill's Burger App...");
        System.out.println("\n-----------------------------------\n");

        dailyOrders = new ArrayList<>();

        testRun();

        double dailyTotal = 0;
        for (var o : dailyOrders) dailyTotal += o.getTotal();

        System.out.println("Thank you for using Bill's Burger App!");
        System.out.println("Total revenue of the day: $" + dailyTotal);
        System.out.println("Have a great day!");
        System.out.println("\n-----------------------------------\n");
    }
    
    public static void testRun() {
        System.out.println("\nTest Run...");  
        System.out.println("\n-----------------------------------\n");

        var orderList = new ArrayList<Menu>();

        // Example of creating a base Menu
        Menu baseMenu = new Menu();
        System.out.println("Base " + baseMenu);
        baseMenu.changeBurgerType("Cheeseburger");
        baseMenu.changeSideItemType("Salad");
        baseMenu.changeDrinkType("Pepsi");
        baseMenu.changeDrinkSize('M');
        System.out.println("Updated " + baseMenu);        
        System.out.println("Total Price: $" + baseMenu.calculateTotalPrice());
        orderList.add(baseMenu);
        System.out.println("Menu added to current order");
        System.out.println("\n-----------------------------------\n");

        // Example of creating a Custom Menu
        Menu customMenu = new Menu(
                new Burger("Bacon Cheeseburger"),
                new SideItem("Onion Rings"),
                new Drink("Sprite", 'L')
        );
        customMenu.addBurgerTopping(new Topping("Lettuce"));
        customMenu.addBurgerTopping(new Topping("Tomato"));
        customMenu.addBurgerTopping(new Topping("Mustard"));
        System.out.println("Custom " + customMenu);
        System.out.println("Total Price: $" + customMenu.calculateTotalPrice());
        orderList.add(customMenu);
        System.out.println("Menu added to current order");
        System.out.println("\n-----------------------------------\n");

        // Example of creating a Deluxe Menu
        DeluxeMenu deluxeMenu = new DeluxeMenu();
        System.out.println(deluxeMenu);
        System.out.println("Total Price: $" + deluxeMenu.calculateTotalPrice());
        deluxeMenu.changeDeluxeBurgerType("Chicken");
        deluxeMenu.changeSideItemType("Ice Cream");
        deluxeMenu.changeDrinkTypeAndSize("Pepsi", 'M');
        deluxeMenu.addBurgerTopping(new Topping("Pickles"));
        deluxeMenu.addBurgerTopping(new Topping("Ketchup"));
        deluxeMenu.addBurgerTopping(new Topping("Mayo"));
        deluxeMenu.addBurgerTopping(new Topping("Jalapenos"));
        deluxeMenu.addBurgerTopping(new Topping("Avocado"));
        System.out.println("\nUpdated " + deluxeMenu);
        System.out.println("Total Price after change: $" + deluxeMenu.calculateTotalPrice());
        orderList.add(deluxeMenu);
        System.out.println("Deluxe menu added to current order");
        System.out.println("\n-----------------------------------\n");

        var order = new Order(orderList);
        System.out.println("Total order price: " + order.getTotal());
        dailyOrders.add(order);
        System.out.println("\n-----------------------------------\n");

    }
}
