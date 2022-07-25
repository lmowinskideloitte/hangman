package org.example;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WordsDaoDbImpl implements WordsDao {

    private static WordsDaoDbImpl instance;

    public static WordsDaoDbImpl getInstance() {
        if (instance == null) {
            instance = new WordsDaoDbImpl();
        }
        return instance;
    }

    private WordsDaoDbImpl() {
    }

    @Override
    public List<String> getWords() {

        String selectSQL = "SELECT * FROM \"HangmanWords\"";

        try (Connection dbConnection = DbConnection.getDbConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL)) {

            ResultSet rs = preparedStatement.executeQuery();
            List<String> words = new ArrayList<>();

            while (rs.next()) {
                words.add(rs.getString("word"));
            }
            return words;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public boolean addWord(String word) {

        String selectSQL = "INSERT INTO \"HangmanWords\" (word) VALUES (?)";

        try (Connection dbConnection = DbConnection.getDbConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL)) {

            preparedStatement.setString(1, word);

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
