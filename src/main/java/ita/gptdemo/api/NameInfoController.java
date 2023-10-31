package ita.gptdemo.api;

import ita.gptdemo.dto.NameInfoResponse;
import ita.gptdemo.service.NameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class NameInfoController {

    private NameInfoService nameInfoService;

    public NameInfoController(NameInfoService nameInfoService) {
        this.nameInfoService = nameInfoService;
    }

    @GetMapping("/name-info")
    public Mono<NameInfoResponse> getNameInfo(@RequestParam String name) {
        return nameInfoService.getNameInfo(name);
    }
}

