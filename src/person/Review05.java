package person;

//IO
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// SQL
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review05 {
    
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // ドライバのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // DBと接続する
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "wakame219"
            );
            
            // DBとやりとりする窓口（PreparedStatementオブジェクト）の作成
            // 検索用SQLおよび検索用PreparedStatementオブジェクトを取得
            String selectSql = "SELECT * FROM person WHERE id = ?";
            pstmt = con.prepareStatement(selectSql);

            // 検索キーワードの入力
            System.out.print("検索キーワードを入力してください > ");
            int num1 = keyInNum();
            
            // 入力されたidをPreparedStatementオブジェクトにセット
            pstmt.setInt(1, num1);
            
            // Query実行結果の格納
            rs = pstmt.executeQuery();
            
            // 結果の出力
            while(rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println(name + "\t" + age);
            }
            
        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバのロードに失敗しました。");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("データベースに異常が発生しました。");
            e.printStackTrace();
        } finally {
            // 8. 接続を閉じる
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("ResultSetを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.err.println("PreparedStatementを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("データベース切断時にエラーが発生しました。");
                    e.printStackTrace();
                }
            }
        }
    }
    
    /*
     * キーボードから入力された値をStringで返す 引数：なし 戻り値：入力された文字列
     */
    private static String keyIn() {
        String line = null;
        try {
            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            line = key.readLine();
        } catch (IOException e) {
        }
        return line;
    }
    
    /*
     * キーボードから入力された値をintで返す 引数：なし 戻り値：int
     */
    private static int keyInNum() {
        int result = 0;
        try {
            result = Integer.parseInt(keyIn());
        } catch (NumberFormatException e) {
        }
        return result;
    }    
    
}    
    
//    
//    
//    public static void main(String[] args) {
//    
//        // Keyboard入力
//        InputStreamReader isr = new InputStreamReader(System.in);
//        BufferedReader br = new BufferedReader(isr);
//        
//        System.out.print("検索キーワードを入力してください > ");
//        
//        String input = null;
//        try {
//            input = br.readLine();
//            br.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        
//        // DB接続
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//    
//        try {
//            // 1. ドライバのクラスをJava上で読み込む
//            // Class.forName("com.mysql.cj.jdbc.Driver");
//            
//            // 2. DBと接続する
//            con = DriverManager.getConnection(
//                "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
//                "root",
//                "wakame219"
//            );
//            
//            // 4. DBとやりとりする窓口（Statementオブジェクト）の作成
//            //String sql = "SELECT name, age FROM person WHERE id = '\" + input + \"'";
//            String insertSql = "SELECT * FROM person WHERE id = ?";
//            pstmt = con.prepareStatement(insertSql);
//            pstmt.setInt(1, input);
//            // 更新するCountryCodeを入力
////          System.out.print("検索キーワードを入力してください > ");
////          String num1 = keyIn();
//            
//            
//            
//            pstmt = con.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//    
//            while (rs.next()) {
//                System.out.println(rs.getString("name"));
//                System.out.println(rs.getInt("age"));
//            }
//    
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (pstmt != null) {
//                try {
//                    pstmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (con != null) {
//                try {
//                    con.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
////    private static String keyIn() {
////        // TODO 自動生成されたメソッド・スタブ
////        return null;
////    }
//}
