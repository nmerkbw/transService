package com.jxs.dao;

import com.jxs.db.DBUtil;
import com.jxs.model.AccountInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangxs on 2018/3/19.
 */
public class AccountDao {

    /**
     * dao层，对数据库进行插入操作
     * */
    public void addAccount(AccountInfo accountInfo) throws SQLException {

        Connection connection = DBUtil.getConnection();
        String sql = "" +
                "insert into account_info" +
                "(account,amount,create_at)" +
                "values(" +
                "?,?,?)";
        PreparedStatement ps = connection.prepareCall(sql);

        ps.setString(1, accountInfo.getAccount());
        ps.setDouble(2, accountInfo.getAmount());
        ps.setDate(3, new Date(accountInfo.getCreateTime().getTime()));

        ps.execute();
    }

    /**
     * dao层，对数据库进行删除操作
     * */
    public void deleteAccount(int id) throws SQLException {

        Connection connection = DBUtil.getConnection();
        String sql = "" +
                " delete from account_info" +
                " where id=?";
        PreparedStatement ps = connection.prepareCall(sql);
        ps.setInt(1, id);
        ps.execute();
    }

    /**
     * dao层，对数据库进行更新操作
     * */
    public void updateAccount(AccountInfo accountInfo) throws SQLException {

        Connection connection = DBUtil.getConnection();
        String sql = "" +
                " update account_info" +
                " set account=?, amount=?, create_at=?" +
                " where id=?";
        PreparedStatement ps = connection.prepareCall(sql);

        ps.setString(1,accountInfo.getAccount());
        ps.setDouble(2, accountInfo.getAmount());
        ps.setDate(3, new Date(accountInfo.getCreateTime().getTime()));
        ps.setInt(4,accountInfo.getId());

        ps.execute();
    }

    /**
     * dao层，对数据库进行查询操作
     * */
    public List<AccountInfo> queryAccount(List<Map<String, Object>> parameters) throws SQLException {

        Connection connection = DBUtil.getConnection();
        List<AccountInfo> result = new ArrayList<>();
        AccountInfo accountInfo;
        StringBuilder sql = new StringBuilder();
        sql.append("select * from account_info where 1=1");
        if (parameters != null && parameters.size() > 0) {
            for (Map<String, Object> parameter : parameters) {
                sql.append("and" + parameter.get("name") + parameter.get("relation") + parameter.get("value"));
            }
        }
        PreparedStatement ps = connection.prepareCall(sql.toString());
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            accountInfo = new AccountInfo();
            accountInfo.setAccount(resultSet.getString("account"));
            accountInfo.setAmount(resultSet.getDouble("amount"));
            accountInfo.setCreateTime(resultSet.getDate("create_at"));
            result.add(accountInfo);
        }
        return result;
    }

    /**
     * dao层，对数据库进行查询单个数据操作
     * */
    public AccountInfo getAccountinfo(int id) throws SQLException {

        Connection connection = DBUtil.getConnection();
        AccountInfo accountInfo = null;
        String sql = "" +
                " select * from account_info" +
                " where id=?";
        PreparedStatement ps = connection.prepareCall(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            accountInfo = new AccountInfo();
            accountInfo.setId(resultSet.getInt("id"));
            accountInfo.setAccount(resultSet.getString("account"));
            accountInfo.setAmount(resultSet.getDouble("amount"));
            accountInfo.setCreateTime(resultSet.getDate("create_at"));
        }
        return accountInfo;
    }
}
