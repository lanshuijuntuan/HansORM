package com.hanszimmer.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hanszimmer.DbHelper;
import com.hanszimmer.annotation.Column;
import com.hanszimmer.annotation.Table;

public class Dao<E> {

	public int add(E model) {
		if (model == null) {
			return 0;
		}
		Integer result = 0;
		Class clazz = model.getClass();
		String tablename = getTableName(clazz);
		HashMap<String, Object> map = getParamsList(model);
		String insertSql = getInsertSql(tablename, map);
		result = executeUpdate(insertSql, map.values().toArray(new Object[map.size()]));
		return result;

	}

	private Integer executeUpdate(String insertSql, Object... params) {
		Integer result = 0;
		Connection connection = DbHelper.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
			for (Integer n = 0; n < params.length; n++) {
				Object param = params[n];
				preparedStatement.setObject(n+1, param);
			}
			// TODO Auto-generated method stub
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
	private ResultSet executeQuery(String querySql,Object... params) {
		ResultSet resultSet=null;
		Connection connection=DbHelper.getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(querySql);
			for (Integer n = 0; n < params.length; n++) {
				Object param = params[n];
				preparedStatement.setObject(n+1, param);
			}
			resultSet=preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	private String getInsertSql(String tablename, HashMap<String, Object> map) {
		String insertSql = "insert into " + tablename + " (";
		String insertValuesSql = ") values (";
		String[] columns = map.keySet().toArray(new String[map.size()]);
		for (Integer n = 0; n < columns.length; n++) {
			insertSql = insertSql + columns[n] + ",";
			insertValuesSql = insertValuesSql + "?,";
		}
		insertSql = insertSql.substring(0, insertSql.length() - 1);
		insertValuesSql = insertValuesSql.substring(0, insertValuesSql.length() - 1);
		insertSql = insertSql + insertValuesSql;
		insertSql = insertSql + ")";
		return insertSql;
	}

	@SuppressWarnings("rawtypes")
	private HashMap<String, Object> getParamsList(E model) {
		Class clazz = model.getClass();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Field[] fields = clazz.getDeclaredFields();
		for (Integer n = 0; n < fields.length; n++) {
			Field field = fields[n];
			field.setAccessible(true);
			Column column = field.getAnnotation(Column.class);
			if (column == null) {
				continue;
			}
			try {
				Object fieldValue = field.get(model);
				hashMap.put(column.name(), fieldValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hashMap;
	}

	private String getTableName(Class clazz) {
		Table table = (Table) clazz.getAnnotation(Table.class);
		return table.name();
	}
}
