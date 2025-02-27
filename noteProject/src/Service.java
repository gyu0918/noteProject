import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Service {
    private List<StudyGroup> studyGroupList = new ArrayList<>();
    private String id;
    public Service(String id) {
        this.id = id;
    }

    private void searchTool(String studyGroupName, String subjectName, String noteName, String searchId, int funchtionNum, String newString) {
        for (StudyGroup studyGroup : studyGroupList) {
            if (studyGroup.getStudyName().equals(studyGroupName)) {
                List<Note> noteList = studyGroup.getNoteList();
                for (Note note : noteList) {
                    if (note.getId().equals(searchId)) {
                        List<NoteContent> searchList = note.getNoteMap().get(subjectName);
                        for (NoteContent noteContent : searchList) {
                            if (noteContent.getTitle().equals(noteName)) {
                                if (funchtionNum == 1){
                                    searchList.remove(noteContent);
                                }else if (funchtionNum == 2){
                                    noteContent.setTitle(newString);
                                }else if (funchtionNum == 3){
                                    noteContent.setContent(newString);
                                }else if (funchtionNum == 4){
                                    System.out.println("노트 제목  = " + noteContent.getTitle());
                                    System.out.println("노트 내용 = " + noteContent.getContent());
                                    System.out.println("노트 날짜 = " + noteContent.getDate());
                                    int joinCount  = noteContent.getJoinCount() + 1;
                                    noteContent.setJoinCount(joinCount); ;
                                }
                                return ;
                            }
                        }
                    }
                }
            }
        }
    }
    public void addNote() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(" 1 :  노트 추가 || 2 : 노트 삭제 || 3 : 노트 수정");
        int choice = Integer.parseInt(br.readLine());
        switch (choice) {
            case 1:
                System.out.println(" 그룹 이름, 과목, 제목 내용 날짜 적으세요");
                String StudyGroupName = br.readLine();
                String SubjectName = br.readLine();
                String NoteName = br.readLine();
                String NoteContent = br.readLine();
                String NoteDate = br.readLine();

                //원래 과목까지 모두 있는 경우
                //스터디 모임-> id -> 해당 과목-> 노트 내용리스트로
                for (StudyGroup studyGroup : studyGroupList) {
                    if (studyGroup.getStudyName().equals(StudyGroupName)) {
                        //id에 해당하는 Note클래스 찾는곳
                        List<Note> noteList = studyGroup.getNoteList();
                        while (true) {
                            boolean flag = false;
                            for (Note note : noteList) {
                                if (note.getId().equals(id)) {
                                    flag = true;
                                    //노트 생성
                                    NoteContent noteContent = new NoteContent();
                                    noteContent.setTitle(NoteName);
                                    noteContent.setContent(NoteContent);
                                    noteContent.setDate(NoteDate);
                                    //Note클래스안에 멥에다가 없으면 새로 생성하고 있으면 넣는다.
                                    if (!note.getNoteMap().containsKey(SubjectName)) {
                                        note.getNoteMap().put(SubjectName, new ArrayList<>());
                                    }
                                    note.getNoteMap().get(SubjectName).add(noteContent);
                                }
                            }
                            if (flag) {
                                break;
                            }
                            // 해당아이디에 Note가 없으니까 추가해준자
                            Note note = new Note();
                            note.setId(id);
                            noteList.add(note);
                        }
                    }
                }

                break;
            case 2:
                //삭제
                System.out.println(" 삭제 하고 싶은 그룹 이름, 과목, 제목 적으세요");
                String removeStudyGroupName = br.readLine();
                String removeSubjectName = br.readLine();
                String removeNoteName = br.readLine();

                //노트 검색부분
                searchTool(removeStudyGroupName, removeSubjectName, removeNoteName ,id, 1, null);

                break;
            case 3:
                //수정
                System.out.println(" 수정 하고 싶은노트가 있는  그룹 이름, 과목, 제목 적으세요");
                String updateStudyGroupName = br.readLine();
                String updateSubjectName = br.readLine();
                String updateNoteName = br.readLine();
                System.out.println("1. 제목수정 || 2. 노트 내용 수정");
                int choiceNum = Integer.parseInt(br.readLine());
                if (choiceNum == 1) {
                    System.out.println("수정할 제목을 입력하세요.");
                    String newNoteName = br.readLine();
                    //검색해서 들어가기
                    searchTool(updateStudyGroupName, updateSubjectName, updateNoteName ,id, 2, newNoteName);


                } else if (choiceNum == 2) {
                    System.out.println("수정할 내용을 입력하세요.");
                    String newNoteContent = br.readLine();
                    //검색해서 들어가기
                    searchTool(updateStudyGroupName, updateSubjectName, updateNoteName ,id, 3, newNoteContent);
                }
                break;
        }
    }



    public void searchNote() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("1. 제목으로 조회 2. 인기글 보기");
        int choiceNum = Integer.parseInt(br.readLine());
        if (choiceNum == 1) {
            //여기에 조회수 부분 추가하기
            System.out.println("찾고 싶은 스터디 이름, 아이디, 과목 을 입력하세요.");
            String searchStudyGroupName = br.readLine();
            String searchId = br.readLine();
            String searchSubjectName = br.readLine();
            for (StudyGroup studyGroup : studyGroupList) {
                if (studyGroup.getStudyName().equals(searchStudyGroupName)) {
                    List<Note> noteList = studyGroup.getNoteList();
                    for (Note note : noteList) {
                        if (note.getId().equals(searchId)) {
                            List<NoteContent> removeList = note.getNoteMap().get(searchSubjectName);
                            for (NoteContent noteContent : removeList) {
                                System.out.println("제목 = " + noteContent.getTitle());
                            }
                            System.out.println("어떤 제목을 조회 하시겠습니가? ");
                            String noteTitle = br.readLine();
                            searchTool(searchStudyGroupName,searchSubjectName,noteTitle,searchId,4,null);
                            return ;
                        }
                    }
                }
            }




        } else if (choiceNum == 2) {
            //인기글 조회수 기준으로 5개 까지 올리기 글! treeMap으로
            TreeMap<Integer, Set<NoteContent>> rank = new TreeMap<>(Collections.reverseOrder());
            //탐색해서 조회수 int로 저장하고 거기서 treeMap에다가 객체 저장
            for (StudyGroup studyGroup : studyGroupList) {
                for (Note note : studyGroup.getNoteList()) {
                    for (String key : note.getNoteMap().keySet()) {
                        List<NoteContent> noteList = note.getNoteMap().get(key);
                        for (NoteContent noteContent : noteList) {
                            int score = noteContent.getJoinCount();
                            rank.putIfAbsent(score, new HashSet<>());
                            rank.get(score).add(noteContent);
                        }
                    }
                }
            }

            //출력
            int count = 1;  // 출력한 항목 수를 추적
            for (Integer score : rank.keySet()) {
                if (count > 5)
                    break;

                // key(조회수)에 해당하는 값(Set<NoteContent>) 가져오기
                Set<NoteContent> noteContents = rank.get(score);

                // 제목 리스트 생성
                List<String> titles = new ArrayList<>();
                for (NoteContent noteContent : noteContents) {
                    titles.add(noteContent.getTitle());
                }

                // 출력
                System.out.println( count +  "등  조회수 = " + score + " 제목 = " + String.join(", ", titles));

                count++;
            }


        }

    }

    public void addStudyGroup() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(" 스터디 그룹 이름을 적으세요.");
        String StudyGroupName = br.readLine();
        StudyGroup studyGroup = new StudyGroup();
        studyGroup.setStudyName(StudyGroupName);
        studyGroupList.add(studyGroup);
    }

    public List<StudyGroup> getStudyGroupList() {
        return studyGroupList;
    }

    public void setStudyGroupList(List<StudyGroup> studyGroupList) {
        this.studyGroupList = studyGroupList;
    }
}
