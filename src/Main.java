import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ProgrammaEventi eventProgram = new ProgrammaEventi("Programma eventi:\n");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        
        boolean running = true;
        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Aggiungi un evento");
            System.out.println("2. Visualizza eventi in una data specifica");
            System.out.println("3. Visualizza tutti gli eventi");
            System.out.println("4. Svuota il programma");
            System.out.println("5. Esci");
            System.out.print("Scegli un'opzione: ");
            int choice = input.nextInt();
            input.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Inserisci il titolo dell'evento: ");
                    String title = input.nextLine();

                    System.out.print("Inserisci la data dell'evento (gg/mm/aaaa): ");
                    String dateString = input.nextLine();

                    System.out.print("Inserisci l'orario dell'evento (HH:mm): ");
                    String timeString = input.nextLine();

                    System.out.print("Inserisci il numero totale di posti: ");
                    int totalSeats = input.nextInt();

                    System.out.print("Inserisci il costo del biglietto: ");
                    double price = input.nextDouble();
                    input.nextLine();  

                    LocalDate date = LocalDate.parse(dateString, dateFormatter);
                    LocalTime time = LocalTime.parse(timeString, timeFormatter);

                    Concerto event = new Concerto(title, date, totalSeats, time, price);
                    eventProgram.addEvent(event);

                    System.out.println("\n Evento aggiunto con successo!");
                    break;

                case 2:
                    System.out.print("Inserisci la data (gg/mm/aaaa) in cui vuoi cercare eventi.");
                    String search = input.nextLine();
                    LocalDate searchDate = LocalDate.parse(search, dateFormatter);

                    List<Evento> eventsOnDate = eventProgram.getEventPerDate(searchDate);
                    if (eventsOnDate.isEmpty()) {
                        System.out.println("Non ci sono eventi in questa data.");
                        break;
                    } else {
                        for (Evento events : eventsOnDate) {
                            System.out.println(events);
                        }
                    }
                    break;

                case 3:
                    if (eventProgram.getEvents() == 0) {
                        System.out.println("\nNon ci sono eventi in programma");
                    } else {
                        System.out.println("\nCi sono un totale di " + eventProgram.getEvents() + " Eventi.");
                        System.out.println(eventProgram.toString());
                    }
                    break;
                
                case 4:
                    if (eventProgram.getEvents() == 0){
                        System.out.println("\nNon ci sono eventi in programma");
                        break;
                    } 

                    System.out.println("Sei sicuro di voler eliminare tutti gli eventi in programma? S/N");
                    String answer = input.nextLine();
                    if (answer.equalsIgnoreCase("S")) {
                        eventProgram.clearEvents();
                        System.out.println("Lista svuotata con successo!");
                    } 
                    break;

                case 5:
                    System.out.println("\nUscita dal programma...");
                    running = false;
                    break;
            
                default:
                    System.out.println("\nScelta non valida.");
                    break;
            }
        } 
        input.close();
        System.out.println("Programma terminato.\n");
    }
}
