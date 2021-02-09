package webService;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myWebService" path)
 */
@Named(value = "myWebService")
@SessionScoped
@Path("/web")
public class MyWebService implements Serializable {

    private static final String URL = "jdbc:derby://localhost:1527/sample";
    private static final String USER = "app";
    private static final String PASSWD = "app";

    //MODULES TABLE
    private ArrayList<Integer> moduleIds = new ArrayList<Integer>();
    private int moduleId;

    private ArrayList<String> moduleNames = new ArrayList<String>();
    private String moduleName;

    private ArrayList<String> moduleLecs = new ArrayList<String>();
    private String moduleLec;

    private ArrayList<Integer> moduleIds2 = new ArrayList<Integer>();
    private int moduleId2;

    private ArrayList<String> moduleNames2 = new ArrayList<String>();
    private String moduleName2;

    private ArrayList<String> moduleLecs2 = new ArrayList<String>();
    private String moduleLec2;

    private ArrayList<String> moduleNames3 = new ArrayList<String>();
    private String moduleName3;

    //SCORES TABLE
    private ArrayList<Integer> scoreStudentIds = new ArrayList<Integer>();
    private int scoreStudentId;

    private ArrayList<Integer> scoreModuleIds = new ArrayList<Integer>();
    private int scoreModuleId;

    private ArrayList<Integer> scoreStudentIds2 = new ArrayList<Integer>();
    private int scoreStudentId2;

    private ArrayList<Integer> scoreModuleIds2 = new ArrayList<Integer>();
    private int scoreModuleId2;

    private ArrayList<Integer> scoreScores = new ArrayList<Integer>();
    private int scoreScore;

    @Context
    private UriInfo context;

    public MyWebService() {
    }

    public int getScoreScore() {
        return scoreScore;
    }

