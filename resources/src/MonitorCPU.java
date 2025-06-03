import java.util.*;

// Critical consumption
class ConsumoCriticoException extends Exception {
    public ConsumoCriticoException(String mensaje) {
        super(mensaje);
    }
}

public class MonitorCPU {
    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);
        Set<Double> consumos = new HashSet<>();

        try {
            System.out.println("Insert server's CPU consumption (%). Write 'end' to finish: ");

            while (true) {
                System.out.println("CPU %: ");
                String entrada = scanner.nextLine();

                if (entrada.equalsIgnoreCase("end")) break;

                try {
                    double valor = Double.parseDouble(entrada);

                    if (valor < 0 || valor > 100) {
                        System.out.println(" Value out of range (0-100).");
                        continue;
                    }

                    if (!consumos.add(valor)) {
                        System.out.println("Duplicate value! Already registered");
                        continue;
                    }

                    if (valor > 95) {
                        throw new ConsumoCriticoException("Critical consumption detected " + valor + "%");
                    }

                    System.out.println("Consumption successfully registered.");

                } catch (NumberFormatException e) {
                    System.out.println("Invalid entry! Insert valid number.");
                } catch (ConsumoCriticoException e) {
                    System.out.println(e.getMessage());
                }
            }
        } finally {
            scanner.close();
            System.out.println("Monitoring Completed. Total valid records: " + consumos.size());
        }

    }
}