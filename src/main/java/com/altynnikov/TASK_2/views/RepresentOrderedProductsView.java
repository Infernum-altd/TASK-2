package com.altynnikov.TASK_2.views;

import com.altynnikov.TASK_2.models.Product;
import com.altynnikov.TASK_2.repositories.ProductRepository;
import com.altynnikov.TASK_2.utils.ConsoleReadWrite;
import com.altynnikov.TASK_2.utils.PropertyReader;


public class RepresentOrderedProductsView extends View {
    public RepresentOrderedProductsView() {
        super(PropertyReader.getProperty("represent.ordered.products.view"));

        Object[][] arrProductTotalOrder = ProductRepository.getOrderedProductsWithTotalQuantity();
        StringBuilder stringBuilder = new StringBuilder();
        if (arrProductTotalOrder != null) {
            for (Object[] objects : arrProductTotalOrder) {
                Product product = (Product) objects[0];
                stringBuilder.append("     " + product.getName() + "           " +
                        product.getPrice() + "              " +
                        product.getStatus() + "      " + objects[1] + "\n");
            }
        }
        super.navigationMessage += ("\n" + stringBuilder.toString());
    }

    @Override
    public View interact(String input) {
        return new HomeView();
    }

    @Override
    public View interact() {
        ConsoleReadWrite.clearConsole();
        showNavigationMessage();
        Object[][] arrProductTotalOrder = ProductRepository.getOrderedProductsWithTotalQuantity();

        for (Object[] objects : arrProductTotalOrder) {
            Product product = (Product) objects[0];
            showNavigationMessage("     " + product.getName() + "           " +
                    product.getPrice() + "              " +
                    product.getStatus() + "      " + objects[1]);
        }

        showNavigationMessage(PropertyReader.getProperty("enter.something"));
        ConsoleReadWrite.readConsoleInput();
        return new HomeView();
    }
}
