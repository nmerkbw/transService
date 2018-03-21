package com.jxs.dao;

import com.jxs.db.DBUtil;
import com.jxs.model.TransInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangxs on 2018/3/21.
 */
public class TransDao {

    public void addTransInfo(TransInfo transInfo) throws SQLException {

        Connection connection = DBUtil.getConnection();
        String sql = "" +
                " insert into trans_info(" +
                " source_id,source_account,destination_id,destination_account,amount,create_at)" +
                " values(" +
                " ?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareCall(sql);
        ps.setInt(1, transInfo.getSourceId());
        ps.setString(2, transInfo.getSourceAccount());
        ps.setInt(3, transInfo.getDestinationId());
        ps.setString(4,transInfo.getDestinationAccount());
        ps.setDouble(5, transInfo.getAmount());
        ps.setDate(6, new Date(transInfo.getCreateTime().getTime()));
        ps.execute();
    }

    public void deleteTransinfo(int id) throws SQLException {

        Connection connection = DBUtil.getConnection();
        String sql = "" +
                " delete from trans_info" +
                " where id=?";
        PreparedStatement ps = connection.prepareCall(sql);
        ps.setInt(1, id);
        ps.execute();
    }

    public void updatetransinfo(TransInfo transInfo) throws SQLException {

        Connection connection = DBUtil.getConnection();
        String sql = "" +
                " update trans_info" +
                " set source_id=?,source_account=?,destination_id=?," +
                " destination_account,amount=?,create_at=?" +
                " where id=?";
        PreparedStatement ps = connection.prepareCall(sql);
        ps.setInt(1, transInfo.getSourceId());
        ps.setDouble(2, transInfo.getAmount());
        ps.setInt(3, transInfo.getDestinationId());
        ps.setString(4, transInfo.getSourceAccount());
        ps.setDouble(5, transInfo.getAmount());
        ps.setDate(6, new Date(transInfo.getCreateTime().getTime()));
        ps.execute();
    }

    public List<TransInfo> queryTransInfo(List<Map<String, Object>> parameters) throws SQLException {

        Connection connection = DBUtil.getConnection();
        StringBuilder sql = new StringBuilder();
        List<TransInfo> result = new ArrayList<>();
        Map<String, Object> parameter = new HashMap<>();
        TransInfo transInfo;

        sql.append("select * from trans_info where 1=1");
        sql.append(" and " + parameter.get("name") + parameter.get("relation") + parameter.get("value"));
        PreparedStatement ps = connection.prepareCall(sql.toString());
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            transInfo = new TransInfo();
            transInfo.setSourceId(resultSet.getInt("source_id"));
            transInfo.setSourceAccount(resultSet.getString("source_account"));
            transInfo.setDestinationId(resultSet.getInt("destination_id"));
            transInfo.setDestinationAccount(resultSet.getString("destination_account"));
            transInfo.setAmount(resultSet.getDouble("amount"));
            transInfo.setCreateTime(resultSet.getDate("create_at"));
            result.add(transInfo);
        }
        return result;
    }
}
