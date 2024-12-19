import java.util.ArrayList;
import java.util.List;

public class ProgrammaEventi {
    private String title;
    private List<Evento> events;

    public ProgrammaEventi(String title){
        this.title = title;
        this.events = new ArrayList<>();
    }
}
