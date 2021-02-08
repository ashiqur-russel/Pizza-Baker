package com.pizzabaker.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientProperties;

import com.pizzabaker.entities.BasePizza;
import com.pizzabaker.entities.Supplier;

public class SupplierDAO {
	
	//--------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------
	//----------------------------------------- PUBLIC METHODS -----------------------------------------
	//--------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------

	public Supplier selectSupplierById(long id) throws DBConnectionException, NoDataException, CorruptedDBException {
		Connection connection = null;
		try {
			connection = DBConnection.GetConnection();
			CallableStatement callableStatement = connection.prepareCall("{ call fetch_supplier_by_id(?) }");
			callableStatement.setLong(1, id);
			ResultSet rs = callableStatement.executeQuery();
			if(!rs.next()) {
				rs.close();
				callableStatement.close();
				connection.close();
				throw new NoDataException("There is no supplier with id '"+id+"'", null);
			}
			String name = rs.getString("name");
			String ingredients = rs.getString("ingredients");
			boolean isHidden = rs.getBoolean("is_hidden");
			Supplier ret = new Supplier(id, name, ingredients, isHidden);
			rs.close();
			callableStatement.close();
			connection.close();
			return ret;
		} catch(SQLException e) {
			try {
				connection.close();
			} catch(Exception ex) {}
			throw new DBConnectionException("There was an error trying to get the supplier with the id '"+id+"'", e);
		}
	}
	
	public Map<Long, Supplier> getMapSuppliersById() throws DBConnectionException{
		Map<Long, Supplier> ret = new HashMap<>();
		List<Supplier> listSuppliers = selectSuppliers(true);
		for(Supplier supplier : listSuppliers) {
			ret.put(supplier.getId(), supplier);
		}
		return ret;
	}
	
	public List<Supplier> selectSuppliers(boolean includeHidden) throws DBConnectionException{
		Connection connection = null;
		try {
			connection = DBConnection.GetConnection();
			CallableStatement callableStatement = connection.prepareCall("{ call fetch_suppliers(false) }");
			ResultSet rs = callableStatement.executeQuery();
			List<Supplier> ret = new ArrayList<>();
			while(rs.next()) {
				long id = rs.getLong("id");
				String name = rs.getString("name");
				String ingredients = rs.getString("ingredients");
				boolean isHidden = rs.getBoolean("is_hidden");
				if(includeHidden || !isHidden) {
					ret.add(new Supplier(id, name, ingredients, isHidden));
				}
			}
			rs.close();
			callableStatement.close();
			connection.close();
			return ret;
		} catch(SQLException e) {
			try {
				connection.close();
			} catch(Exception ex) {}
			throw new DBConnectionException("There was an error trying to get the suppliers from the database", e);
		}
	}
	
	public void updateSupplier(Supplier supplier) throws DBConnectionException {
		Connection connection = null;
		try {
			connection = DBConnection.GetConnection();
			CallableStatement callableStatement = connection.prepareCall("{ call upd_supplier(?, ?, ?, ?) }");
			callableStatement.setLong(1, supplier.getId());
			callableStatement.setString(2, supplier.getName());
			callableStatement.setString(3, supplier.getIngredients());
			callableStatement.setBoolean(4, supplier.isHidden());
			callableStatement.execute();
			connection.close();
		} catch (SQLException e) {
			try {
				connection.close();
			} catch(Exception ex) {}
			throw new DBConnectionException("There was an error trying to update the supplier of id '"+supplier.getId()+"'", e);
		}
	}
	
	public void insertSupplier(Supplier supplier) throws DBConnectionException {
		Connection connection = null;
		try {
			connection = DBConnection.GetConnection();
			CallableStatement callableStatement = connection.prepareCall("{ ? = call ins_supplier(?, ?, ?) }");
			callableStatement.registerOutParameter(1, Types.BIGINT);
			callableStatement.setString(2, supplier.getName());
			callableStatement.setString(3, supplier.getIngredients());
			callableStatement.setBoolean(4, supplier.isHidden());
			callableStatement.execute();
			long id = callableStatement.getLong(1);
			connection.close();
			supplier.setId(id);
		} catch (SQLException e) {
			throw new DBConnectionException("There was an error trying to add a supplier to the database", e);
		}
	}
	
	public void deleteSupplier(long id) throws DBConnectionException {
		Connection connection = null;
		try {
			connection = DBConnection.GetConnection();
			CallableStatement callableStatement = connection.prepareCall("{ call del_supplier(?) }");
			callableStatement.setLong(1, id);
			callableStatement.execute();
			connection.close();
		} catch (SQLException e) {
			try {
				connection.close();
			} catch(Exception ex) {}
			throw new DBConnectionException("There was an error trying to delete the supplier of id '"+id+"'", e);
		}
	}

	//--------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------
	//---------------------------------------- INTERNAL METHODS ----------------------------------------
	//--------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------
	
	/*Map<Long, Supplier> getMapSuppliersById(Connection connection, String condition) throws SQLException{
		List<Supplier> listSuppliers = selectSuppliers(connection, condition);
		Map<Long, Supplier> ret = new HashMap<>();
		for(Supplier supplier : listSuppliers) {
			ret.put(supplier.getId(), supplier);
		}
		return ret;
	}*/
	
	/*List<Supplier> selectSuppliers(Connection connection, String condition) throws SQLException{
		String query = "SELECT * FROM supplier";
		if(condition != null && !condition.trim().isEmpty()) {
			query += " WHERE " + condition;
		}
		query += " ORDER BY id";
		List<Supplier> ret = new ArrayList<>();
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			long id = rs.getLong("id");
			String name = rs.getString("name");
			String ingredients = rs.getString("ingredients");
			boolean hidden = rs.getBoolean("is_hidden");
			Supplier supplier = new Supplier(id, name, ingredients, hidden);
			ret.add(supplier);
		}
		rs.close();
		ps.close();
		return ret;
	}*/
}
