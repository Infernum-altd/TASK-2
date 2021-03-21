package com.altynnikov.TASK_2.views;


import com.altynnikov.TASK_2.repositories.ProductRepository;
import com.altynnikov.TASK_2.utils.ConsoleReadWrite;
import com.altynnikov.TASK_2.utils.PropertyReader;

public class RemoveView extends View {
    private static int attemptsToEnterPassword = 4;

    public RemoveView() {
        super(PropertyReader.getProperty("delete.view"));
    }

    @Override
    public View interact(String input) {
        View nextView;

        switch (input) {
            case "1":
                removeProductByIdInteraction();
                nextView = new HomeView();
                break;
            case "2":
                removeAllProductsInteraction();
                nextView = new HomeView();
                break;
            default:
                ConsoleReadWrite.showErrorMessageWithTimePause(PropertyReader.getProperty("wrong.input.simple"));
                nextView = this;
        }
        return nextView;
    }

    @Override
    public View interact() {
        ConsoleReadWrite.clearConsole();
        showNavigationMessage();
        View nextView;
        String input = ConsoleReadWrite.readConsoleInput();

        switch (input) {
            case "1":
                removeProductByIdInteraction();
                nextView = new HomeView();
                break;
            case "2":
                removeAllProductsInteraction();
                nextView = new HomeView();
                break;
            default:
                ConsoleReadWrite.showErrorMessageWithTimePause(PropertyReader.getProperty("wrong.input.simple"));
                nextView = this;
        }
        return nextView;
    }

    private void removeAllProductsInteraction() {
        ConsoleReadWrite.clearConsole();
        showNavigationMessage(PropertyReader.getProperty("delete.view.all"));
        boolean isUserTryToInput = true;

        while (isUserTryToInput && attemptsToEnterPassword > 0) {
            String input = ConsoleReadWrite.readConsoleInput();
            if (input.equals(PropertyReader.getProperty("delete.password"))) {
                ProductRepository.deleteAll();
                ConsoleReadWrite.showSuccessfulMessageWithTimePause(PropertyReader.getProperty("delete.completed.all"));
                isUserTryToInput = false;
            } else if (input.equals("back")) {
                isUserTryToInput = false;
            } else {
                ConsoleReadWrite.showErrorMessageWithTimePause(PropertyReader.getProperty("attempts.left") + " " + attemptsToEnterPassword);
                attemptsToEnterPassword--;
            }
        }

    }

    private void removeProductByIdInteraction() {
        ConsoleReadWrite.clearConsole();
        showNavigationMessage(PropertyReader.getProperty("delete.view.by.id"));
        boolean isUserTryToInput = true;

        while (isUserTryToInput) {
            String input = ConsoleReadWrite.readConsoleInput();
            if (input.matches("\\d+")) {
                ProductRepository.deleteById(Integer.parseInt(input));
                ConsoleReadWrite.showSuccessfulMessageWithTimePause("delete.completed");
                isUserTryToInput = false;
            } else if (input.equals("back")) {
                isUserTryToInput = false;
            } else {
                ConsoleReadWrite.showErrorMessage(PropertyReader.getProperty("wrong.input"));
            }
        }
    }
}
