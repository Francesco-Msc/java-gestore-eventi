import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean add = true;
        boolean remove = true;

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

        //Ciclo per aggiungere prenotazioni
        while (add) {
            System.out.println("Desideri aggiungere delle prenotazioni? S/N");
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("S")) {
                System.out.println("Inserisci il numero di posti che desideri prenotare. N.B. Sono disponibili " + (totalSeats - event.getReservedSeats()) + " posti");
                int reserve = input.nextInt();
                input.nextLine();

                event.makeReservation(reserve);
                System.out.println("Hai prenotato " + reserve + " posti");
            } else if (!answer.equalsIgnoreCase("S")) {
                add = false;
            }
        }

        //Ciclo per rimuovere prenotazioni
        if (event.getReservedSeats() > 0) {
            while (remove) {
                System.out.println("Desideri rimuovere delle prenotazioni? S/N");
                String answer = input.nextLine();
                if (answer.equalsIgnoreCase("S")) {
                    System.out.println("Inserisci il numero di prenotazioni che desideri rimuovere. N.B. Ci sono " + event.getReservedSeats() + " prenotazioni");
                    int reserve = input.nextInt();
                    input.nextLine();
    
                    event.cancelReservation(reserve);
                    System.out.println("Hai disdetto " + reserve + " prenotazioni");
                } else if (!answer.equalsIgnoreCase("S")) {
                    remove = false;
                }
            }
        }
        input.close();

        System.out.println("Hai prenotato " + event.getReservedSeats() + " posti per l'evento " + event.toString() + " sono rimasti " + (totalSeats - event.getReservedSeats()) + " posti disponibili");
    }
}
