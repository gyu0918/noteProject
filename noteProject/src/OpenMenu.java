import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OpenMenu {

    public OpenMenu() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Login login = new Login();
        String userId = null;

        while(true){
            System.out.println(" 1. 회원가입 2. 로그인 ");
            int choice = Integer.parseInt(br.readLine());
            if (choice == 1){
                while(true){
                    System.out.println(" 회원가입할 아이디를 적어 주세요.");
                    String id = br.readLine();
                    if (login.checkId(id)){
                        System.out.println("비밀번호를 입력하세요.");
                        login.signUp(id, br.readLine());
                        userId = id;
                        break ;
                    }
                    System.out.println(" 아이디가 중복 입니다. ");
                }
                break ;

            }else if (choice == 2){
                while(true) {
                    System.out.println(" 로그인 아이디를 입력해주세요.");
                    String id = br.readLine();
                    if (login.checkId(id) == false){
                        while(true){
                            System.out.println(" 비밀 번호를 입력하세요.");
                            String password = br.readLine();
                            if (login.checkPwd(id,password)){
                                userId = id;
                                break ;
                            }
                            System.out.println(" 비밀번호가 달라요 다시 입력!!!!");
                        }
                        break ;
                    }
                    System.out.println(" 아이디가 없습니다 !!!1");
                }
                break ;
            }
        }
        //로그인 아이디를 넘겨준다.
        Service service = new Service(userId);

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
