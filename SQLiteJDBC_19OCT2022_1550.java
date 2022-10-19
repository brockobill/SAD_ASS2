
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
            stmt.executeUpdate("DROP TABLE IF EXISTS ATTENDANCE"); //drops table
            stmt.executeUpdate("DROP TABLE IF EXISTS TESTS_COMPLETED"); //drops table
            stmt.executeUpdate("DROP TABLE IF EXISTS TESTS_IN_PROGRESS"); //drops table
            stmt.executeUpdate("DROP TABLE IF EXISTS TOPICS"); //drops table
            stmt.executeUpdate("DROP TABLE IF EXISTS PARTS"); //drops table

            String student = "CREATE TABLE IF NOT EXISTS STUDENT "
                    + "(ID INT NOT NULL PRIMARY KEY, "
                    + " FirstName VARCHAR(20) NOT NULL, "
                    + " LastName VARCHAR(20) NOT NULL, "
                    + " DOB DATE NOT NULL)";

            String attendance = "CREATE TABLE IF NOT EXISTS ATTENDANCE"
                    + " (ID INT NOT NULL PRIMARY KEY, "
                    + " Semester INT, "
                    + " Week INT, "
                    + " Hours DECIMAL(2,2), "
                    + " StudentID INT, "
                    + " FOREIGN KEY(studentID) REFERENCES STUDENT(ID))";

            String badges = "CREATE TABLE IF NOT EXISTS BADGES "
                    + "(ID INT NOT NULL PRIMARY KEY, "
                    + " BadgeType VARCHAR(10), "
                    + " BadgeLevel VARCHAR(8)NOT NULL, "
                    + " StudentID INT, "
                    + " FOREIGN KEY(studentID) REFERENCES STUDENT(ID))";

            String tests_completed = "CREATE TABLE IF NOT EXISTS TESTS_COMPLETED"
                    + " (ID INT NOT NULL PRIMARY KEY, "
                    + " BadgesID INT, "
                    + " TestNumberCompleted INT, "
                    + " FOREIGN KEY(badgesID) REFERENCES BADGES(ID))";

            String tests_in_progress = "CREATE TABLE IF NOT EXISTS TESTS_IN_PROGRESS"
                    + " (ID INT NOT NULL PRIMARY KEY, "
                    + " TestNumberInProgress INT, "
                    + " StudentID INT, "
                    + " BadgesID INT, "
                    + " FOREIGN KEY(studentID) REFERENCES STUDENT(ID), "
                    + " FOREIGN KEY(badgesID) REFERENCES BADGES(ID))";

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
            stmt.executeUpdate(attendance);
            stmt.executeUpdate(tests_completed);
            stmt.executeUpdate(tests_in_progress);
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

            //use badge IDs 1-3
            //use tests_completed/tests_in_progress IDs 1-63 
            String student1 = "INSERT INTO STUDENT (ID, FirstName, LastName,"
                    + "DOB) "
                    + "VALUES (1, 'Brock', 'Mathison', '27/11/1971');"
                    + "INSERT INTO badges (ID, BadgeLevel, BadgeType,"
                    + "studentID)"
                    + "VALUES (1, 'Achievement', 'Diamond', 1),"
                    + "(2, 'Skill', 'Platinum', 1), (3, 'Core', 'Lithium', 1);"
                    + "INSERT INTO attendance (ID, Semester, Week, Hours, studentID)"
                    + "VALUES (1, 1, 13, 6.0, 1);"
                    + "INSERT INTO attendance (ID, Semester, Week, Hours, studentID)"
                    + "VALUES (2, 1, 12, 7.5, 1);"
                    + "INSERT INTO tests_completed (ID, BadgesID, TestNumberCompleted)"
                    + "VALUES (1, 1, 1),(2, 1, 2),(3, 1, 3),(4, 1, 4), "
                    + "(5, 1, 5),(6, 1, 6), (7, 1, 7),(8, 1, 8),(9, 1, 9), "
                    + "(10, 1, 10),(11, 1, 11),(12, 1, 12), (13, 1, 13), "
                    + "(14, 1, 14),(15, 1, 15),(16, 1, 16),(17, 1, 17);"
                    + "INSERT INTO tests_completed (ID, BadgesID, TestNumberCompleted)"
                    + "VALUES (18, 2, 1),(19, 2, 2),(20, 2, 3),(21, 2, 4), "
                    + "(22, 2, 5),(23, 2, 6), (24, 2, 7),(25, 2, 8),(26, 2, 9);"
                    + "INSERT INTO tests_completed (ID, BadgesID, TestNumberCompleted)"
                    + "VALUES (27, 3, 1),(28, 3, 2),(29, 3, 3),(30, 3, 4), "
                    + "(31, 3, 5),(32, 3, 6), (33, 3, 7);"
                    + "INSERT INTO tests_in_progress (ID, StudentID, BadgesID, "
                    + "TestNumberInProgress)"
                    + "VALUES (34, 1, 3, 10),(35, 1, 3, 11),(36, 1, 3, 12), "
                    + "(37, 1, 3, 13),(38, 1, 3, 14),(39, 1, 3, 15),(40, 1, 2, 10),"
                    + "(41, 1, 2, 11),(42, 1, 2, 12),(43, 1, 2, 13),(44, 1, 2, 14);";

            stmt.executeUpdate(student1);

            //use badge IDs 4-6
            //use tests_completed/tests_in_progress IDs 64-127
            String student2 = "INSERT INTO STUDENT (ID, FirstName, LastName, "
                    + "DOB) "
                    + "VALUES (2, 'Caden', 'Wilson', '01/02/2002');"
                    + "INSERT INTO badges (ID, BadgeLevel, BadgeType, "
                    + "studentID) "
                    + "VALUES (4, 'Skill', 'Platinum', 2);"
                    + "INSERT INTO attendance (ID, Semester, Week, Hours, studentID) "
                    + "VALUES (3, 1, 13, 6.0, 2);"
                    + "INSERT INTO attendance (ID, Semester, Week, Hours, studentID) "
                    + "VALUES (4, 1, 12, 7.5, 2);"
                    + "INSERT INTO tests_completed (ID, BadgesID, TestNumberCompleted) "
                    + "VALUES (64, 4, 1),(65, 4, 2),(66, 4, 3),(67, 4, 4), "
                    + "(68, 4, 5),(69, 4, 6), (70, 4, 7),(71, 4, 8),(72, 4, 9);";

            stmt.executeUpdate(student2);

            //use badge IDs 7-9
            //use tests_completed/tests_in_progress IDs 128-191
            String student3 = "INSERT INTO STUDENT (ID, FirstName, LastName, "
                    + "DOB) "
                    + "VALUES (3, 'ChitiPat', 'Marsri', '11/05/2003');"
                    + "INSERT INTO badges (ID, BadgeLevel, BadgeType, "
                    + "studentID) "
                    + "VALUES (7, 'Core', 'Lithium', 3);"
                    + "INSERT INTO attendance (ID, Semester, Week, Hours, studentID) "
                    + "VALUES (5, 1, 13, 6.0, 3);"
                    + "INSERT INTO attendance (ID, Semester, Week, Hours, studentID) "
                    + "VALUES (6, 1, 12, 7.5, 3);"
                    + "INSERT INTO tests_completed (ID, BadgesID, TestNumberCompleted)"
                    + "VALUES (128, 7, 1),(129, 7, 2),(130, 7, 3),(131, 7, 4), "
                    + "(132, 7, 5),(133, 7, 6), (134, 7, 7);"
                    + "INSERT INTO tests_in_progress (ID, StudentID, BadgesID, "
                    + "TestNumberInProgress)"
                    + "VALUES (135, 3, 7, 10),(136, 3, 7, 11),(137, 3, 7, 12), "
                    + "(138, 3, 7, 13),(139, 3, 7, 14),(140, 3, 7, 15),(141, 3, 7, 10),"
                    + "(142, 3, 7, 11),(143, 3, 7, 12),(144, 3, 7, 13),(145, 3, 7, 14);";

            stmt.executeUpdate(student3);

            //use badge IDs 10-12
            //use tests_completed/tests_in_progress IDs 191-254
            String student4 = "INSERT INTO STUDENT (ID, FirstName, LastName, "
                    + "DOB) "
                    + "VALUES (4, 'Steven', 'Meng', '21/05/2001');"
                    + "INSERT INTO badges (ID, BadgeLevel, BadgeType, "
                    + "studentID) "
                    + "VALUES (10, 'Achievement', 'Lithium', 4);"
                    + "INSERT INTO attendance (ID, Semester, Week, Hours, studentID) "
                    + "VALUES (7, 1, 13, 6.0, 4);"
                    + "INSERT INTO attendance (ID, Semester, Week, Hours, studentID) "
                    + "VALUES (8, 1, 12, 7.5, 4);"
                    + "INSERT INTO tests_completed (ID, BadgesID, TestNumberCompleted)"
                    + "VALUES (191, 10, 1),(192, 10, 2),(193, 10, 3),(194, 10, 4), "
                    + "(195, 10, 5),(196, 10, 6), (197, 10, 7);";

            stmt.executeUpdate(student4);
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
