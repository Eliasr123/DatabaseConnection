import include.Database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database database = null;
        try {
            database = new Database();
        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();
            System.exit(404);
        }

        int id = 1;
        while (true) {
            ArrayList<String[]> first = fetchStmt(database, "SELECT * FROM story WHERE id = " + id + " Limit 1;");
            System.out.println(first.get(0)[1]);
            if (id == 666) {
                System.exit(0);
            }

            Scanner myObj = new Scanner(System.in);
            ArrayList<String[]> storylinks = fetchStmt(database, "SELECT target, text FROM storylink WHERE storyid = " + first.get(0)[0] + "");
            for (int i = 0; i < storylinks.size(); i++) {
                System.out.println("---");
                System.out.println(i + 1 + " " + storylinks.get(i)[1]);
            }
            System.out.println("Ditt val");

            String choice = myObj.nextLine();
            if (choice.equalsIgnoreCase("q") || choice.equalsIgnoreCase("quit") || choice.contains("ex")) {
                System.exit(0);
            }
            String[] choices = storylinks.get(Integer.parseInt(choice) - 1);
            System.out.println("du valde: " + choices[1]);
            id = Integer.parseInt(choices[0]);

            System.out.println("-----------------------------");
        }
    }

    private static ArrayList<String[]> fetchStmt(Database database, String stmt) {
        try {
            Statement statement = database.getConnection().createStatement();
            ResultSet rset = statement.executeQuery(stmt);

            //System.out.println("The records selected are:");
            ArrayList<String[]> returnsArr = new ArrayList<>();
            while (rset.next()) {   // Move the cursor to the next row, return false if no more row

                ResultSetMetaData metadata = rset.getMetaData();
                int columnCount = metadata.getColumnCount();
                String[] strings = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    String name = metadata.getColumnName(i + 1);
                    strings[i] = rset.getString(name);
                }
                returnsArr.add(strings);
            }
            return returnsArr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
