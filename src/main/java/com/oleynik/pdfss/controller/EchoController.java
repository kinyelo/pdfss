package com.oleynik.pdfss.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EchoController {

    @GetMapping(value = "/echo", produces = "text/plain")
    public String echo(@RequestParam(value = "text", required = false) String text) {
        log.debug(text);
        return text;
    }

}
