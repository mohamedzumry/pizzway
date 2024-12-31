package com.example.pizzway.controllers;

import com.example.pizzway.MainApp;
import com.example.pizzway.models.LoggedInCustomer;
import com.example.pizzway.models.Order;
import com.example.pizzway.models.Pizza;
import com.example.pizzway.patterns.builder.PizzaBuilder;
import com.example.pizzway.patterns.chainOfResponsibility.*;
import com.example.pizzway.patterns.command.CommandInvoker;
import com.example.pizzway.patterns.command.PlaceOrderCommand;
import com.example.pizzway.patterns.command.SubmitFeedbackCommand;
import com.example.pizzway.patterns.decorator.ExtraCheeseDecorator;
import com.example.pizzway.patterns.decorator.PizzaComponent;
import com.example.pizzway.patterns.decorator.SpecialPackagingDecorator;
import com.example.pizzway.patterns.observer.UserNotification;
import com.example.pizzway.patterns.strategy.CardPaymentStrategy;
import com.example.pizzway.patterns.strategy.DigitalWalletPaymentStrategy;
import com.example.pizzway.patterns.strategy.PaymentContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;

public class PizzaOrderController {

    @FXML
    private ComboBox<String> crustComboBox;

    @FXML
    private ComboBox<String> cheeseComboBox;

    @FXML
    private ComboBox<String> sauceComboBox;

    @FXML
    private ComboBox<String> orderTypeComboBox, paymentTypeComboBox;

    @FXML
    private VBox toppingsVBox;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Button orderButton, reviewOrderButton, savePizzaButton, cancelOrderButton, undoButton, homeButton, profileButton;

    @FXML
    private TextField pizzaNameTextField;

    @FXML
    private TextArea orderSummary;

    @FXML
    private CheckBox favouriteCheckbox, specialPackagingCheckbox, extraCheeseCheckbox;

    private CommandInvoker commandInvoker = new CommandInvoker();
    private double totalPrice = 0.0;
    LoggedInCustomer user = LoggedInCustomer.getInstance();

    @FXML
    public void initialize() {
        // Initialize crust options
        crustComboBox.getItems().addAll("Thin", "Thick", "Stuffed");
        // Initialize cheese options
        cheeseComboBox.getItems().addAll("Mozzarella", "Cheddar", "Vegan Cheese");
        // Initialize sauce options
        sauceComboBox.getItems().addAll("Tomato", "Barbecue", "Pesto");
        // Initialize order type options
        orderTypeComboBox.getItems().addAll("Pick Up", "Delivery");
        // Initialize payment options
        paymentTypeComboBox.getItems().addAll("Digital Wallet", "Card");

        // Initialize toppings
        String[] toppings = {"Genza", "Pepperoni", "Mushrooms", "Onions", "Olives", "Bacon", "Pineapple"};
        for (String topping : toppings) {
            CheckBox checkBox = new CheckBox(topping);
            toppingsVBox.getChildren().add(checkBox);
        }

        // Set event handlers
        reviewOrderButton.setOnAction(event -> reviewPizza());
        savePizzaButton.setOnAction(event -> savePizza());
        cancelOrderButton.setOnAction(event -> cancelOrder());
//        undoButton.setOnAction(event -> undoLastAction());
        orderButton.setOnAction(event -> handleOrder());
        homeButton.setOnAction(event -> goToHomePage());
        profileButton.setOnAction(event -> goToProfilePage());
        updateTotalPrice(0); // Initialize price
    }

