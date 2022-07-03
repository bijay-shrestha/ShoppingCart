package shoppingcart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import products.Product;
import static java.lang.System.out;

public class ShoppingCart {
    List<CartLine> cartLines = new ArrayList<>();

    public void processAction(Product product, String action) {
        boolean isAddAction = action.equals("add");
        boolean isRemoveAction = action.equals("remove");
        if (isAddAction) {
            addProductToCartLine(product);
        }
        if (isRemoveAction) {
            removeProductFromCartLine(product);
        }
        if(!(isAddAction || isRemoveAction)) {
            displayShoppingCartDetails();
        }
    }

    private void removeProductFromCartLine(Product product) {
        Iterator<CartLine> iterator = cartLines.iterator();
        while (iterator.hasNext()) {
            CartLine cartLine = iterator.next();
            if (isProductExistInACartLine(cartLine, product)) {
                if (cartLine.getQuantity() > 1) {
                    cartLine.setQuantity(cartLine.getQuantity() - 1);
                } else {
                    iterator.remove();
                }
            }
        }
    }

    private void addProductToCartLine(Product product) {
        for (CartLine cartLine : cartLines) {
            if (isProductExistInACartLine(cartLine, product)) {
                cartLine.setQuantity(cartLine.getQuantity() + 1);
                return;
            }
        }
        addNewProductInACartLine(product);
    }

    private void addNewProductInACartLine(Product product) {
        CartLine cartLine = new CartLine();
        cartLine.setProduct(product);
        cartLine.setQuantity(1);
        cartLines.add(cartLine);
    }

    private boolean isProductExistInACartLine(CartLine cartLine, Product product) {
        return cartLine.getProduct().getProductnumber().equals(product.getProductnumber());
    }

    private void displayShoppingCartDetails() {
        out.println("Content of the Shopping Cart:");
        for (CartLine cartLine : cartLines) {
            out.println(cartLine.getQuantity() + " "
                    + cartLine.getProduct().getProductnumber() + " "
                    + cartLine.getProduct().getDescription() + " "
                    + cartLine.getProduct().getPrice());
        }
        out.println("Total price =" + getTotalPrice());
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (CartLine cartLine : cartLines) {
            totalPrice = totalPrice + (cartLine.getProduct().getPrice() * cartLine.getQuantity());
        }
        return totalPrice;
    }
}
