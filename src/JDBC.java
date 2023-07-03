import java.sql.*;

public class JDBC {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb", "developer", "passwordhere");
            Statement statement = conn.createStatement();

            String createQuery = """
                    CREATE TABLE IF NOT EXISTS newdb.students
                    ( student_id INT(10) NOT NULL AUTO_INCREMENT,
                      last_name VARCHAR(30),
                      first_name VARCHAR(25),
                      CONSTRAINT students_pk PRIMARY KEY (student_id)
                    );
                    """;
            statement.executeUpdate(createQuery);
            System.out.println("Table 'students' created successfully.");

            String populateQuery = """
                    INSERT INTO newdb.students (last_name, first_name)
                    VALUES ('Walker', 'John'),('Brown', 'Lucy'),('Gardens', 'Pete'),('Sanders', 'Alice');
                    """;
            statement.executeUpdate(populateQuery);
            System.out.println("Data inserted successfully.");

            String selectQuery = "SELECT last_name, first_name FROM students";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            System.out.println("Inserted data:");
            while (resultSet.next()) {
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                System.out.println(lastName + " "+ firstName);
            }

        } catch (SQLException var13) {
            System.out.println("An error occurred: " + var13.getMessage());
        }
    }
}

