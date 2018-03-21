package com.jxs.test;

import com.jxs.dao.AccountDao;
import com.jxs.model.AccountInfo;
import com.jxs.service.TransService;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by jiangxs on 2018/3/21.
 */
public class TestTrans {

    @Test
    public void testTransAuto() {

        try {
            System.out.println(transAuto());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("出错啦！");
        }
    }

    @Test
    public void testTransNotAuto() {

        try {
            System.out.println(transNotAuto());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("出错啦！");
        }
    }

    private String transAuto() throws SQLException {

        AccountDao accountDao = new AccountDao();
        TransService transService = new TransService();

        AccountInfo from = null;
        AccountInfo to = null;

        from = accountDao.getAccountinfo(1);
        to = accountDao.getAccountinfo(2);

        return transService.transAuto(from, to, 10.00);
    }

    private String transNotAuto() throws SQLException {

        AccountDao accountDao = new AccountDao();
        TransService transService = new TransService();

        AccountInfo from = null;
        AccountInfo to = null;

        from = accountDao.getAccountinfo(1);
        to = accountDao.getAccountinfo(2);

        return transService.transNotAuto(from, to, 10.00);
    }
}
