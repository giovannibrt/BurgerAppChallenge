

public class BillsBurgerApp {

    public static void main(String[] args) throws Exception {
        System.out.println("\nStarting Bill's Burger App...");
        System.out.println("-----------------------------------\n");
        //System.out.println("Please select your menu");

        // Example of creating a base Menu
        Menu baseMenu = new Menu();
        System.out.println("Base Menu: " + baseMenu);
        baseMenu.changeBurgerType("Cheeseburger");
        baseMenu.changeSideItemType("Salad");
        baseMenu.changeDrinkType("Pepsi");
        baseMenu.changeDrinkSize('M');
        System.out.println("Updated Menu: " + baseMenu);
        System.out.println("Total Price: $" + baseMenu.calculateTotalPrice());
        System.out.println("-----------------------------------\n");

        // Example of creating a Custom Menu
        Menu customMenu = new Menu(
                new Burger("Bacon Cheeseburger"),
                new SideItem("Onion Rings"),
                new Drink("Sprite", 'L')
        );
        customMenu.addBurgerTopping(new Topping("Lettuce"));
        customMenu.addBurgerTopping(new Topping("Tomato"));
        customMenu.addBurgerTopping(new Topping("Mustard"));
        System.out.println("Custom Menu: " + customMenu);
        System.out.println("Total Price: $" + customMenu.calculateTotalPrice());
        System.out.println("-----------------------------------\n");

        // Example of creating a Deluxe Menu
        DeluxeMenu deluxeMenu = new DeluxeMenu();
        System.out.println("Deluxe Menu: " + deluxeMenu);
        System.out.println("Total Price: $" + deluxeMenu.calculateTotalPrice());
        deluxeMenu.changeDeluxeBurgerType("Chicken");
        deluxeMenu.changeSideItemType("Ice Cream");
        deluxeMenu.changeDrinkTypeAndSize("Pepsi", 'M');
        deluxeMenu.addBurgerTopping(new Topping("Pickles"));
        deluxeMenu.addBurgerTopping(new Topping("Ketchup"));
        deluxeMenu.addBurgerTopping(new Topping("Mayo"));
        deluxeMenu.addBurgerTopping(new Topping("Jalapenos"));
        deluxeMenu.addBurgerTopping(new Topping("Avocado"));
        System.out.println("Updated Deluxe Menu: " + deluxeMenu);
        System.out.println("Total Price after change: $" + deluxeMenu.calculateTotalPrice());
        System.out.println("-----------------------------------\n");

        System.out.println("Thank you for using Bill's Burger App!");
        System.out.println("Have a great day!");
        System.out.println("-----------------------------------\n");
    }
}
