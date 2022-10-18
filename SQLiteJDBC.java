
import java.sql.*;

public class SQLiteJDBC {

    /**
     * Connect to a sample database
     */
    public static void connect() {
        Connection conn = null;
        try {
            // db parameters  
            String url = "jdbc:sqlite:C:/sqlite/ASS2.db";
            // create a connection to the database  
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        connect(); //establish connection to database

        /**
         * ********************************************************************
         * Create Student Table
         * ********************************************************************
         */
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/ASS2.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            stmt.executeUpdate("DROP TABLE IF EXISTS STUDENT"); //drops table
            stmt.executeUpdate("DROP TABLE IF EXISTS BADGES"); //drops table
            stmt.executeUpdate("DROP TABLE IF EXISTS ATTENDENCE"); //drops table
            stmt.executeUpdate("DROP TABLE IF EXISTS TESTS"); //drops table
            stmt.executeUpdate("DROP TABLE IF EXISTS TOPICS"); //drops table
            stmt.executeUpdate("DROP TABLE IF EXISTS PARTS"); //drops table

            String student = "CREATE TABLE IF NOT EXISTS STUDENT "
                    + "(ID INT NOT NULL PRIMARY KEY, "
                    + " FirstName VARCHAR(20) NOT NULL, "
                    + " LastName VARCHAR(20) NOT NULL, "
                    + " DOB DATE NOT NULL)";

            String badges = "CREATE TABLE IF NOT EXISTS BADGES "
                    + "(ID INT NOT NULL PRIMARY KEY, "
                    + " Level VARCHAR(1), "
                    + " Types VARCHAR(8)NOT NULL, "
                    + " acquire BOOLEAN)";

            String attendence = "CREATE TABLE IF NOT EXISTS ATTENDENCE"
                    + " (ID INT NOT NULL PRIMARY KEY, "
                    + " Date DATE, "
                    + " attended BOOLEAN)";

            String tests = "CREATE TABLE IF NOT EXISTS TESTS"
                    + " (ID INT NOT NULL PRIMARY KEY, "
                    + " BadgeID INT, "
                    + " Completed BOOLEAN)";

            String topics = "CREATE TABLE IF NOT EXISTS TOPICS"
                    + " (ID INT NOT NULL PRIMARY KEY, "
                    + " TestID INT, "
                    + " Completed BOOLEAN)";

            String parts = "CREATE TABLE IF NOT EXISTS PARTS"
                    + " (ID INT NOT NULL PRIMARY KEY, "
                    + " TopicID INT, "
                    + " Completed BOOLEAN)";

            stmt.executeUpdate(student);
            stmt.executeUpdate(badges);
            stmt.executeUpdate(attendence);
            stmt.executeUpdate(tests);
            stmt.executeUpdate(topics);
            stmt.executeUpdate(parts);
            stmt.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");

        /**
         * ********************************************************************
         * Insert Test Data for 4 Students
         * ********************************************************************
         */
        try {
            Class.forName("org.sqlite.JDBC");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            
            

            String student1 = "INSERT INTO AUTO_INCREMENT STUDENT (ID, FirstName, LastName, "
                    + "DOB) "
                    + "VALUES (1, 'Brock', 'Mathison', '27/11/1971');";
            stmt.executeUpdate(student1);

            String student1_1 = "INSERT INTO badges (ID, Level, Types, "
                    + "acquire) "
                    + "VALUES (1, 'A', 'Diamond', '1');";
            stmt.executeUpdate(student1_1);

            String student2 = "INSERT INTO STUDENT (ID, FirstName, LastName, "
                    + "DOB) "
                    + "VALUES (2, 'Caden', 'Wilson', '01/02/2002');";
            stmt.executeUpdate(student2);

            String student2_1 = "INSERT INTO badges (ID, Level, Types, "
                    + "acquire) "
                    + "VALUES (2, 'B', 'Platinum', '1');";
            stmt.executeUpdate(student2_1);

            String student3 = "INSERT INTO STUDENT (ID, FirstName, LastName, "
                    + "DOB) "
                    + "VALUES (3, 'ChitiPat', 'Marsri', '11/05/2003');";
            stmt.executeUpdate(student3);

            String student3_1 = "INSERT INTO badges (ID, Level, Types, "
                    + "acquire) "
                    + "VALUES (3, 'C', 'Lithium', '1');";
            stmt.executeUpdate(student3_1);

            String student4 = "INSERT INTO STUDENT (ID, FirstName, LastName, "
                    + "DOB) "
                    + "VALUES (4, 'Steven', 'Meng', '21/05/2001');";
            stmt.executeUpdate(student4);

            String student4_1 = "INSERT INTO badges (ID, Level, Types, "
                    + "acquire) "
                    + "VALUES (4, 'A', 'Diamond', '1');";
            stmt.executeUpdate(student4_1);

            stmt.close();
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}
