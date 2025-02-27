import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Login {
    private String url;
    private String user;
    private String password;

    public Login() throws IOException {
        url = "jdbc:mariadb://localhost:3306/user";
        user = "root";
        password = "1234";
    }

    public void signUp(String newId, String newPwd) {
        String query = "INSERT INTO userInfo (id, pwd) VALUES (?, ?)";

        // 연결 (try-with-resource)
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement cursor = conn.prepareStatement(query)) {

            // SQL 파라미터 설정
            cursor.setString(1, newId);
            cursor.setString(2, newPwd);

            // SQL 실행
            cursor.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
    public boolean checkId(String checkId){
        String query = "SELECT * FROM userInfo";

        try(Connection conn = DriverManager.getConnection(url,user,password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

            //ResultSet을 탐색해 id만 꺼내서 비교한다
            boolean found = false;
            while(rs.next()) {
                String check = rs.getString("id");
                if(checkId.equals(check)) {
                    found = true;
                }
            }
            if (found){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return true;
    }

    public boolean checkPwd(String checkId, String checkPwd){
        String query = "SELECT * FROM userInfo";

        try(Connection conn = DriverManager.getConnection(url,user,password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query)){

            //ResultSet 을 탐색해 pwd만 꺼내서 비교한다.
            while(rs.next()) {
                String dbId = rs.getString("id");
                if (checkId.equals(dbId)) {
                    String dbPwd = rs.getString("pwd");
                    if (checkPwd.equals(dbPwd)) {
                        return true;
                    }
                }
            }


        }catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return false;
    }
}
