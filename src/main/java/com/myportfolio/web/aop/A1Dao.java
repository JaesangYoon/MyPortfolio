package com.myportfolio.web.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class A1Dao {

    @Autowired
    DataSource ds;

    public int insert(int key, int value) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
//            conn = ds.getConnection();
            conn = DataSourceUtils.getConnection(ds);  // 1. Tx를 위해 connection 가져오는 부분 수정
            pstmt = conn.prepareStatement("INSERT INTO a1 VALUES(?,?)");
            pstmt.setInt(1, key);
            pstmt.setInt(2, value);
            System.out.println("conn = " + conn);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;

        } finally {
//            close(conn, pstmt); // conn, pstmt 모두 close하는 이전방식
            close(pstmt);
            DataSourceUtils.releaseConnection(conn, ds); // 2. Tx를 위해 이제 connection을 무조건 close 하는 것이 아니라, TransactionManager가 close 해야하는 지 아닌지 판단한 뒤에 close 한다.
        }
    }
    private void close(AutoCloseable... acs) {
        for(AutoCloseable ac :acs)
            try { if(ac!=null) ac.close(); } catch(Exception e) { e.printStackTrace(); }
    }
}
