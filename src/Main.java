import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        //Input informazioni evento
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci il titlo dell'evento");
        String title = input.nextLine();

        System.out.println("Inserisci la data dell'evento in formato (gg/mm/aaaa)");
        String dateString = input.nextLine();

        System.out.println("Inserisci l'orario dell'evento");
        String timeString = input.nextLine();

        System.out.println("Inserisci il numero di posti totali dell'evento");
        int totalSeats = input.nextInt();

        System.out.println("Inserisci il costo del bigletto");
        double price = input.nextDouble();
        input.nextLine();

         //Formattazione della data e ora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate date = LocalDate.parse(dateString, formatter);
        LocalTime time = LocalTime.parse(timeString, timeFormatter);

        Concerto event = new Concerto(title, date, totalSeats, time, price);
    }
}