    public void setScoreScore(int scoreScore) {
        this.scoreScore = scoreScore;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public int getModuleId2() {
        return moduleId2;
    }

    public void setModuleId2(int moduleId2) {
        this.moduleId2 = moduleId2;
    }

    public String getModuleName2() {
        return moduleName2;
    }

    public void setModuleName2(String moduleName2) {
        this.moduleName2 = moduleName2;
    }

    public String getModuleLec2() {
        return moduleLec2;
    }

    public void setModuleLec2(String moduleLec2) {
        this.moduleLec2 = moduleLec2;
    }

    public int getScoreStudentId2() {
        return scoreStudentId2;
    }

    public void setScoreStudentId2(int scoreStudentId2) {
        this.scoreStudentId2 = scoreStudentId2;
    }

    public int getScoreModuleId2() {
        return scoreModuleId2;
    }

    public void setScoreModuleId2(int scoreModuleId2) {
        this.scoreModuleId2 = scoreModuleId2;
    }

    public String getModuleLec() {
        return moduleLec;
    }

    public void setModuleLec(String moduleLec) {
        this.moduleLec = moduleLec;
    }

    public int getScoreStudentId() {
        return scoreStudentId;
    }

    public void setScoreStudentId(int scoreStudentId) {
        this.scoreStudentId = scoreStudentId;
    }

    public int getScoreModuleId() {
        return scoreModuleId;
    }

    public void setScoreModuleId(int scoreModuleId) {
        this.scoreModuleId = scoreModuleId;
    }

    public String getModuleName3() {
        return moduleName3;
    }

    public void setModuleName3(String moduleName3) {
        this.moduleName3 = moduleName3;
    }

    /*
    createTables creates the tables in the database and adds initial values to them
    
    returns text data showing created tables.
     */
    @GET
    @Produces("text/plain")
    //public String createTheTables(@PathParam("username") String userName) {
    public String createTables(String a) {
        String data;
        String str;

        // use try with resource
        try (Connection connect = DriverManager.getConnection(URL, USER, PASSWD);
                Statement stmt = connect.createStatement();) {

            //Tables are deleted when running createTables.xhtml 
            if ("a".equals(a)) {
                stmt.executeUpdate("DROP TABLE SCORES ");
                stmt.executeUpdate("DROP TABLE MODULES ");
                stmt.executeUpdate("DROP TABLE STUDENTS ");
            }

            // execute statement - note DB needs to perform full processing
            // on calling executeQuery
            stmt.executeUpdate("CREATE TABLE STUDENTS"
                    + "(STUDENT_ID INTEGER PRIMARY KEY "
                    + "GENERATED ALWAYS AS IDENTITY"
                    + "(START WITH 1, INCREMENT BY 1), "
                    + "FNAME VARCHAR(255), "
                    + "LNAME VARCHAR(255))");

            stmt.executeUpdate("CREATE TABLE MODULES"
                    + "(MODULE_ID INTEGER PRIMARY KEY "
                    + "GENERATED ALWAYS AS IDENTITY"
                    + "(START WITH 4000, INCREMENT BY 1), "
                    + "MODULE_NAME VARCHAR(255) NOT NULL UNIQUE, "
                    + "LECTURER VARCHAR(255), "
                    + "EXAM_DATE DATE)");

            stmt.executeUpdate("CREATE TABLE SCORES"
                    + "(SCORE_ID INTEGER PRIMARY KEY "
                    + "GENERATED ALWAYS AS IDENTITY"
                    + "(START WITH 1000, INCREMENT BY 1), "
                    + "STUDENT_ID INTEGER, "
                    + "MODULE_ID INTEGER, "
                    + "SCORE REAL, "
                    + "FOREIGN KEY(STUDENT_ID) REFERENCES STUDENTS(STUDENT_ID),"
                    + "FOREIGN KEY(MODULE_ID) REFERENCES MODULES(MODULE_ID))");

            stmt.executeUpdate("INSERT INTO STUDENTS(FNAME, LNAME)"
                    + "VALUES('Rory', 'McMahon'), "
                    + "('Joe', 'Soap'), "
                    + "('John', 'Brown'), "
                    + "('Jim', 'Green'), "
                    + "('Jack', 'Quinn')");

            stmt.executeUpdate("INSERT INTO MODULES(MODULE_NAME, LECTURER, EXAM_DATE)"
                    + "VALUES"
                    + "('ASICS', 'I.Grout', '2020-05-11'), "
                    + "('OS', 'R.Dojen', '2020-05-15'), "
                    + "('ACD', 'M.Hayes', '2020-05-12'), "
                    + "('FYP', 'I.Grout', '2020-05-13'), "
                    + "('COMMS', 'T.Newe', '2020-05-14')");

            stmt.executeUpdate("INSERT INTO SCORES(STUDENT_ID, MODULE_ID, SCORE)"
                    + "VALUES"
                    + "(1, 4001, 77.5), "
                    + "(1, 4002, 67.5), "
                    + "(1, 4003, 57.6), "
                    + "(2, 4001, 47.7), "
                    + "(2, 4002, 51.8), "
                    + "(3, 4002, 91.5), "
                    + "(3, 4003, 71.0), "
                    + "(4, 4000, 61.9), "
                    + "(4, 4001, 41.3), "
                    + "(4, 4004, 54.2), "
                    + "(5, 4000, 74.8), "
                    + "(5, 4002, 94.8)");

            ResultSet result = stmt.executeQuery("SELECT STUDENT_ID,FNAME,LNAME FROM STUDENTS");

            data = "Welcome to the Table Creation & Display Section.\n\n"
                    + "3 Tables have been created. "
                    + "One for Students, one for modules, and one for students' exam scores.\n\n"
                    + "Scroll to the bottom of the page to proceed to the next step"
                    + "\n\n\nSTUDENTS Table\n\n"
                    + "STUDENT ID\tFNAME\t\tLNAME\n\n";

            // process results
            // while there are results
            while (result.next()) {
                // get data out - note: index starts at 1 !!!!
                data += "" + result.getInt(1);
                data += "\t\t" + result.getString(2);
                data += "\t\t" + result.getString(3);
                data += "\n";
            }

            ResultSet result1 = stmt.executeQuery("SELECT MODULE_ID,MODULE_NAME,LECTURER,EXAM_DATE FROM MODULES");

            data += "\n\nMODULES Table\n\n"
                    + "MODULE ID\tMODULE NAME\tLECTURER\tEXAM DATE\n\n";

            // process results
            // while there are results
            while (result1.next()) {
                // get data out - note: index starts at 1 !!!!
                data += "" + result1.getInt(1);
                data += "\t\t" + result1.getString(2);
                data += "\t\t" + result1.getString(3);
                data += "\t\t" + result1.getString(4);
                data += "\n";
            }

            ResultSet result2 = stmt.executeQuery("SELECT SCORE_ID,STUDENT_ID,MODULE_ID, SCORE FROM SCORES");

            data += "\n\nSCORES Table\n\n";
            // process results
            // while there are results
            data += "SCORE ID\tSTUDENT ID\tMODULE ID\tSCORE\n\n";
            while (result2.next()) {
                // get data out - note: index starts at 1 !!!!
                data += "" + result2.getInt(1);
                data += "\t\t" + result2.getInt(2);
                data += "\t\t" + result2.getInt(3);
                data += "\t\t" + result2.getString(4);
                data += "\n";
            }

            data += "\n\nCopy & Paste one of the following URI's to invoke a query:\n\n"
                    + "http://localhost:8080/WebServiceApp/webresources/web/Query1-1\n"
                    + "http://localhost:8080/WebServiceApp/webresources/web/Query1-M\n"
                    + "http://localhost:8080/WebServiceApp/webresources/web/QueryM-M\n\n\n\n\n";

            ResultSet mNameQuery = stmt.executeQuery("SELECT MODULE_NAME FROM MODULES ORDER BY MODULE_ID");

            while (mNameQuery.next()) {
                moduleName = mNameQuery.getString(1);
                moduleNames.add(moduleName);
            }

            ResultSet mIdQuery = stmt.executeQuery("SELECT MODULE_ID FROM MODULES");

            while (mIdQuery.next()) {
                moduleId = mIdQuery.getInt(1);
                moduleIds.add(moduleId);
            }

            ResultSet mLecQuery = stmt.executeQuery("SELECT LECTURER FROM MODULES ORDER BY MODULE_ID");

            while (mLecQuery.next()) {
                moduleLec = mLecQuery.getString(1);
                moduleLecs.add(moduleLec);
            }

            ResultSet scStudentQuery = stmt.executeQuery("SELECT STUDENT_ID FROM SCORES ORDER BY SCORE_ID");

            while (scStudentQuery.next()) {
                scoreStudentId = scStudentQuery.getInt(1);
                scoreStudentIds.add(scoreStudentId);
            }

            ResultSet scModuleQuery = stmt.executeQuery("SELECT MODULE_ID FROM SCORES ORDER BY SCORE_ID");

            while (scModuleQuery.next()) {
                scoreModuleId = scModuleQuery.getInt(1);
                scoreModuleIds.add(scoreModuleId);
            }

            // deal with any potential exceptions
            // note: all resources are closed automatically - no need for finally
        } catch (SQLException sql) {
            data = ("Message: " + sql.getMessage() + "\nCode: " + sql.getSQLState());
        }

        return data;
    }

    /*
    queryOneToOne Invokes one to one query.
    This method is used to invoke a query that selects module id’s and module names from the 
    MODULES table and displays them.

    returns text data showing query data.
     */
    @GET
    @Path("/Query1-1")
    @Produces("text/plain")
    public String queryOneToOne() {
        String data;

        try (Connection connect = DriverManager.getConnection(URL, USER, PASSWD);
                Statement stmt = connect.createStatement();) {
            data = "Query for the One-to-One Relationship.\n\n"
                    + "Module ID and Module Name is one-to-one, as no Module Name can have 2 Module ID's.\n\n\n";

            ResultSet result = stmt.executeQuery("SELECT MODULE_ID,MODULE_NAME FROM MODULES");

            // process results
            // while there are results
            data += "MODULE ID\tMODULE NAME\n\n";

            while (result.next()) {
                // get data out - note: index starts at 1 !!!!
                data += "" + result.getInt(1);
                data += "\t\t" + result.getString(2);
                data += "\n";
            }

            data += "\n\nCopy & Paste one of the following URI's to invoke another Query: "
                    + "\n\nhttp://localhost:8080/WebServiceApp/webresources/web/Query1-M"
                    + "\nhttp://localhost:8080/WebServiceApp/webresources/web/QueryM-M"
                    + "\n\n\nOr proceed to the Table Addition Section:\n"
                    + "\nhttp://localhost:8080/WebServiceApp/webresources/web/Add1-1\n\n";

        } catch (SQLException sql) {
            data = ("Message: " + sql.getMessage() + "\nCode: " + sql.getSQLState());
        }
        return data;

    }

    /*
    queryOneToMany Invokes one to many query.
    This method is used to invoke a query that selects module names and lecturers from the 
    MODULES table and displays them.
 
    returns text data showing query data.
     */
    @GET
    @Path("/Query1-M")
    @Produces("text/plain")
    public String queryOneToMany() {
        String data;

        try (Connection connect = DriverManager.getConnection(URL, USER, PASSWD);
                Statement stmt = connect.createStatement();) {
            data = "Query for the One-to-Many Relationship.\n\n"
                    + "One Lecturer can teach multiple modules\n\n\n";

            ResultSet result = stmt.executeQuery("SELECT MODULE_NAME,LECTURER FROM MODULES");

            // process results
            // while there are results
            data += "MODULE NAME\tLECTURER\n\n";

            while (result.next()) {
                // get data out - note: index starts at 1 !!!!
                data += "" + result.getString(1);
                data += "\t\t" + result.getString(2);
                data += "\n";
            }

            data += "\n\nCopy & Paste one of the following URI's to invoke another Query: "
                    + "\n\nhttp://localhost:8080/WebServiceApp/webresources/web/Query1-1"
                    + "\nhttp://localhost:8080/WebServiceApp/webresources/web/QueryM-M"
                    + "\n\n\nOr proceed to the Table Addition Section:\n"
                    + "\nhttp://localhost:8080/WebServiceApp/webresources/web/Add1-1\n\n";

        } catch (SQLException sql) {
            data = ("Message: " + sql.getMessage() + "\nCode: " + sql.getSQLState());
        }
        return data;

    }

    /*
    queryManyToMany Invokes many to many query.
    This method is used to invoke a query that selects module id’s and student id’s from the 
    SCORES table and displays them.

    returns text data showing query data.
     */
    @GET
    @Path("/QueryM-M")
    @Produces("text/plain")
    public String queryManyToMany() {
        String data;

        try (Connection connect = DriverManager.getConnection(URL, USER, PASSWD);
                Statement stmt = connect.createStatement();) {

            data = "Query for the Many-to-Many Relationship.\n\n"
                    + "Many modules are shared by many students. Many students can share many modules.\n\n\n";

            ResultSet result = stmt.executeQuery("SELECT MODULE_ID,STUDENT_ID FROM SCORES");

            // process results
            // while there are results
            data += "MODULE ID\tSTUDENT ID\n\n";

            while (result.next()) {
                // get data out - note: index starts at 1 !!!!
                data += "" + result.getInt(1);
                data += "\t\t" + result.getInt(2);
                data += "\n";
            }

            data += "\n\nCopy & Paste one of the following URI's to invoke another Query: "
                    + "\n\nhttp://localhost:8080/WebServiceApp/webresources/web/Query1-1"
                    + "\nhttp://localhost:8080/WebServiceApp/webresources/web/Query1-M"
                    + "\n\n\nOr proceed to the Table Addition Section:\n"
                    + "\nhttp://localhost:8080/WebServiceApp/webresources/web/Add1-1\n\n";

        } catch (SQLException sql) {
            data = ("Message: " + sql.getMessage() + "\nCode: " + sql.getSQLState());
        }
        return data;

    }

    /*
    addOneToOne Adds to one to one table.
    This method is used to invoke a query that adds a module ID and a module name to the 
    MODULES table based on the input from the user and displays the updated table.

    returns text data showing query data.
     */
    @GET
    @Path("/Add1-1")
    @Produces("text/plain")
    public String addOneToOne() {
        String data = "Part 1 of the Table Addition Section.\n\n\n\n"
                + "To add a value to a table with a one to one relationship, "
                + "Copy & Paste a URI in the following format:\n\n"
                + "http://localhost:8080/WebServiceApp/webresources/web/Add1-1/MODULE\n\n"
                + "where MODULE is to be replaced by the name of the module you want to add.\n\n\n\n"
                + "A Module ID will automatically be given to the module name you have entered.\n\n"
                + "Be sure not to enter a module name that has already been defined in the list below.\n\n\n\n"
                + "Here's a list of all current modules:\n\n";

        try (Connection connect = DriverManager.getConnection(URL, USER, PASSWD);
                Statement stmt = connect.createStatement();) {

            ResultSet result = stmt.executeQuery("SELECT MODULE_ID,MODULE_NAME FROM MODULES");

            // process results
            // while there are results
            data += "MODULE ID\tMODULE NAME\n\n";

            while (result.next()) {
                // get data out - note: index starts at 1 !!!!
                data += "" + result.getInt(1);
                data += "\t\t" + result.getString(2);
                data += "\n";
            }

        } catch (SQLException sql) {
            data = ("Message: " + sql.getMessage() + "\nCode: " + sql.getSQLState());
        }

        return data;
    }

    @GET
    @Path("/Add1-1/{module}")
    @Produces("text/plain")
    public String addRow(@PathParam("module") String mod) {

        String data;

        try (Connection connect = DriverManager.getConnection(URL, USER, PASSWD);
                Statement stmt = connect.createStatement();) {

            data = "You're Row has been added for One-to-One! See the updated table below.\n\n";

            stmt.executeUpdate("INSERT INTO MODULES(MODULE_NAME) VALUES('" + mod + "')");

            ResultSet result = stmt.executeQuery("SELECT MODULE_ID,MODULE_NAME FROM MODULES");

            // process results
            // while there are results
            data += "MODULE ID\tMODULE NAME\n\n";

            while (result.next()) {
                // get data out - note: index starts at 1 !!!!
                data += "" + result.getInt(1);
                data += "\t\t" + result.getString(2);
                data += "\n";
            }

            data += "\n\nCopy & Paste one of the following URI's to add to another table:\n"
                    + "\nhttp://localhost:8080/WebServiceApp/webresources/web/Add1-M\n"
                    + "http://localhost:8080/WebServiceApp/webresources/web/AddM-M\n";

            ResultSet mIdQuery = stmt.executeQuery("SELECT MODULE_ID FROM MODULES");

            while (mIdQuery.next()) {
                moduleId2 = mIdQuery.getInt(1);
                moduleIds2.add(moduleId2);
            }

            ResultSet mNameQuery = stmt.executeQuery("SELECT MODULE_NAME FROM MODULES ORDER BY MODULE_ID");

            while (mNameQuery.next()) {
                moduleName2 = mNameQuery.getString(1);
                moduleNames2.add(moduleName2);
            }

        } catch (SQLException sql) {
            data = ("Message: " + sql.getMessage() + "\nCode: " + sql.getSQLState());
        }

        return data;
    }

    /*
    addOneToMany Adds to one to many table.
    This method is used to invoke a query that adds a module ID, module name and a lecturer to the 
    MODULES table based on the input from the user. It then displays the updated table.

    returns text data showing query data.
     */
    @GET
    @Path("/Add1-M")
    @Produces("text/plain")
    public String addOneToMany() {
        String data = "Part 2 of the Table Addition Section.\n\n\n\n"
                + "To add a value to a table with a One-to-Many relationship, "
                + "Copy & Paste a URI in the following format:\n\n"
                + "http://localhost:8080/WebServiceApp/webresources/web/Add1-M/MODULE/LECTURER\n\n"
                + "where LECTURER is to be replaced by the name of the Lecturer you want to add,\n"
                + "and MODULE is replaced by the module you want to add (must be a new module).\n\n\n\n"
                + "To showcase the One-to-Many relationship, a lecturer can have many modules, but a module"
                + "can only have one lecturer.\n\n"
                + "Here's a list of all current lecturers and their modules:\n\n";

        try (Connection connect = DriverManager.getConnection(URL, USER, PASSWD);
                Statement stmt = connect.createStatement();) {

            ResultSet result = stmt.executeQuery("SELECT MODULE_NAME,LECTURER FROM MODULES");

            // process results
            // while there are results
            data += "MODULE NAME\tLECTURER\n\n";

            while (result.next()) {
                // get data out - note: index starts at 1 !!!!
                data += "" + result.getString(1);
                data += "\t\t" + result.getString(2);
                data += "\n";
            }

        } catch (SQLException sql) {
            data = ("Message: " + sql.getMessage() + "\nCode: " + sql.getSQLState());
        }

        return data;
    }

    @GET
    @Path("/Add1-M/{module}/{lecturer}")
    @Produces("text/plain")
    public String addRow1(@PathParam("module") String mod, @PathParam("lecturer") String lec) {

        String data;

        try (Connection connect = DriverManager.getConnection(URL, USER, PASSWD);
                Statement stmt = connect.createStatement();) {

            stmt.executeUpdate("INSERT INTO MODULES(MODULE_NAME, LECTURER) VALUES('" + mod + "', '" + lec + "' )");

            data = "You're Row has been added for One-to-Many! See an updated table below.\n\n";

            ResultSet result = stmt.executeQuery("SELECT MODULE_NAME, LECTURER FROM MODULES");

            // process results
            // while there are results
            data += "MODULE NAME\tLECTURER\n\n";

            while (result.next()) {
                // get data out - note: index starts at 1 !!!!
                data += "" + result.getString(1);
                data += "\t\t" + result.getString(2);
                data += "\n";
            }

            data += "\n\nCopy & Paste one of the following URI's to add to another table:\n"
                    + "\nhttp://localhost:8080/WebServiceApp/webresources/web/Add1-1\n"
                    + "http://localhost:8080/WebServiceApp/webresources/web/AddM-M\n";

            ResultSet mNameQuery = stmt.executeQuery("SELECT MODULE_NAME FROM MODULES ORDER BY MODULE_ID");

            while (mNameQuery.next()) {
                moduleName3 = mNameQuery.getString(1);
                moduleNames3.add(moduleName3);
            }

            ResultSet mLecQuery = stmt.executeQuery("SELECT LECTURER FROM MODULES ORDER BY MODULE_ID");

            while (mLecQuery.next()) {
                moduleLec2 = mLecQuery.getString(1);
                moduleLecs2.add(moduleLec2);
            }

        } catch (SQLException sql) {
            data = ("Message: " + sql.getMessage() + "\nCode: " + sql.getSQLState());
        }
        return data;
    }

    /*
    addManyToMany Adds to many to many table. 
    This method is used to invoke a query that adds a Score ID and Score to the 
    SCORES table based on the input from the user and displays the updated table.

    returns text data showing query data.
     */
    @GET
    @Path("/AddM-M")
    @Produces("text/plain")
    public String addManyToMany() {
        String data = "Part 3 of the Table Addition Section for Many-to-Many.\n\n\n\n"
                + "To add a value to a table with a Many-to-Many relationship, "
                + "Copy & Paste a URI in the following format:\n\n"
                + "http://localhost:8080/WebServiceApp/webresources/web/AddM-M/StudentID/ModuleID/Score\n\n"
                + "where StudentID is to be replaced by a Student ID from the list of students below, "
                + "ModuleID is replaced by the Module ID from the list of modules below,\n"
                + "and Score is replaced by the score you want to give them.\n\n\n\n"
                + "To showcase the Many-to-Many relationship, a student can have many modules, and a module "
                + "can have many students.\n\n"
                + "Here's a list of all current Student ID's and all current Module ID's:\n\n";

        try (Connection connect = DriverManager.getConnection(URL, USER, PASSWD);
                Statement stmt = connect.createStatement();) {

            ResultSet result = stmt.executeQuery("SELECT STUDENT_ID FROM STUDENTS");

            // process results
            // while there are results
            data += "\nLIST OF STUDENTS\n\n";

            while (result.next()) {
                // get data out - note: index starts at 1 !!!!
                data += "" + result.getInt(1);
                data += "\n";
            }

            ResultSet result1 = stmt.executeQuery("SELECT MODULE_ID FROM MODULES");

            data += "\nLIST OF MODULES\n\n";

            while (result1.next()) {
                // get data out - note: index starts at 1 !!!!
                data += "" + result1.getInt(1);
                data += "\n";
            }

        } catch (SQLException sql) {
            data = ("Message: " + sql.getMessage() + "\nCode: " + sql.getSQLState());
        }

        return data;
    }

    @GET
    @Path("/AddM-M/{student}/{module}/{score}")
    @Produces("text/plain")
    public String addRow2(@PathParam("student") int stu, @PathParam("module") int mod,
            @PathParam("score") double sco) {

        String data;

        try (Connection connect = DriverManager.getConnection(URL, USER, PASSWD);
                Statement stmt = connect.createStatement();) {

            stmt.executeUpdate("INSERT INTO SCORES(STUDENT_ID, MODULE_ID, SCORE) VALUES(" + stu + "," + mod + "," + sco + ")");

            data = "You're Row has been added for Many-to-Many! See an updated table below.\n\n";

            ResultSet result = stmt.executeQuery("SELECT STUDENT_ID, MODULE_ID, SCORE FROM SCORES");

            // process results
            // while there are results
            data += "STUDENT_ID\tMODULE_ID\tSCORE\n\n";

            while (result.next()) {
                // get data out - note: index starts at 1 !!!!
                data += "" + result.getInt(1);
                data += "\t\t" + result.getInt(2);
                data += "\t\t" + result.getString(3);
                data += "\n";
            }

            data += "\n\nCopy & Paste one of the following URI's to add to another table:\n"
                    + "\nhttp://localhost:8080/WebServiceApp/webresources/web/Add1-1\n"
                    + "http://localhost:8080/WebServiceApp/webresources/web/Add1-M\n";

            ResultSet scStuQuery = stmt.executeQuery("SELECT STUDENT_ID FROM SCORES ORDER BY SCORE_ID");

            while (scStuQuery.next()) {
                scoreStudentId2 = scStuQuery.getInt(1);
                scoreStudentIds2.add(scoreStudentId2);
            }

            ResultSet scModQuery = stmt.executeQuery("SELECT MODULE_ID FROM SCORES ORDER BY SCORE_ID");

            while (scModQuery.next()) {
                scoreModuleId2 = scModQuery.getInt(1);
                scoreModuleIds2.add(scoreModuleId2);
            }

            ResultSet scScoreQuery = stmt.executeQuery("SELECT SCORE FROM SCORES ORDER BY SCORE_ID");

            while (scScoreQuery.next()) {
                scoreScore = scScoreQuery.getInt(1);
                scoreScores.add(scoreScore);
            }

        } catch (SQLException sql) {
            data = ("Message: " + sql.getMessage() + "\nCode: " + sql.getSQLState());
        }
        return data;
    }

    public List<String> getModuleNames() {
        return this.moduleNames;
    }

    public List<Integer> getModuleIds() {
        return this.moduleIds;
    }

    public List<String> getModuleLecs() {
        return this.moduleLecs;
    }

    public List<Integer> getScoreStudentIds() {
        return this.scoreStudentIds;
    }

    public List<Integer> getScoreModuleIds() {
        return this.scoreModuleIds;
    }

    public List<String> getModuleNames2() {
        return this.moduleNames2;
    }

    public List<String> getModuleNames3() {
        return this.moduleNames3;
    }

    public List<Integer> getModuleIds2() {
        return this.moduleIds2;
    }

    public List<String> getModuleLecs2() {
        return this.moduleLecs2;
    }

    public List<Integer> getScoreStudentIds2() {
        return this.scoreStudentIds2;
    }

    public List<Integer> getScoreModuleIds2() {
        return this.scoreModuleIds2;
    }

    public List<Integer> getScoreScores() {
        return this.scoreScores;
    }

}
