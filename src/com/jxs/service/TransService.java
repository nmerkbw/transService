package com.jxs.service;

import com.jxs.dao.AccountDao;
import com.jxs.dao.TransDao;
import com.jxs.db.DBUtil;
import com.jxs.model.AccountInfo;
import com.jxs.model.TransInfo;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by jiangxs on 2018/3/19.
 */
public class TransService {

    public String transAuto(AccountInfo from, AccountInfo to, double amount) throws SQLException {

        AccountDao accountDao = new AccountDao();
        TransDao transDao = new TransDao();

        from.setAmount(from.getAmount() - amount);
        accountDao.updateAccount(from);

        String a = null;
        a.split("///");

        to.setAmount(to.getAmount() + amount);
        accountDao.updateAccount(to);

        TransInfo info = new TransInfo();
        info.setSourceId(from.getId());
        info.setSourceAccount(from.getAccount());
        info.setDestinationAccount(to.getAccount());
        info.setDestinationId(to.getId());
        info.setAmount(amount);
        info.setCreateTime(to.getCreateTime());
        transDao.addTransInfo(info);

        return "Success";
    }

    public String transNotAuto(AccountInfo from, AccountInfo to, double amount) throws SQLException {

        Connection connection = DBUtil.getConnection();
        connection.setAutoCommit(false);
        try {
            AccountDao accountDao = new AccountDao();
            TransDao transDao = new TransDao();

            from.setAmount(from.getAmount() - amount);
            accountDao.updateAccount(from);

            String a = null;
            a.split("///");

            to.setAmount(to.getAmount() + amount);
            accountDao.updateAccount(to);

            TransInfo info = new TransInfo();
            info.setSourceId(from.getId());
            info.setSourceAccount(from.getAccount());
            info.setDestinationAccount(to.getAccount());
            info.setDestinationId(to.getId());
            info.setAmount(amount);
            info.setCreateTime(to.getCreateTime());
            transDao.addTransInfo(info);

            connection.commit();
            return "Success";
        } catch (Exception e) {

            connection.rollback();
            e.printStackTrace();
            return "fail";
        }
    }
}
