package com.jxs.action;

import com.jxs.dao.AccountDao;
import com.jxs.model.AccountInfo;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangxs on 2018/3/21.
 */
public class AccountAction {

    @Test
    public void addAccountInfo(AccountInfo accountInfo) throws SQLException {

        AccountDao accountDao = new AccountDao();
        accountDao.addAccount(accountInfo);
    }

    @Test
    public void deleteAccountInfo(int id) throws SQLException {

        AccountDao accountDao = new AccountDao();
        accountDao.deleteAccount(id);
    }

    @Test
    public void updateAccountInfo(AccountInfo accountInfo) throws SQLException {

        AccountDao accountDao = new AccountDao();
        accountDao.updateAccount(accountInfo);
    }

    @Test
    public List<AccountInfo> queryAccountInfo(List<Map<String, Object>> parameters) throws SQLException {

        AccountDao accountDao = new AccountDao();
        return accountDao.queryAccount(parameters);
    }
}
