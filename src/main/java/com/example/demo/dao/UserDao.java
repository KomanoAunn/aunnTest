package com.example.demo.dao;

import com.example.demo.dao.base.BaseJsonDao;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao extends BaseJsonDao<User> {
    private static final String tableName = "user-json.text";
    private static final String dbName = "user";

    private volatile List<User> datas = null;

    {
        createTable(tableName, dbName);
    }

    public List<User> getList() {
        if (datas != null) {
            return datas;
        }
        datas = super.readObject();
        return datas;
    }

    public User insert(User user) {
        return super.save(user);
    }

    public User getById(String id) {
        return super.getById_(id);
    }

    @Override
    public boolean deleteTable() {
        return super.deleteTable();
    }

}
