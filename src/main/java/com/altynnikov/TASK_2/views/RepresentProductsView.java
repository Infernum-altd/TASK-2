package com.altynnikov.TASK_2.views;

import com.altynnikov.TASK_2.models.Product;
import com.altynnikov.TASK_2.repositories.ProductRepository;
import com.altynnikov.TASK_2.utils.ConsoleReadWrite;
import com.altynnikov.TASK_2.utils.PropertyReader;

import java.util.List;

public class RepresentProductsView extends View {
    public RepresentProductsView() {
        super(PropertyReader.getProperty("represent.products.view"));

        List<Product> products = ProductRepository.getAllProducts();

        StringBuilder stringBuilder = new StringBuilder();

        for (Product product : products) {
            stringBuilder.append("       " + product.getName() + "           "
                    + product.getPrice() + "          " + product.getStatus() + "\n");
        }

        super.navigationMessage +=  ("\n" + stringBuilder.toString());
    }

    @Override
    public View interact(String input) {
        return new HomeView();
    }

    @Override
    public View interact() {
        ConsoleReadWrite.clearConsole();
        showNavigationMessage();
        List<Product> products = ProductRepository.getAllProducts();

        for (Product product : products) {
            showNavigationMessage("       " + product.getName() + "           "
                    + product.getPrice() + "          " + product.getStatus());
        }

        showNavigationMessage(PropertyReader.getProperty("enter.something"));
        ConsoleReadWrite.readConsoleInput();
        return new HomeView();
    }
}
