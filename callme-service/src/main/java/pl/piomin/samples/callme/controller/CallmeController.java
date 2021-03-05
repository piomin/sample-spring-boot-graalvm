package pl.piomin.samples.callme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piomin.samples.callme.model.Callme;
import pl.piomin.samples.callme.repository.CallmeRepository;

import java.util.Date;

@RestController
@RequestMapping("/callme")
public class CallmeController {

    @Value("${spring.application.name}")
    private String appName;
    @Value("${POD_NAME}")
    private String podName;
    @Value("${POD_NAMESPACE}")
    private String podNamespace;
    @Autowired
    private CallmeRepository repository;

    @GetMapping("/ping")
    public String ping() {
        Callme c = repository.save(new Callme(new Date(), podName));
        return appName + "(id=" + c.getId() + "): " + podName + " in " + podNamespace;
    }

}
