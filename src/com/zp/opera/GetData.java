package com.zp.opera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import com.zp.mail.JavaMailSender;

public class GetData {

	Connection ora_conn = null;
	Statement ora_stmt = null;
	Statement ora_stmt2 = null;
	ResultSet ora_rs = null;
	ResultSet ora_rs2 = null;
	ResultSet in_rs = null;
	PreparedStatement pstm = null;

	public void GetErpData() {
		try {
			String sql = "SELECT * FROM ERPTOB2BDELIVERYORDER_EXP";
			String bill_no = null;
			String query_sql = "SELECT * FROM ERPTOB2BDELIVERYORDER where ERP_PLAN_NO = \'";

			ora_conn = OraConnect.createOracleConn();
			ora_stmt = ora_conn.createStatement();
			ora_rs = ora_stmt.executeQuery(sql);
			Date currentTime = new Date();

			while (ora_rs.next()) {
				bill_no = ora_rs.getString("CO_CODE") + "_TH_" + ora_rs.getString("BILL_NUMBER");
				System.out.println(bill_no);
				query_sql = query_sql.concat(bill_no).concat("'");
				System.out.println(query_sql);
				ora_stmt2 = ora_conn.createStatement();
				ora_rs2 = ora_stmt2.executeQuery(query_sql);
				int exist_count = 0;
				while (ora_rs2.next()) {
					exist_count += 1;
				}
				// 调用邮件推送程序
				JavaMailSender.qqSender("组织：" + ora_rs.getString("CO_CODE") + "\r\n" + "单号："
						+ ora_rs.getString("BILL_NUMBER") + "\r\n" + "当前E2B提货单视图该单号记录数：" + exist_count);
				// pstm = ora_conn.prepareStatement("insert into b2b_push_record
				// values(?,?,?,?,?,?,?,?,?)");
				pstm = ora_conn.prepareStatement("insert into b2b_push_record values(?,?,?,?,?,?,?,?,?)");
				pstm.setString(1, "TH_EXP");
				pstm.setString(2, bill_no);
				pstm.setTimestamp(3, new java.sql.Timestamp(currentTime.getTime()));
				pstm.setInt(4, 2);
				pstm.setString(5, null);
				pstm.setString(6, null);
				pstm.setString(7, null);
				pstm.setString(8, null);
				pstm.setString(9, null);
				pstm.executeQuery();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OraConnect.closeRs(ora_rs);
			OraConnect.closeStmt(ora_stmt);
			OraConnect.releaseConnection(ora_conn);
		}
	}

	public static void main(String[] args) {
		GetData start = new GetData();
		start.GetErpData();
	}
}