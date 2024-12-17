import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Concerto extends Evento {
    private LocalTime time;
    private double price;

    public Concerto(String title, LocalDate date, int totalSeats, LocalTime time, double price){
        super(title, date, totalSeats);

        //Combina la data e l'orario dell'evento in un oggetto LocalDateTime per rappresentare il momento preciso dell'evento
        LocalDateTime eventDateTime = LocalDateTime.of(date, time);
        
        if (time == null) {
            throw new IllegalArgumentException("Inserisci un'orario valido.");
        } else if (eventDateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("L'orario da te selezionato è già passato");
        } else if (price < 0) {
            throw new IllegalArgumentException("Il prezzo non può essere negativo.");
        }

        this.time = time;
        this.price = price;
    }
}
