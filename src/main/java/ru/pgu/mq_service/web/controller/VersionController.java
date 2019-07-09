package ru.pgu.mq_service.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class VersionController {

    @GetMapping("/ver")
    public String getVrsion(@Value("${ovm.version}") String version) {
        return version;
    }

}