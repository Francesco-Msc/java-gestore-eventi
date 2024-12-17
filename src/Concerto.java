import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    //Getter e esetter per l'ora
    public LocalTime getTime(){
        return time;
    }

    public void setTime(LocalTime time){
        if (time == null) {
            throw new IllegalArgumentException("Inserisci un'orario valido.");
        }
        this.time = time;
    }

    //Getter e setter per il prezzo
    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        if (price < 0) {
            throw new IllegalArgumentException("Il prezzo non può essere negativo.");
        }
        this.price = price;
    }

    //Metodo per restituire la data e ora formattata
    public String getFormattedDateTime() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        return getDate().format(dateFormatter) + " " + time.format(timeFormatter);
    }

    //Metodo per restituire il prezzo formattato
    public String getFormattedPrice() {
        DecimalFormat priceFormatter = new DecimalFormat("##,##0.00€");
        return priceFormatter.format(price);
    }

    @Override
    public String toString(){
        return getFormattedDateTime() + "-" + getTitle() + "-" + getFormattedPrice();
    }
}
