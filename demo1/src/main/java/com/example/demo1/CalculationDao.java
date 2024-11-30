package com.example.demo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalculationDao {

    private Connection connection;

    public CalculationDao() throws SQLException {
        this.connection = ConnectorDB.getConnection();
    }

    public void addCalculation(Calculation calculation) throws SQLException {
        String query = "INSERT INTO calculations (expression, result) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, calculation.getExpression());
            ps.setString(2, calculation.getResult());
            ps.executeUpdate();
        }
    }

    public List<Calculation> getAllCalculations() throws SQLException {
        List<Calculation> calculations = new ArrayList<>();
        String query = "SELECT * FROM calculations";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Calculation calculation = new Calculation();
                calculation.setId(rs.getInt("id"));
                calculation.setExpression(rs.getString("expression"));
                calculation.setResult(rs.getString("result"));
                calculation.setTimestamp(rs.getTimestamp("timestamp"));
                calculations.add(calculation);
            }
        }
        return calculations;
    }
}

