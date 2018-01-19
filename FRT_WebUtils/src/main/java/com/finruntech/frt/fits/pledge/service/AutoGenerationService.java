package com.finruntech.frt.fits.pledge.service;

import com.finruntech.frt.fits.pledge.utils.AutoGenerationJavaCode;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yinan.zhang on 2018/1/15.
 */
@Service
public class AutoGenerationService {

    @Autowired
    private AutoGenerationJavaCode autoGenerationJavaCode;

    public boolean autoGenerationJavaCode(List<String> list) throws ClassNotFoundException, TemplateException, SQLException, IOException {
        for (String tableName : list) {
            autoGenerationJavaCode.setSqlAndTableName(tableName);
            autoGenerationJavaCode.autoGenerationJavaCode();
        }
        return true;
    }
}
