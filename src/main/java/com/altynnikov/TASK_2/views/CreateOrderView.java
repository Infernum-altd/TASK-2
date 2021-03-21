package com.altynnikov.TASK_2.views;

import com.altynnikov.TASK_2.models.Order;
import com.altynnikov.TASK_2.repositories.OrderRepository;
import com.altynnikov.TASK_2.repositories.ProductRepository;
import com.altynnikov.TASK_2.utils.ConsoleReadWrite;
import com.altynnikov.TASK_2.utils.PropertyReader;

public class CreateOrderView extends View {
    public CreateOrderView() {
        super(PropertyReader.getProperty("create.order.view"));
    }

    public CreateOrderView(String extraMessage) {
        super(PropertyReader.getProperty("create.order.view") + "\n" + extraMessage);
    }

    @Override
    public View interact(String input) {
        View nextView;

        if (Order.isInputForOrderMatches(input)) {
            Order order = Order.convertInputToOrder(input);

            for (int i = 0; i < order.getOrderItems().size(); i++) {
                if (!ProductRepository.isProductExist(order.getOrderItems().get(i).getProductId())) {
                    order.getOrderItems().remove(i);
                }
            }
            OrderRepository.save(order);
            //manageOrder(order);
            nextView = new HomeView();
        } else {
            nextView = new CreateOrderView(PropertyReader.getProperty("wrong.input.simple"));
        }
        return nextView;
    }

    @Override
    public View interact() {
        ConsoleReadWrite.clearConsole();
        showNavigationMessage();
        View nextView;
        String input = ConsoleReadWrite.readConsoleInput();

        if (Order.isInputForOrderMatches(input)) {
            Order order = Order.convertInputToOrder(input);

            for (int i = 0; i < order.getOrderItems().size(); i++) {
                if (!ProductRepository.isProductExist(order.getOrderItems().get(i).getProductId())) {
                    order.getOrderItems().remove(i);
                }
            }
            manageOrder(order);
            nextView = new HomeView();
        } else {
            ConsoleReadWrite.showErrorMessageWithTimePause(PropertyReader.getProperty("wrong.input.simple"));
            nextView = this;
        }
        return nextView;
    }

    private void manageOrder(Order order) {
        boolean isManagingInProcess = true;
        while (isManagingInProcess) {
            //ConsoleReadWrite.clearConsole();
            order.showOrderItems();
            showNavigationMessage(PropertyReader.getProperty("create.order.view.actions"));
            String navigationInput = ConsoleReadWrite.readConsoleInput();

            switch (navigationInput) {
                case "1":
                    OrderRepository.save(order);
                    ConsoleReadWrite.showSuccessfulMessageWithTimePause(PropertyReader.getProperty("create.order.view.completed"));
                    isManagingInProcess = false;
                    break;
                case "2":
                    ConsoleReadWrite.showErrorMessageWithTimePause(PropertyReader.getProperty("create.order.view.declined"));
                    isManagingInProcess = false;
                    break;
                case "3":
                    removeProductItem(order);
                    break;
                case "4":
                    changeQuantity(order);
                    break;
                default:
                    ConsoleReadWrite.showErrorMessageWithTimePause(PropertyReader.getProperty("wrong.input.simple"));
            }
        }
    }

    private void changeQuantity(Order order) {
        showNavigationMessage(PropertyReader.getProperty("create.order.view.change.enter"));
        boolean isUserTryToInput = true;

        while (isUserTryToInput) {
            String input = ConsoleReadWrite.readConsoleInput();
            if (input.matches("(\\d+ \\d+){1}")) {
                String[] inputValues = input.split(" ");
                if (Integer.parseInt(inputValues[0]) <= order.getOrderItems().size() && Integer.parseInt(inputValues[1]) != 0) {
                    order.changeQuantity(Integer.parseInt(inputValues[0]), Integer.parseInt(inputValues[1]));
                    isUserTryToInput = false;
                }
            } else if (input.equals("back")) {
                isUserTryToInput = false;
            } else {
                ConsoleReadWrite.showErrorMessage(PropertyReader.getProperty("wrong.input"));
            }
        }
    }

    private void removeProductItem(Order order) {
        showNavigationMessage(PropertyReader.getProperty("create.order.view.remove"));
        boolean isUserTryToInput = true;

        while (isUserTryToInput) {
            String input = ConsoleReadWrite.readConsoleInput();
            if (input.toLowerCase().matches("\\d+") && Integer.parseInt(input) <= order.getOrderItems().size()
                    && Integer.parseInt(input) != 0) {
                order.getOrderItems().remove(Integer.parseInt(input) - 1);
                ConsoleReadWrite.showErrorMessageWithTimePause(PropertyReader.getProperty("item.remove"));
                isUserTryToInput = false;
            } else if (input.equals("back")) {
                isUserTryToInput = false;
            } else {
                ConsoleReadWrite.showErrorMessage(PropertyReader.getProperty("wrong.input"));
            }
        }
    }
}
