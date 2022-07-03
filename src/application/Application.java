package application;
import products.Product;
import shoppingcart.ShoppingCart;


public class Application {

	public static void main(String[] args) {
		ShoppingCart cart = new ShoppingCart();
		Product p1 = new Product("A123", 100.0, "TV");
		cart.processAction(p1,"add");
		Product p2 = new Product("A665", 75.0, "MP3 Player");
		cart.processAction(p2,"add");
		Product p3 = new Product("A665", 75.0, "MP3 Player");
		cart.processAction(p3,"add");
		
		cart.processAction(null,"print");
		
		cart.processAction(p3,"remove");
		cart.processAction(p2,"remove");
		
		cart.processAction(null,"print");

	}

}
