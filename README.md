# Currency Converter

A Java-based desktop application that allows users to convert amounts between different currencies using real-time exchange rates fetched from an external API.

---

## What It Does

This application provides a user-friendly interface for converting currency amounts. Key features include:
- **Real-Time Exchange Rates**: Fetches up-to-date exchange rates from an external API.
- **Simple GUI**: Built using Java Swing for ease of use.
- **Error Handling**: Gracefully handles invalid inputs and API-related issues.

---

## Technologies Used

- **Programming Language**: Java
- **GUI Framework**: Swing (for building the graphical user interface)
- **API**: Open Exchange Rates (for fetching real-time exchange rates)

---

## How to Use These Files

1. **Clone or Download the Repository**:
   - If you cloned the repository:
     ```bash
     git clone https://github.com/your-github-nickname/currency-converter.git
     cd currency-converter
     ```
   - Alternatively, download the ZIP file and extract it.

2. **Set Up the API Key**:
   - Open the `CurrencyConverter.java` file in a text editor or IDE.
   - Replace `YOUR_API_KEY` in the following line with your actual API key from [Open Exchange Rates](https://openexchangerates.org/):
     ```java
     private static final String API_URL = "https://openexchangerates.org/api/latest.json?app_id=YOUR_API_KEY";
     ```

3. **Compile the Program**:
   - Open a terminal or command prompt in the project folder.
   - Compile the Java file:
     ```bash
     javac CurrencyConverter.java
     ```

4. **Run the Application**:
   - After successful compilation, run the program:
     ```bash
     java CurrencyConverter
     ```

5. **Using the Application**:
   - Enter the amount you want to convert in the "Amount" field.
   - Select the source currency ("From Currency") and target currency ("To Currency") from the dropdown menus.
   - Click the "Convert" button to see the converted amount.

---

## Created By

[@ColdBoy66](https://github.com/ColdBoy66)

---

Thank you for checking out this project! 😊
