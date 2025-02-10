package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.HelloService;

@RestController
public class HelloController {
    private final HelloService _helloService;

    public HelloController(HelloService helloService) {
        this._helloService = helloService;
    }

    @GetMapping("/")
    public String sayGreeting() {
        return _helloService.sayHello();
    }
}