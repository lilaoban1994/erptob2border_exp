package com.zp.opera;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OraConnect {
	public  static String oracleDRIVER = "oracle.jdbc.driver.OracleDriver";
	
	public  static  String ORAURL = "jdbc:oracle:thin:@192.168.190.128:1521:PROD1";
	public  static  String ORAUSER = "apps";
	public  static  String ORAPWD = "xxb401";
	
	/**
	  * 创建oracle(apps)连接
	  * @return
	  */
	 public static Connection createOracleConn(){  
		  try {
			  Class.forName(oracleDRIVER);
			  Connection oracleConn = DriverManager.getConnection(ORAURL, ORAUSER, ORAPWD);
			  return oracleConn;
		  } catch (ClassNotFoundException e) {
			  e.printStackTrace();
			  return null;
		  } catch (SQLException e) {
			  e.printStackTrace();
			  return null;
		  }
		  
	 }
	 
	 /**
	  * 释放连接
	  */
	 public static void releaseConnection(Connection conn){  
		  if (conn!=null){
			   try {
				   conn.close();
			   } catch (SQLException e) {
				   e.printStackTrace();
			   }
		  }
	 }
	 
	 /**
	  * 关闭PreparedStatement
	  */
	 public static void closePstm(PreparedStatement pstm){  
		  if (pstm!=null){
			   try {
				   pstm.close();
			   } catch (SQLException e) {
				   e.printStackTrace();
			   }
		  }
	 }
	 /**
	  * 关闭Statement
	  */
	 public static void closeStmt(Statement stmt){  
		  if (stmt!=null){
			   try {
				   stmt.close();
			   } catch (SQLException e) {
				   e.printStackTrace();
			   }
		  }
	 }
	 /**
	  * 关闭ResultSet
	  */
	 public static void closeRs(ResultSet rs){  
		  if (rs!=null){
			   try {
				   rs.close();
			   } catch (SQLException e) {
				   e.printStackTrace();
			   }
		  }
	 }

}

