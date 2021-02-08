package com.pizzabaker.daos;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pizzabaker.entities.BasePizza;

public class BasePizzaDAO {

	
	//--------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------
	//----------------------------------------- PUBLIC METHODS -----------------------------------------
	//--------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------

	public List<BasePizza> selectBasePizzas() throws DBConnectionException {
		Connection connection = null;
		try {
			connection = DBConnection.GetConnection();
			CallableStatement callableStatement = connection.prepareCall("{ call fetch_pizzas() }");
			ResultSet rs = callableStatement.executeQuery();
			List<BasePizza> ret = new ArrayList<>();
			while(rs.next()) {
				long id = rs.getLong("id");
				int size = rs.getInt("size");
				double price = rs.getDouble("price");
				ret.add(new BasePizza(id, size, price));
			}
			rs.close();
			callableStatement.close();
			connection.close();
			return ret;
		} catch (SQLException e) {
			try {
				connection.close();
			} catch(Exception ex) {}
			throw new DBConnectionException("There was an error trying to select the base pizzas", e);
		}
	}
	
	public Map<Long, BasePizza> getMapBasePizzasById() throws DBConnectionException{
		Map<Long, BasePizza> ret = new HashMap<>();
		List<BasePizza> listBasePizzas = selectBasePizzas();
		for(BasePizza basePizza : listBasePizzas) {
			ret.put(basePizza.getId(), basePizza);
		}
		return ret;
	}
}
