import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConverter {
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_URL = "https://open.er-api.com/v6/latest/";

    public static void main(String[] args) {
        // Get user input
        String baseCurrency = getUserInput("Enter the base currency code (e.g., USD): ");
        String targetCurrency = getUserInput("Enter the target currency code (e.g., EUR): ");
        double amount = Double.parseDouble(getUserInput("Enter the amount to convert: "));

        // Fetch exchange rates
        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);

        // Perform conversion
        double convertedAmount = amount * exchangeRate;

        // Display result
        System.out.printf("%.2f %s is equal to %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
    }

    private static String getUserInput(String prompt) {
        System.out.print(prompt);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            URL url = new URL(API_URL + baseCurrency + "?apikey=" + API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            // Parse JSON response and extract exchange rate
            // Note: This is a simplified example; you may want to use a JSON parsing library for a real-world application
            String jsonResponse = response.toString();
            String targetRate = jsonResponse.split("\"" + targetCurrency + "\":")[1].split(",")[0];
            return Double.parseDouble(targetRate);

        } catch (IOException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
