package com.tsvietkovich;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException {
        String login = "root";
        String pas = "tsvietkovich93";
        String localhost = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(localhost,login,pas);
            Statement statement = connection.createStatement()) {
            //statement.executeUpdate("INSERT INTO personse (FirstName, LastName, Telephone, City) VALUES ('Masha','Tsvietkovich','0635871156','Kiyv')");
            //statement.executeUpdate("INSERT INTO personse (FirstName, LastName, Telephone, City) VALUES ('Tanya','Tsvietkovich','0678909978','Kiyv')");
            //statement.executeUpdate("UPDATE personse set FirstName = 'Katya' WHERE PersonID=2");
            //String name = "Eugen";
            //String surname = "Belogurov";
            //PreparedStatement st = connection.prepareStatement("INSERT  INTO personse(FirstName, LastName) VALUES (?,?)");
            //st.setString(1,name);
            //st.setString(2,surname);
            //st.executeUpdate();
            //st.executeUpdate("DELETE FROM personse WHERE FirstName = 'Vitya' AND LastName = 'Losha'");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM personse");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("PersonID"));
                System.out.println(resultSet.getString("FirstName"));
                System.out.println(resultSet.getString("LastName"));
                System.out.println(resultSet.getString("Telephone"));
                System.out.println(resultSet.getString("City"));
            }
            //statement.executeUpdate("ALTER TABLE personse ADD Salary INT (20)");
            //statement.executeUpdate("ALTER TABLE personse ADD BirthDay DATE ");
            //statement.executeUpdate("UPDATE personse set BirthDay = '1993-12-01' WHERE PersonID=1");
            ResultSet result = statement.executeQuery("SELECT BirthDay FROM personse WHERE FirstName ='Masha'");
            if (result.next())
                System.out.println(result.getDate("BirthDay"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
