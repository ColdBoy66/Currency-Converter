import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyConverter {
    private static final String API_URL = "https://openexchangerates.org/api/latest.json?app_id=YOUR_API_KEY"; // Replace with your API key

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyConverter::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();
        JLabel fromLabel = new JLabel("From Currency:");
        String[] currencies = {"USD", "EUR", "GBP", "INR", "JPY"};
        JComboBox<String> fromCurrencyBox = new JComboBox<>(currencies);
        JLabel toLabel = new JLabel("To Currency:");
        JComboBox<String> toCurrencyBox = new JComboBox<>(currencies);

        inputPanel.add(amountLabel);
        inputPanel.add(amountField);
        inputPanel.add(fromLabel);
        inputPanel.add(fromCurrencyBox);
        inputPanel.add(toLabel);
        inputPanel.add(toCurrencyBox);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton convertButton = new JButton("Convert");
        JLabel resultLabel = new JLabel("Result: ");

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    String fromCurrency = (String) fromCurrencyBox.getSelectedItem();
                    String toCurrency = (String) toCurrencyBox.getSelectedItem();

                    JSONObject rates = fetchExchangeRates();
                    double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency, rates);
                    resultLabel.setText("Result: " + String.format("%.2f", convertedAmount) + " " + toCurrency);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Failed to fetch exchange rates. Check your internet connection or API key.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonPanel.add(convertButton);
        buttonPanel.add(resultLabel);

        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private static JSONObject fetchExchangeRates() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return new JSONObject(response.toString()).getJSONObject("rates");
    }

    private static double convertCurrency(double amount, String fromCurrency, String toCurrency, JSONObject rates) {
        double fromRate = rates.getDouble(fromCurrency);
        double toRate = rates.getDouble(toCurrency);

        return (amount / fromRate) * toRate;
    }
}