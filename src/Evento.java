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

    //Metodo per eseguire una prenotazione
    public void makeReservation(int reservations){
        if (reservations < 0) {
            throw new IllegalArgumentException("Il numero di prenotazioni dev'essere positivo");
        } else if (bookedSeats == totalSeats) {
            throw new IllegalArgumentException("Non ci sono posti disponibili");
        } else if (bookedSeats + reservations > totalSeats) {
            throw new IllegalArgumentException("Non ci sono posti a sufficienza per il numero di persone richiesto, sono disponibili " + (totalSeats - bookedSeats) + " posti");
        } else {
            bookedSeats += reservations;
        }
    }

    //Metodo per disdire una prenotazione
    public void cancelReservation(int reservations){
        if (reservations <= 0) {
            throw new IllegalArgumentException("Il numero di cancellazioni dev'essere positivo");
        } else if (bookedSeats == 0) {
            throw new IllegalArgumentException("Non ci sono prenotazioni");
        } else if (bookedSeats - reservations < 0) {
            throw new IllegalArgumentException("Non ci sono cosi tante prenotazioni. Al momento il numero di prenotazioni sono " + bookedSeats);
        } else {
            bookedSeats -= reservations;
        }
    }

}
