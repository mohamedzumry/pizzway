//package com.example.pizzway.controllers;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.input.MouseEvent;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class OrderNowController {
//
//    // UI Elements
//    @FXML private RadioButton o_rd_lc, o_rd_mc, o_rd_sc;
//    @FXML private TextField o_txt_mc, o_txt_gc, o_txt_pc;
//    @FXML private TextField o_txt_as, o_txt_ps, o_txt_ts;
//    @FXML private TextField o_txt_pt, o_txt_mt, o_txt_ot, o_txt_st, o_txt_spt;
//    @FXML private Button o_btn_review;
//
//    // Hardcoded Prices
//    private final Map<String, Double> crustPrices = Map.of(
//            "large", 10.0, "medium", 8.0, "small", 6.0
//    );
//    private final Map<String, Double> cheesePrices = Map.of(
//            "mozarella", 1.0, "gorgonzola", 1.5, "provolone", 1.2
//    );
//    private final Map<String, Double> saucePrices = Map.of(
//            "Abbabiata", 0.8, "Puttanesca", 0.9, "Tuna", 1.0
//    );
//    private final Map<String, Double> toppingPrices = Map.of(
//            "Pepperoni", 1.5, "Mushrooms", 1.0, "Onions", 0.5, "Sausage", 2.0, "Spinach", 1.2
//    );
//
//    @FXML
//    public void onReviewOrderClicked(MouseEvent event) {
//        // Retrieve crust selection
//        String selectedCrust = getSelectedCrust();
//
//        // Get input values for cheese, sauces, and toppings
//        Map<String, Integer> cheeseSelections = getTextBoxValues(
//                Map.of("mozarella", o_txt_mc, "gorgonzola", o_txt_gc, "provolone", o_txt_pc)
//        );
//        Map<String, Integer> sauceSelections = getTextBoxValues(
//                Map.of("Abbabiata", o_txt_as, "Puttanesca", o_txt_ps, "Tuna", o_txt_ts)
//        );
//        Map<String, Integer> toppingSelections = getTextBoxValues(
//                Map.of("Pepperoni", o_txt_pt, "Mushrooms", o_txt_mt, "Onions", o_txt_ot, "Sausage", o_txt_st, "Spinach", o_txt_spt)
//        );
//
//        // Calculate total price
//        double totalPrice = calculateTotalPrice(selectedCrust, cheeseSelections, sauceSelections, toppingSelections);
//
//        // Populate the order model
//        Order order = new Order();
//        order.setCrust(selectedCrust);
//        order.setCheeseSelections(cheeseSelections);
//        order.setSauceSelections(sauceSelections);
//        order.setToppingSelections(toppingSelections);
//        order.setTotalPrice(totalPrice);
//
//        // Navigate to Order Review Page (assume navigation method exists)
//        navigateToOrderReviewPage(order);
//    }
//
//    private String getSelectedCrust() {
//        if (o_rd_lc.isSelected()) return "large";
//        if (o_rd_mc.isSelected()) return "medium";
//        if (o_rd_sc.isSelected()) return "small";
//        throw new IllegalStateException("No crust selected");
//    }
//
//    private Map<String, Integer> getTextBoxValues(Map<String, TextField> textFieldMap) {
//        Map<String, Integer> selections = new HashMap<>();
//        for (Map.Entry<String, TextField> entry : textFieldMap.entrySet()) {
//            String key = entry.getKey();
//            TextField field = entry.getValue();
//            try {
//                int value = Integer.parseInt(field.getText());
//                if (value > 0) {
//                    selections.put(key, value);
//                }
//            } catch (NumberFormatException e) {
//                // Ignore invalid or empty inputs
//            }
//        }
//        return selections;
//    }
//
//    private double calculateTotalPrice(String crust, Map<String, Integer> cheeseSelections,
//                                       Map<String, Integer> sauceSelections, Map<String, Integer> toppingSelections) {
//        double totalPrice = crustPrices.getOrDefault(crust, 0.0);
//
//        for (Map.Entry<String, Integer> entry : cheeseSelections.entrySet()) {
//            totalPrice += cheesePrices.getOrDefault(entry.getKey(), 0.0) * entry.getValue();
//        }
//        for (Map.Entry<String, Integer> entry : sauceSelections.entrySet()) {
//            totalPrice += saucePrices.getOrDefault(entry.getKey(), 0.0) * entry.getValue();
//        }
//        for (Map.Entry<String, Integer> entry : toppingSelections.entrySet()) {
//            totalPrice += toppingPrices.getOrDefault(entry.getKey(), 0.0) * entry.getValue();
//        }
//
//        return totalPrice;
//    }
//
//    private void navigateToOrderReviewPage(Order order) {
//        System.out.println("Navigating to Order Review Page with order: " + order);
//        // Add your navigation logic here
//    }
//}
