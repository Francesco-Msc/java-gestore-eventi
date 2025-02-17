import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
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
            System.out.println("4. Aggiungi una prenotazione");
            System.out.println("5. Disdici una prenotazione");
            System.out.println("6. Svuota il programma");
            System.out.println("7. Esci");

            int choice = -1;
            while (choice == -1) {
                try {
                    System.out.print("Scegli un'opzione: ");
                    choice = input.nextInt();
                    input.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("\nErrore: Inserisci un numero valido (1-7).");
                    input.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("\nInserisci il titolo dell'evento: ");
                        String title = input.nextLine();

                        LocalDate date = null;
                        while (date == null) {
                            try {
                                System.out.print("Inserisci la data dell'evento (gg/mm/aaaa): ");
                                String dateString = input.nextLine();
                                date = LocalDate.parse(dateString, dateFormatter);
                            } catch (DateTimeParseException e) {
                                System.out.println("\nIl foramto della data è sbagliato per favore inserisci la data con questo formato (gg/mm/aaaa)");
                            }
                        }

                        LocalTime time = null;
                        while (time == null) {
                            try {
                                System.out.print("Inserisci l'orario dell'evento (HH:mm): ");
                                String timeString = input.nextLine();
                                time = LocalTime.parse(timeString, timeFormatter);
                            } catch (DateTimeParseException e) {
                                System.out.println("\nIl formato dell'orario è sbagliato per favore inserisci l'orario con questo formato (hh/mm)");
                            }
                        }

                        int totalSeats = 0;
                        while (totalSeats <= 0) {
                            try {
                                System.out.print("Inserisci il numero totale di posti: ");
                                int seats = input.nextInt();
                                totalSeats = seats;
                                if (totalSeats <= 0) {
                                    System.out.println("\nIl numero di posti deve essere maggiore di zero.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("\nErrore: per favore inserisci un numero valido.");
                                input.nextLine();
                            }
                        }

                        double price = -1.0;
                        while (price < 0.0) {
                            try {
                                System.out.print("Inserisci il costo del biglietto: ");
                                double ticketPrice = input.nextDouble();
                                price = ticketPrice;
                                input.nextLine();
                                if (price < 0) {
                                    System.out.println("\nIl prezzo dev'essere maggiore o uguale a 0");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("\nErrore: per favore inserisci un numero valido.");
                                input.nextLine();
                            }
                        }

                        Concerto event = new Concerto(title, date, totalSeats, time, price);
                        eventProgram.addEvent(event);

                        System.out.println("\n Evento aggiunto con successo!");

                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErrore: " + e.getMessage());
                    }
                    break;

                case 2:

                    LocalDate searchDate = null;
                    while (searchDate == null) {
                        try {
                            System.out.print("\nInserisci la data (gg/mm/aaaa) in cui vuoi cercare eventi: ");
                            String search = input.nextLine();
                            searchDate = LocalDate.parse(search, dateFormatter);
        
                            List<Evento> eventsOnDate = eventProgram.getEventPerDate(searchDate);
                            if (eventsOnDate.isEmpty()) {
                                System.out.println("\nNon ci sono eventi in questa data.");
                                break;
                            } else {
                                for (Evento events : eventsOnDate) {
                                    System.out.println("\n" + events);
                                }
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("\nErrore: il formato della data è sbagliato. Per favore, inserisci la data nel formato (gg/mm/aaaa).");
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
                    if (eventProgram.getEvents() == 0) {
                        System.out.println("\nNon ci sono eventi in programma");
                        break;
                    }

                    System.out.println("\nInserisci il titolo dell'evento che vuoi prenotare: ");
                    String bookTitle = input.nextLine();

                    Evento choosenEvent = null;
                    for (Evento element : eventProgram.getEventsTitles()) {
                        if (element.getTitle().equalsIgnoreCase(bookTitle)) {
                            choosenEvent = element;
                            break;
                        }
                    }

                    if (choosenEvent == null) {
                        System.out.println("\nNon ci sono eventi con il titolo " + bookTitle);
                    } else {
                        try {
                            System.out.print("Inserisci il numero di prenotazioni che vuoi effettuare: ");
                            int bookingsNumber = input.nextInt();
                            input.nextLine();
                            choosenEvent.makeReservation(bookingsNumber);
                            System.out.println("\nPrenotazione effettuata!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("\nErrore: " + e.getMessage());
                        } catch (InputMismatchException e) {
                            System.out.println("\nErrore: Inserisci un numero valido di prenotazioni");
                            input.nextLine();
                        }
                    }
                    break;

                case 5:
                    if (eventProgram.getEvents() == 0) {
                        System.out.println(
                                "\nNon è possibile disdire prenoatzioni perchè non ci sono eventi in programma");
                        break;
                    }

                    System.out.println("\nInserisci il titolo dell'evento che vuoi disdire: ");
                    String cancelBooking = input.nextLine();

                    Evento choosenBooking = null;
                    for (Evento element : eventProgram.getEventsTitles()) {
                        if (element.getTitle().equalsIgnoreCase(cancelBooking)) {
                            choosenBooking = element;
                            break;
                        }
                    }

                    if (choosenBooking == null) {
                        System.out.println("\nNon ci sono prenotazioni con il titolo " + cancelBooking);
                        break;
                    } else {
                        try {
                            System.out.print("Inserisci il numero di prenotazioni che vuoi disdire: ");
                            int cancelBookingsNumber = input.nextInt();
                            input.nextLine();
                            choosenBooking.cancelReservation(cancelBookingsNumber);
                            System.out.println("\nDisdetta effettuata!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Errore: " + e.getMessage());
                        } catch (InputMismatchException e) {
                            System.out.println("\nErrore: Inserisci un numero valido di disdette");
                            input.nextLine();
                        }
                    }
                    break;

                case 6:
                    if (eventProgram.getEvents() == 0) {
                        System.out.println("\nNon ci sono eventi in programma");
                        break;
                    }

                    System.out.println("\nSei sicuro di voler eliminare tutti gli eventi in programma? S/N");
                    String answer = input.nextLine();
                    if (answer.equalsIgnoreCase("S")) {
                        eventProgram.clearEvents();
                        System.out.println("\nLista svuotata con successo!");
                    }
                    break;

                case 7:
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
