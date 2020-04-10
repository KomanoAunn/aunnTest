package com.example.demo.dao;

import com.example.demo.common.error.BusinessException;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseFileDao {
    private static final String allDbBasePackage = "db";
    private static final String schemaBasePath = "src" + File.separator + "main" + File.separator + "java" + File.separator + allDbBasePackage + File.separator;
    protected Logger logger = Logger.getLogger(BaseObjectDao.class.getName());
    private File file = null;

    protected File getFile() {
        if (file == null || !file.exists()) {
            throw new BusinessException("表文件未创建");
        }
        return file;
    }

    private File createAndGetFile(String tableName, String dbName) {
        String dbPackagePath = schemaBasePath + dbName + File.separator;
        if (!new File(dbPackagePath).exists()) {
            new File(dbPackagePath).mkdirs();
        }
        file = new File(dbPackagePath + tableName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
        return file;
    }

    protected void createTable(String tableName, String dbName) {
        createAndGetFile(tableName, dbName);
    }

    protected boolean deleteTable() {
        return getFile().delete();
    }
}
