import java.sql.*;

public class Main {
    static public String url = "jdbc:postgresql://localhost:5432/A3Database";
    static public String user = "postgres";
    static public String password = "#George12345";


    /*
        function: getAllStudents()
        parameters: None
        Retrives and prints all Students entities from the Students table
    */
    static void getAllStudents() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                System.out.print(resultSet.getInt("student_id") + " " + resultSet.getString("first_name") + " " + resultSet.getString("last_name") + " " + resultSet.getString("email") + " " + resultSet.getDate("enrollment_date") + "\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
    function: addStudent(String first_name, String last_name, String email, String enrollment_date)
    parameters:
        - first_name for new student(String)
        - last_name of new student (String)
        - email of new student (String)
        - enrollment_date of new student (String)
     Takes parameters of a new Student entity and Inserts it into the students relation
    */
    static void addStudent(String first_name, String last_name, String email, String enrollment_date) {

        String query = "INSERT INTO students(first_name, last_name, email, enrollment_date) " + "VALUES(?,?,?,?)";

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // Setters to fill in the preparedstatement missing values
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, email);
            statement.setDate(4, java.sql.Date.valueOf(enrollment_date));
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
    function: updateStudentEmail(int student_id, String new_email)
    parameters:
        - student_id of the student to update(Int)
        - new_email to replace the student entity's current email (String)
    Function updates the email address of the student with student_id, if it exists
    */
    static void updateStudentEmail(int student_id, String new_email) {

        String query = "UPDATE students SET email = ? WHERE student_id = ?";

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = connection.prepareStatement(query);

            // Setters to fill in the preparedstatement query's missing values
            statement.setString(1, new_email);
            statement.setInt(2, student_id);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
    function: deleteStudent(int student_id)
    parameters:
        - student_id of the student to delete from the Students relation (Int)
    Function deletes the Student entity with the passed student_id from the Students relation
    */
    static void deleteStudent(int student_id){

        String query = "DELETE FROM students WHERE student_id = ?";

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = connection.prepareStatement(query);

            // Setters to fill in the preparedstatement query's missing values
            statement.setInt(1,student_id);
            statement.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


    public static void main(String[] args) {
        getAllStudents();
        //addStudent("Keshav", "Narula", "keshavnarula@yahoo.com",   "2000-01-01");
        //updateStudentEmail(4, "newemail@yahoo.com");
        //deleteStudent(4);


    }

}
