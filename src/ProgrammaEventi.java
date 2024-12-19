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
}
