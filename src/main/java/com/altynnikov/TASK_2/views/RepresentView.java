package com.altynnikov.TASK_2.views;

import com.altynnikov.TASK_2.repositories.OrderRepository;
import com.altynnikov.TASK_2.utils.ConsoleReadWrite;
import com.altynnikov.TASK_2.utils.PropertyReader;

public class RepresentView extends View {

    public RepresentView() {
        super(PropertyReader.getProperty("represent.view"));
    }

    @Override
    public View interact() {
        ConsoleReadWrite.clearConsole();
        View nextView;
        showNavigationMessage();
        String input = ConsoleReadWrite.readConsoleInput();

        switch (input) {
            case "1":
                nextView = new RepresentProductsView();
                break;
            case "2":
                nextView = new RepresentOrderedProductsView();
                break;
            case "3":
                int orderId = askOrderId();
                nextView = orderId > 0 ? new RepresentOrdersView(OrderRepository.getOrderById(orderId)) : this;
                break;
            case "4":
                nextView = new RepresentOrdersView(OrderRepository.getAllOrders());
                break;
            case "5":
                nextView = new HomeView();
                break;
            default:
                ConsoleReadWrite.showErrorMessageWithTimePause(PropertyReader.getProperty("wrong.input.simple"));
                nextView = this;
        }
        return nextView;
    }

    @Override
    public View interact(String input) {
        View nextView;
        switch (input) {
            case "1":
                nextView = new RepresentProductsView();
                break;
            case "2":
                nextView = new RepresentOrderedProductsView();
                break;
            case "3":
                int orderId = askOrderId();
                nextView = orderId > 0 ? new RepresentOrdersView(OrderRepository.getOrderById(orderId)) : this;
                break;
            case "4":
                nextView = new RepresentOrdersView(OrderRepository.getAllOrders());
                break;
            case "5":
                nextView = new HomeView();
                break;
            default:
                ConsoleReadWrite.showErrorMessageWithTimePause(PropertyReader.getProperty("wrong.input.simple"));
                nextView = this;
        }
        return nextView;
    }

    private int askOrderId() {
        ConsoleReadWrite.clearConsole();
        showNavigationMessage(PropertyReader.getProperty("represent.view.ask.id"));
        boolean isUserTryToInput = true;
        int result = 0;

        while (isUserTryToInput) {
            String input = ConsoleReadWrite.readConsoleInput();
            if (input.matches("\\d+")) {
                result = Integer.parseInt(input);
                isUserTryToInput = false;
            } else if (input.equals("back")) {
                isUserTryToInput = false;
            } else {
                ConsoleReadWrite.showErrorMessage(PropertyReader.getProperty("wrong.input"));
            }
        }
        return result;
    }
}
