# SAD_ASS2
16OCT2022: Brock created README file.
Steps to Initialize ASS2.DB:
WINDOWS INSTRUCTIONS
1. Download ASS2.db & SQLiteJDBC.java useing the GREEN CODE button on the SAD_ASS2 main page
2. An sqlite3 folder will be included in this dowload
3. Create folder sqlite in C:\ drive of your PC
4. Import contents of sqlite folder into C:\sqlite
5. In NetBeans IDE - create project called ASS2 (must be Java Ant project)
6. Drag and Drop the dowloaded SQLiteJDBC_final.java into the Source Packages Folder for the project
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
19. Go to our Query doc in our Google Drive and copy this code to the Command Prompt: .mode column #creates table columns
20. Then right click and copy Query 1 code into the Command Prompt and hit enter
21. This populates our first table which shows Students who have achieved Badges
22. Repeat this process for Queries 2 to 12
23. Query 11 will show all tests completed, tests in progress, and topics and parts in progress for tests in progress for Student 1
24. These tables cannot be joined due to their different sizes and the need to display the sub-catergories for each test in progress
