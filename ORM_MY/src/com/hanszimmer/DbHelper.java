package com.hanszimmer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.hanszimmer.dao.Dao;
import com.hanszimmer.models.City;

/**
 * 数据库连接管理类
 * 
 * @author zhou
 * @date 2018年4月14日
 * @projectName ORM_MY
 * @version
 */
public class DbHelper {

	public static Connection getConnection() {
		try {
			String url = null;
			String user = null;
			String password = null;
			String className = null;
			Properties properties = new Properties();
			String file = "src/config.properties";
			InputStream inputStream = new FileInputStream(file);
			properties.load(inputStream);
			className = properties.getProperty("className");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			Class.forName(className);
			Connection connection = DriverManager.getConnection(url, user, password);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		
		try {
			City city=new City(6002,"test","CHN","test110",1280000L);
			
			Dao<City> dao=new Dao<City>();
			dao.add(city);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void Close(Connection con) {

		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

}
