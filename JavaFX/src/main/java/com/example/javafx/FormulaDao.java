package com.example.javafx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FormulaDao {

    private Connection connection;

    public FormulaDao() throws SQLException {
        this.connection = ConnectorDB.getConnection();
    }

    public void addFormula(Formula formula) throws SQLException {
        String query = "INSERT INTO formulas (name, expression) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, formula.getName());
            ps.setString(2, formula.getExpression());
            ps.executeUpdate();
        }
    }

    public void deleteFormula(int id) throws SQLException {
        String query = "DELETE FROM formulas WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Formula> getAllFormulas() throws SQLException {
        List<Formula> formulas = new ArrayList<>();
        String query = "SELECT * FROM formulas";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Formula formula = new Formula();
                formula.setId(rs.getInt("id"));
                formula.setName(rs.getString("name"));
                formula.setExpression(rs.getString("expression"));
                formulas.add(formula);
            }
        }
        return formulas;
    }
}

