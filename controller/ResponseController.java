package controller;


RestController
@RequestMapping("/respostas")
@SecurityRequirement(name = "bearer-key")
public class ResponseController {
    @Autowired
    private RespostaService service;
}
