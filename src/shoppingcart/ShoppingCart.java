package shoppingcart;

import java.util.ArrayList;


import java.util.Iterator;
import java.util.List;

import products.Product;

import static java.lang.System.out;

public class ShoppingCart {
    List<CartLine> cartLines = new ArrayList<>();

    public void shoppingCartAction(Product product, String action) {
        boolean isAddAction = action.equals("add");
        boolean isRemoveAction = action.equals("remove");
        if (isAddAction) {
            addProductToCartLine(product);
        }
        if (isRemoveAction) {
            removeProductFromCartLine(product);
        } else {
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
        CartLine cline = new CartLine();
        cline.setProduct(product);
        cline.setQuantity(1);
        cartLines.add(cline);
    }

    private boolean isProductExistInACartLine(CartLine cartLine, Product product) {
        return cartLine.getProduct().getProductnumber().equals(product.getProductnumber());
    }

    private void displayShoppingCartDetails() {
        out.println("Content of the Shopping Cart:");
        for (CartLine cline : cartLines) {
            out.println(cline.getQuantity() + " "
                    + cline.getProduct().getProductnumber() + " "
                    + cline.getProduct().getDescription() + " "
                    + cline.getProduct().getPrice());
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
