package kata5p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kata5P1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:KATA5.DB");
        
        Statement st = con.createStatement();
        
        String query = "SELECT * FROM PEOPLE";
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()) {
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
        }
        
        query = "CREATE TABLE IF NOT EXISTS MAIL ('Id' INTEGER PRIMARY KEY AUTOINCREMENT , 'Mail' TEXT NOT NULL);";
        st.execute(query);
        
        String filename = "emails.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
        String mail;
            
        while ((mail = reader.readLine())!=null){
            if(!mail.contains("@")){
                continue;
            }
            query = "INSERT INTO MAIL (MAIL) VALUES('" + mail + "');";
            st.executeUpdate(query);
                
        }
        System.out.println("Fin OK");
    }
    
}