    private void cancelOrder() {
        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("com.example.pizzway/login-view.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("home-view.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load());

            // Get the current stage (window)
            Stage stage = (Stage) pizzaNameTextField.getScene().getWindow();

            // Set the new scene (Login view)
            stage.setScene(loginScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToHomePage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("home-view.fxml"));
            Scene homeScene = new Scene(fxmlLoader.load());

            // Get the current stage (window)
            Stage stage = (Stage) homeButton.getScene().getWindow();

            // Set the new scene (Login view)
            stage.setScene(homeScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToProfilePage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("profile-view.fxml"));
            Scene profileScene = new Scene(fxmlLoader.load());
            // Get the current stage (window)
            Stage stage = (Stage) profileButton.getScene().getWindow();
            stage.setScene(profileScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void navigateToPaymentsPage(PlaceOrderCommand placeOrderCommand) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("payment-view.fxml"));
            Scene paymentScene = new Scene(fxmlLoader.load());

            // Get the current stage (window)
            Stage stage = (Stage) pizzaNameTextField.getScene().getWindow();

            // Set the new scene (Payment view)
            stage.setScene(paymentScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void undoLastAction() {
        commandInvoker.undoLastCommand();
        showAlert(Alert.AlertType.INFORMATION, "Undo", "Last action has been undone.");
    }

    private void submitFeedback(String feedback) {
        SubmitFeedbackCommand feedbackCommand = new SubmitFeedbackCommand(feedback);
        commandInvoker.executeCommand(feedbackCommand);
        showAlert(Alert.AlertType.INFORMATION, "Feedback Submitted", "Thank you for your feedback!");
    }

    private void reviewPizza() {
        PizzaComponent decoratedPizza = getDecoratedPizza();
        totalPriceLabel.setText("Rs. " + decoratedPizza.getPrice() + " /=");
        displayOrderSummary(decoratedPizza);
    }

    private void displayOrderSummary(PizzaComponent pizza) {
        orderSummary.setText(pizza.getDescription() + "\nTotal Price: Rs. " + pizza.getPrice());
    }

    private void savePizza() {
        Pizza finalizedPizza = getPizzaSummary();
        try {
            boolean is_favourite = false;
            if (favouriteCheckbox.isSelected()) {
                is_favourite = true;
            }
            savePizzaToFile(finalizedPizza, pizzaNameTextField.getText(), is_favourite);
            showAlert(Alert.AlertType.INFORMATION, "Pizza Saved", "Saved pizza " + pizzaNameTextField.getText() + " successfully!");
            clearPizzaNameField();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "File Error", "Unable to save user data!");
        }
    }

    private void savePizzaToFile(Pizza pizza, String pizzaName, boolean is_favourite) throws IOException {
        if (is_favourite) {
            String FAVOURITE_PIZZA_DATA_FILE = "favourite-pizzas.txt";
            File file = new File(FAVOURITE_PIZZA_DATA_FILE);
            boolean newFile = file.createNewFile(); // Create file if it doesn't exist
            try (FileWriter fileWriter = new FileWriter(file, true);
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {
                if (newFile) {
                    printWriter.println("Pizza Name, Description"); // Add header if new file
                }
                printWriter.printf("%s%n,%s%n", pizzaName, pizza.getDescription());
            }
        }
        String SAVED_PIZZA_DATA_FILE = "saved-pizzas.txt";
        File file = new File(SAVED_PIZZA_DATA_FILE);
        boolean newFile = file.createNewFile(); // Create file if it doesn't exist
        try (FileWriter fileWriter = new FileWriter(file, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            if (newFile) {
                printWriter.println("Pizza Name, Description"); // Add header if new file
            }
            printWriter.printf("%s%n,%s%n", pizzaName, pizza.getDescription());
        }
    }

    private void clearPizzaNameField() {
        pizzaNameTextField.clear();
        favouriteCheckbox.setSelected(false);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void handleOrder() {
        if (orderTypeComboBox.getValue() == null || paymentTypeComboBox.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Required Fields", "First select Order Type & Payment Method");
        } else {
            PizzaComponent finalizedPizza = getDecoratedPizza(); // Get the decorated pizza
            if (finalizedPizza != null) {
                // Use the extracted method to create the order
                Order order = createOrder(finalizedPizza);
                // Create and execute the PlaceOrderCommand
                PlaceOrderCommand placeOrderCommand = new PlaceOrderCommand(order);

                if (Objects.equals(order.getOrderType(), "Pick Up")) {
                    commandInvoker.executeCommand(placeOrderCommand);
                    showAlert(Alert.AlertType.INFORMATION, "Order Placed", "Your order has been placed successfully!, You can pickup your order at our Kalutara outlet when Order is out for delivery.");
                } else if (Objects.equals(order.getOrderType(), "Delivery")) {
                    PaymentContext paymentContext = new PaymentContext();
                    String paymentType = paymentTypeComboBox.getValue();
                    if ("Card".equals(paymentType)) {
                        paymentContext.setPaymentStrategy(new CardPaymentStrategy("1234-5678-9876-5432", placeOrderCommand, commandInvoker));
                    } else if ("Digital Wallet".equals(paymentType)) {
                        paymentContext.setPaymentStrategy(new DigitalWalletPaymentStrategy(user, order.getTotalPrice(), placeOrderCommand, commandInvoker)); // Example wallet ID
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Payment Error", "Invalid payment method selected!");
                        return;
                    }
                }
            }
        }
    }

    private int getLastOrderId() {
        String filePath = "orders.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            if(reader.readLine() == null){
                return 0;
            } else {
                return reader.readLine().length();
            }
        } catch (IOException e) {
            showAlert("Error", "Error reading orders file: " + e.getMessage());
        }
        return 0;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Order createOrder(PizzaComponent finalizedPizza) {

        return new Order(
                (getLastOrderId() +1),
                finalizedPizza,
                user.getUsername(),
                user.getName(),
                user.getPhone(),
                user.getAddress(),
                finalizedPizza.getPrice(),
                orderTypeComboBox.getValue(),
                paymentTypeComboBox.getValue(),
                "Pending"
        );
    }

    private double getCrustPrice(String crust) {
        switch (crust) {
            case "Thin":
                return 50.0;
            case "Thick":
                return 80.0;
            case "Stuffed":
                return 100.0;
            default:
                return 0.0;
        }
    }

    private double getCheesePrice(String cheese) {
        switch (cheese) {
            case "Mozzarella":
                return 200.0;
            case "Cheddar":
                return 250.0;
            case "Vegan Cheese":
                return 230.0;
            default:
                return 0.0;
        }
    }

    private double getSaucePrice(String sauce) {
        switch (sauce) {
            case "Tomato":
                return 50.0;
            case "Barbecue":
                return 150.0;
            case "Pesto":
                return 20.0;
            default:
                return 0.0;
        }
    }

    private double getToppingPrice(String topping) {
        return switch (topping) {
            case "Genza" -> 120.0;
            case "Pepperoni", "Mushrooms", "Onions", "Olives" -> 60.0;
            case "Bacon", "Pineapple" -> 80.0;
            default -> 0.0;
        };
    }

    private void updateTotalPrice(double price) {
        totalPrice += price;
        totalPriceLabel.setText("Total Price: $" + totalPrice);
    }


    //    Chain of responsibility pattern
    private CustomizationHandler setupCustomizationChain() {
        CustomizationHandler crustHandler = new CrustHandler();
        CustomizationHandler cheeseHandler = new CheeseHandler();
        CustomizationHandler sauceHandler = new SauceHandler();
        CustomizationHandler toppingHandler = new ToppingHandler();

        crustHandler.setNextHandler(cheeseHandler);
        cheeseHandler.setNextHandler(sauceHandler);
        sauceHandler.setNextHandler(toppingHandler);

        return crustHandler;
    }

    private Pizza getPizzaSummary() {
        if (crustComboBox.getValue() == null || cheeseComboBox.getValue() == null || sauceComboBox.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Required Fields", "Crust, Cheese, and Sauce are required fields!!!");
            return null;
        }

        Pizza pizza = new PizzaBuilder().build(); // Start with a new pizza
        CustomizationHandler customizationChain = setupCustomizationChain();

        // Process crust customization
        String crust = crustComboBox.getValue();
        CustomizationRequest crustRequest = new CustomizationRequest("Crust", crust, getCrustPrice(crust));
        customizationChain.handleRequest(crustRequest, pizza);

        // Process cheese customization
        String cheese = cheeseComboBox.getValue();
        CustomizationRequest cheeseRequest = new CustomizationRequest("Cheese", cheese, getCheesePrice(cheese));
        customizationChain.handleRequest(cheeseRequest, pizza);

        // Process sauce customization
        String sauce = sauceComboBox.getValue();
        CustomizationRequest sauceRequest = new CustomizationRequest("Sauce", sauce, getSaucePrice(sauce)); // Correct method call
        customizationChain.handleRequest(sauceRequest, pizza);

        // Process topping customizations
        for (int i = 0; i < toppingsVBox.getChildren().size(); i++) {
            if (toppingsVBox.getChildren().get(i) instanceof CheckBox checkBox && checkBox.isSelected()) {
                String topping = checkBox.getText();
                CustomizationRequest toppingRequest = new CustomizationRequest("Topping", topping, getToppingPrice(topping));
                customizationChain.handleRequest(toppingRequest, pizza);
            }
        }

        return pizza;
    }

    //    Decorator pattern
    private PizzaComponent getDecoratedPizza() {
        // Start with the base pizza
        PizzaComponent decoratedPizza = getPizzaSummary();

        // Add extra cheese if selected
        if (extraCheeseCheckbox.isSelected()) {
            double extraCheeseCost = 100.0; //  cost for special packaging
            decoratedPizza = new ExtraCheeseDecorator(decoratedPizza, extraCheeseCost);
        }

        // Add special packaging if selected
        if (specialPackagingCheckbox.isSelected()) {
            double packagingCost = 75.0; //  cost for special packaging
            decoratedPizza = new SpecialPackagingDecorator(decoratedPizza, packagingCost);
        }

        return decoratedPizza;
    }

}

