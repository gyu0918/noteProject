public class NoteContent {
    private String title;
    private String content;
    private String date;
    //private boolean openCheck = false;
    private int joinCount = 0;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

//    public boolean isOpenCheck() {
//        return openCheck;
//    }
//
//    public void setOpenCheck(boolean openCheck) {
//        this.openCheck = openCheck;
//    }

    public int getJoinCount() {
        return joinCount;
    }

    public void setJoinCount(int joinCount) {
        this.joinCount = joinCount;
    }
}
