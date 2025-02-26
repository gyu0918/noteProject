import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OpenMenu {


    public OpenMenu() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(" 아이디 입력");
        Service service = new Service(br.readLine());

        while(true){
            System.out.println("1번 노트 만들기 || 2번 노트 검색 || 3번 스터디모임 만들기 || 4번 종료");
            int menuChoice = Integer.parseInt(br.readLine());
            switch(menuChoice){
                case 1:
                    service.addNote();
                    break ;
                case 2:
                    service.searchNote();
                    break ;
                case 3:
                    service.addStudyGroup();
                    break ;
                case 4:
                    return ;
            }
        }
    }
}
