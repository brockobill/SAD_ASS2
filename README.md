# SAD_ASS2
16OCT2022: Brock created README file.
Steps to Initialize ASS2.DB:
WINDOWS INSTRUCTIONS
1. Download ASS2_final.db
2. Download SQLiteJDBC_final.java
3. Create folder sqlite in C:\ drive of your PC
4. Import contents of sqlite folder into C:\sqlite
5. In Netbeans - create project called ASS2 (must be Java Ant project)
6. Drop SQLiteJDBC_final.java in Source Packages Folder
7. Right-click Libraries folder under the ASS2 Project
8. Click ADD JAR/Folder
9. Go to C:\sqlite and select sqlite-jdbc-3.39.3.0
10. Under the ASS2 project folder open Source Packages, then open <default package>
11. And open the SQLiteJDBC.java class
12. Open up Command Prompt on your desktop computer (Windows PC)
13. Type: cd\ #this will return you to the root directory
14. Type: cd sqlite #this will take you to the sqlite folder
15. Type: sqlite3 # this will start the sqlite3 program
16. Type: .open ASS2.db #this opens the database
17. Go back to NetBeans and right click in the body of SQLiteJDBC.java class and click option Run File
18. Go back to Command Prompty and type: .tables # this will show you thta the database tables have been created
  
  
