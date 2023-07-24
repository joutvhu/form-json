package com.joutvhu.spring.formjson.controller;

import com.joutvhu.spring.formjson.FormJson;
import com.joutvhu.spring.formjson.model.DemoModel;
import com.joutvhu.spring.formjson.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private DemoService service;

    @PostMapping
    public ResponseEntity<Boolean> create(@FormJson DemoModel model) {
        return ResponseEntity.ok(service.submit(model));
    }
}
