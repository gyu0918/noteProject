
import java.util.*;

public class Note {
    private String id;
    private Map<String, List<NoteContent>> NoteMap = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, List<NoteContent>> getNoteMap() {
        return NoteMap;
    }

    public void setNoteMap(Map<String, List<NoteContent>> noteMap) {
        NoteMap = noteMap;
    }
}
