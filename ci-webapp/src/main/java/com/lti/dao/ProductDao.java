package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lti.dao.util.ConnManager;
import com.lti.entity.Product;

public class ProductDao {
//classes which contain DB code are commonly
//referred to as Data Access Objects
	public Product select(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//Step 1. Loading the JDBC Driver
			conn=ConnManager.connect();
			
			//can you tell me what should the select query look like?
			String sql = "SELECT * FROM tbl_products WHERE id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
					return product;
			}
			return null;
		}
		catch(SQLException e) {
			e.printStackTrace(); //we should rather throw an user defined exception
			return null;
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
	}
	public List<Product> selectAll(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//Step 1. Loading the JDBC Driver
			conn=ConnManager.connect();
			
			//can you tell me what should the select query look like?
			String sql = "SELECT * FROM tbl_products";
			stmt = conn.prepareStatement(sql);
			//stmt.setInt(1, id);
			rs = stmt.executeQuery();
			List<Product> list = new ArrayList<Product>();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				list.add(product);
			}
			return list;
		}
		catch(SQLException e) {
			e.printStackTrace(); //we should rather throw an user defined exception
			return null;
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
	}
	public void insert(Product product) {
		Connection conn=null;
		PreparedStatement stmt = null;
		try {
			//Step 1. Loading the JDBC Driver
			conn=ConnManager.connect();			
			String sql = "insert into tbl_products values(?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, product.getId());
			stmt.setString(2, product.getName());
			stmt.setDouble(3, product.getPrice());
			int count = stmt.executeUpdate(sql);
		}
		catch(SQLException e) {
			e.printStackTrace(); //we should rather throw an user defined exception
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
	}
}
