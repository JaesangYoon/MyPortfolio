package com.myportfolio.web.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class A1DaoTest {
    @Autowired
    A1Dao a1Dao;
    @Autowired
    DataSource ds;

    @Autowired
    DataSourceTransactionManager tm;
    @Test
    public void insertTest() throws Exception {
//        PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
//        빈 주입(DataSourceTransactionManager)해서 생략 가능. insert 두 번 해도 같은 connection을 사용하도록 해준다
        TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition()); // Tx 속성이 Default로 셋팅

        try {
            deleteAll();
            a1Dao.insert(3, 7);
            a1Dao.insert(4, 4);
            tm.commit(status);
        } catch (Exception e) {
            e.printStackTrace();
            tm.rollback(status);
        } finally {

        }

    }

    private void deleteAll() throws Exception {
        Connection conn = ds.getConnection();

        String sql = "DELETE FROM a1 ";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection공격, 성능향상
        pstmt.executeUpdate(); //  insert, delete, update
    }
}