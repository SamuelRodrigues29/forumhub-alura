package br.com.alura.Forumhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

RestController
@RequestMapping("/respostas")
@SecurityRequirement(name = "bearer-key")
public class ResponseController {
    @Autowired
    private service.ResponseService service;
}
