package com.tinc.web.dao.jdbc.function;

public class mysqlFunction {
	public String insertId(String table, String val1) {
		return "SELECT COALESCE(MAX(" + val1 + "), 0)+1 FROM " + table + " t1";
	}

	public String insertId(String table, String val1, String val2, int whereId) {
		return "SELECT COALESCE(MAX(" + val1 + "), 0)+1 FROM " + table + " t1 WHERE " + val2 + "=" + whereId + ")";
	}
}
