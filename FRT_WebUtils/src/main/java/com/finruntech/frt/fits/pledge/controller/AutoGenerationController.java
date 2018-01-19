package com.finruntech.frt.fits.pledge.controller;

import com.finruntech.frt.fits.pledge.service.AutoGenerationService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yinan.zhang on 2018/1/15.
 */
@RestController
@CrossOrigin
public class AutoGenerationController {

    @Autowired
    private AutoGenerationService autoGenerationService;

    @RequestMapping(value = "autoGeneration", method = RequestMethod.POST)
    public ResponseEntity<?> autoGenerationJavaCode(@RequestBody List<String> list) throws ClassNotFoundException, TemplateException, SQLException, IOException {

        return ResponseEntity.ok().body(autoGenerationService.autoGenerationJavaCode(list));
    }

}
