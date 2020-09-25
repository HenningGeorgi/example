package org.example.repository;

import org.example.model.Greeting;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class GreetingRowMapper implements RowMapper<Greeting> {

    public Greeting mapRow(ResultSet rs, int rowNum) throws SQLException {
        Greeting greeting = new Greeting(
                rs.getObject("id", UUID.class),
                rs.getString("name"),
                rs.getBoolean("vegan"),
                rs.getInt("age"));

        return greeting;
    }
}
