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

    /**
     * 使用默认的自动提交，当程序出错时终止，之前的数据库操作不会回滚造成数据错误
     * @author jiangxingsong
     * @date 2018-3-22
     * @param from
     * @param to
     * @param amount
     * */
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

    /**
     * 关闭自动提交，当程序出错时数据库回滚，之前的数据库操作由于回滚操作而不会修改
     * @author jiangxingsong
     * @date 2018-3-22
     * @param from
     * @param to
     * @param amount
     * */
    public String transNotAuto(AccountInfo from, AccountInfo to, double amount) throws SQLException {

        Connection connection = DBUtil.getConnection();
        // 关闭自动提交
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

            // 手动提交
            connection.commit();
            return "Success";
        } catch (Exception e) {

            // 数据库回滚
            connection.rollback();
            e.printStackTrace();
            return "fail";
        }
    }
}
