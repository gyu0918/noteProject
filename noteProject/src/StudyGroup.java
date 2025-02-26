import java.util.*;

public class StudyGroup {
     private String StudyName;
     private List<Note> noteList = new ArrayList<>();

    public String getStudyName() {
        return StudyName;
    }

    public void setStudyName(String studyName) {
        StudyName = studyName;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }
}
