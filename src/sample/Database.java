package sample;

import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String DB_NAME = "numbers.db";
    private static Connection conn = null;

    public static void connect() {
        if (conn == null) {
            String url = "jdbc:sqlite:" + DB_NAME;
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void insert(Number number) {

        String sql = "INSERT INTO numbers(number) VALUES (?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            byte[] serialized = serialize(number);
            ByteArrayInputStream bais = new ByteArrayInputStream(serialized);
            ps.setBinaryStream(1, bais, serialized.length);
            ps.executeUpdate();
            bais.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] serialize(Number number) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(number);
            bos.close();
            out.close();
            return bos.toByteArray();
        }
    }

    public static void select() {
        String query = "SELECT number FROM numbers";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            List<Number> numbers = new ArrayList<>();

            while (rs.next()) {
                numbers.add(deserialize(rs.getBytes("number")));
            }
            Controller.numbers = numbers;
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Number deserialize(byte[] data) {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is;
        try {
            is = new ObjectInputStream(in);
            Number map = (Number) is.readObject();
            in.close();
            is.close();
            return map;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
