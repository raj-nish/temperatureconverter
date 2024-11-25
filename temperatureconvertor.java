import java.util.Scanner;

public class TemperatureConverter {

    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }

    public static double fahrenheitToKelvin(double fahrenheit) {
        return celsiusToKelvin(fahrenheitToCelsius(fahrenheit));
    }

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static double kelvinToFahrenheit(double kelvin) {
        return celsiusToFahrenheit(kelvinToCelsius(kelvin));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Temperature Converter");
            System.out.println("Enter temperature value: ");
            double temperature = getTemperature(scanner);
            
            System.out.println("Select source scale (C for Celsius, F for Fahrenheit, K for Kelvin): ");
            char sourceScale = getScale(scanner);
            
            System.out.println("Select target scale (C for Celsius, F for Fahrenheit, K for Kelvin): ");
            char targetScale = getScale(scanner);
            
            double convertedTemperature = convertTemperature(temperature, sourceScale, targetScale);
            System.out.printf("Converted temperature: %.2f %s%n", convertedTemperature, targetScale);
            
            System.out.println("Do you want to convert another temperature? (yes/no)");
            String continueChoice = scanner.nextLine().trim().toLowerCase();
            if (!continueChoice.equals("yes")) {
                break;
            }
        }
        
        scanner.close();
    }

    private static double getTemperature(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid temperature.");
            }
        }
    }

    private static char getScale(Scanner scanner) {
        while (true) {
            String scale = scanner.nextLine().trim().toUpperCase();
            if (scale.equals("C") || scale.equals("F") || scale.equals("K")) {
                return scale.charAt(0);
            } else {
                System.out.println("Invalid scale! Please enter C, F, or K.");
            }
        }
    }

    private static double convertTemperature(double temperature, char sourceScale, char targetScale) {
        if (sourceScale == targetScale) {
            return temperature;
        }

        switch (sourceScale) {
            case 'C':
                if (targetScale == 'F') return celsiusToFahrenheit(temperature);
                if (targetScale == 'K') return celsiusToKelvin(temperature);
                break;
            case 'F':
                if (targetScale == 'C') return fahrenheitToCelsius(temperature);
                if (targetScale == 'K') return fahrenheitToKelvin(temperature);
                break;
            case 'K':
                if (targetScale == 'C') return kelvinToCelsius(temperature);
                if (targetScale == 'F') return kelvinToFahrenheit(temperature);
                break;
        }
        
        throw new IllegalArgumentException("Invalid conversion request.");
    }
}
