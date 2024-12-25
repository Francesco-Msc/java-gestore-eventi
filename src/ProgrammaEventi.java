import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgrammaEventi {
    private String title;
    private List<Evento> events;

    public ProgrammaEventi(String title){
        this.title = title;
        this.events = new ArrayList<>();
    }

    public void addEvent(Evento event){
        events.add(event);
    }

    public void clearEvents(){
        events.clear();
    }

    public int getEvents(){
        return events.size();
    }

    //Metodo per ottenere eventi in una data specifica
    public List<Evento> getEventPerDate(LocalDate inputDate){
        List<Evento> dateEvents = new ArrayList<>();
        for (Evento element : events) {
            if (element.getDate().equals(inputDate)) {
                dateEvents.add(element);
            }
        }
        return dateEvents;
    }

    public List<Evento> getEventsTitles(){
        return events;
    }

    //Metodo per ottenere tutti gli eventi in ordine cronologico
    @Override
    public String toString() {
    
    events.sort((e1, e2) -> e1.getDate().compareTo(e2.getDate()));

    StringBuilder result = new StringBuilder(title + "\n");

    for (Evento element : events) {
        result.append(element.getDate())
              .append(" - ")
              .append(element.getTitle())
              .append("\n");
    }
    return result.toString();
}
}
