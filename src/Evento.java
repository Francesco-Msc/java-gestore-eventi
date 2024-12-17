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

    //Getter e setter per Titolo
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    //Getter e setter per Data
    public LocalDate getDate(){
        return date;
    }

    public void setDate(LocalDate date){
        if (date.isBefore(LocalDate.now())) {
            System.out.println("La data dell'evento non può essere nel passato.");
        } else {
            this.date = date;
        }
    }

    //Getter per Posti totali e posti prenotati
    public int getTotalSeats(){
        return totalSeats;
    }

    public int getReservedSeats(){
        return bookedSeats;
    }
}
