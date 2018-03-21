package com.jxs.action;

import com.jxs.dao.TransDao;
import com.jxs.model.TransInfo;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangxs on 2018/3/21.
 */
public class TransAction {

    @Test
    public void addTransInfo(TransInfo transInfo) throws SQLException {

        TransDao transDao = new TransDao();
        transDao.addTransInfo(transInfo);
    }

    @Test
    public void deleteTransInfo(int id) throws SQLException {

        TransDao transDao = new TransDao();
        transDao.deleteTransinfo(id);
    }

    @Test
    public void updateTransinfo(TransInfo transInfo) throws SQLException {

        TransDao transDao = new TransDao();
        transDao.updatetransinfo(transInfo);
    }

    @Test
    public List<TransInfo> queryTransInfo(List<Map<String, Object>> parameters) throws SQLException {

        TransDao transDao = new TransDao();
        return transDao.queryTransInfo(parameters);
    }
}
