import java.time.LocalDate;

public class Evento {
    private String title;
    private LocalDate date;
    private int totalSeats;
    private int bookedSeats;

    public Evento(String title, LocalDate date, int totalSeats){

        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento è gia passata");
        } else if (totalSeats <= 0) {
            throw new IllegalArgumentException("Il numero dei posti deve essere positivo");            
        }
        this.title = title;
        this.date = date;
        this.totalSeats = totalSeats;
        this.bookedSeats = 0;
    }
}
