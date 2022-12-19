package kz.solva.controller;

import io.swagger.annotations.Api;
import kz.solva.dto.LimitDto;
import kz.solva.model.Limit;
import kz.solva.service.LimitService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/limit")
@Api(tags = "limit")
public class LimitController {
    private final LimitService limitService;

    @Autowired
    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }


    @PostMapping("/newLimit")
    public ResponseEntity<Limit> newLimit(@RequestBody LimitDto limitDto) {
        return ResponseEntity.ok(limitService.newLimit(limitDto));
    }

    @GetMapping("/getAllLimits")
    public List<Limit> getAllLimits(){
        return limitService.getAllLimits();
    }

    @GetMapping("/getLimitsByAccount/{account_from}")
    public List<Limit> getLimitsByAccount(@PathVariable Long account_from) {
        return limitService.findLimitsByAccount(account_from);
    }
    @GetMapping("/getLimitsByAccountAndCategory/{account_from}/{category}")
    public List<Limit> getLimitsByAccountAndCategory(@PathVariable Long account_from, @PathVariable String category) {
        return limitService.findLimitByAccount_fromAndExpense_category(account_from,category);
    }


}
