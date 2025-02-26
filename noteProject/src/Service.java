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
                for (StudyGroup studyGroup : studyGroupList) {
                    if (studyGroup.getStudyName().equals(removeStudyGroupName)) {
                        List<Note> noteList = studyGroup.getNoteList();
                        for (Note note : noteList) {
                            if (note.getId().equals(id)) {
                                List<NoteContent> removeList = note.getNoteMap().get(removeSubjectName);
                                for (NoteContent noteContent : removeList) {
                                    if (noteContent.getTitle().equals(removeNoteName)) {
                                        removeList.remove(noteContent);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

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
                    //노트 검색부분
                    for (StudyGroup studyGroup : studyGroupList) {
                        if (studyGroup.getStudyName().equals(updateStudyGroupName)) {
                            List<Note> noteList = studyGroup.getNoteList();
                            for (Note note : noteList) {
                                if (note.getId().equals(id)) {
                                    List<NoteContent> removeList = note.getNoteMap().get(updateSubjectName);
                                    for (NoteContent noteContent : removeList) {
                                        if (noteContent.getTitle().equals(updateNoteName)) {
                                            noteContent.setTitle(newNoteName);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }


                } else if (choiceNum == 2) {
                    System.out.println("수정할 내용을 입력하세요.");
                    String newNoteContent = br.readLine();
                    //검색해서 들어가기
                    //노트 검색부분
                    for (StudyGroup studyGroup : studyGroupList) {
                        if (studyGroup.getStudyName().equals(updateStudyGroupName)) {
                            List<Note> noteList = studyGroup.getNoteList();
                            for (Note note : noteList) {
                                if (note.getId().equals(id)) {
                                    List<NoteContent> removeList = note.getNoteMap().get(updateSubjectName);
                                    for (NoteContent noteContent : removeList) {
                                        if (noteContent.getTitle().equals(updateNoteName)) {
                                            noteContent.setContent(newNoteContent);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }


                }
                break;
        }
    }

    public void searchNote() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("자신의 노트들을 찾고 싶으면 1 다른 아이디 노트들을 찾고 싶으면 2 인기글 보고 싶으면 3");
        int choiceNum = Integer.parseInt(br.readLine());
        if (choiceNum == 1) {
            System.out.println("찾고 싶은 노트를 찾을려면 스터디그룹이름, 과목이름을 입력하세요.");
            String StudyGroupName = br.readLine();
            String SubjectName = br.readLine();
            for (StudyGroup studyGroup : studyGroupList) {
                if (studyGroup.getStudyName().equals(StudyGroupName)) {
                    List<Note> noteList = studyGroup.getNoteList();
                    for (Note note : noteList) {
                        if (note.getId().equals(id)) {
                            note.getNoteMap().get(SubjectName)
                                    .stream().forEach(noteContent -> {
                                        System.out.println("noteContent.getTitle() = " + noteContent.getTitle());
                                        System.out.println("noteContent.getContent() = " + noteContent.getContent());
                                        System.out.println("noteContent.getDate() = " + noteContent.getDate());
                                    });
                            break;
                        }
                    }
                    break;
                }
            }


        } else if (choiceNum == 2) {
            //여기에 조회수 부분 추가하기
            System.out.println("찾고 싶은 스터디 이름, 아이디, 과목을 입력하세요.");
            String otherStudyGroupName = br.readLine();
            String otherId = br.readLine();
            String otherSubjectName = br.readLine();
            for (StudyGroup studyGroup : studyGroupList) {
                if (studyGroup.getStudyName().equals(otherStudyGroupName)) {
                    List<Note> noteList = studyGroup.getNoteList();
                    for (Note note : noteList) {
                        if (note.getId().equals(otherId)) {
                            note.getNoteMap().get(otherSubjectName)
                                    .stream().forEach(noteContent -> {
                                        System.out.println("noteContent.getTitle() = " + noteContent.getTitle());
                                        System.out.println("noteContent.getContent() = " + noteContent.getContent());
                                        System.out.println("noteContent.getDate() = " + noteContent.getDate());
                                    });
                            break;
                        }
                    }
                    break;
                }
            }


        } else if (choiceNum == 3) {
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
            int count = 0;  // 출력한 항목 수를 추적
            for (Map.Entry<Integer, Set<NoteContent>> entry : rank.entrySet()) {
                if (count >= 5) {
                    break;  // 5개 출력 후 종료
                }

                // entry.getKey()는 조회수, entry.getValue()는 그 조회수를 가진 NoteContent들의 Set
                int score = entry.getKey();
                Set<NoteContent> noteContents = entry.getValue();

                // 각 NoteContent를 출력
                System.out.print(" 조회수 = " + score + " 내용 = " );
                for (NoteContent noteContent : noteContents) {
                    // NoteContent 안의 내용 출력 (예시로 getContent() 사용)
                    System.out.print(",  " + noteContent.getContent());
                }
                System.out.println();
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
