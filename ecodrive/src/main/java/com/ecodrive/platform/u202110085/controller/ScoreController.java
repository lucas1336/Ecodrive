package com.ecodrive.platform.u202110085.controller;

import com.ecodrive.platform.u202110085.dto.Response;
import com.ecodrive.platform.u202110085.model.Score;
import com.ecodrive.platform.u202110085.service.ScoreService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/drivers")
public class ScoreController {
    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    // URL: http://localhost:8080/api/v1/drivers/{driverId}/scores
    // Method: POST
    @Transactional
    @PostMapping("/{driverId}/scores")
    public ResponseEntity<Score> createScore(@PathVariable(name = "driverId") Long driverId, @RequestBody Score score) {
        return new ResponseEntity<Score>(scoreService.createScore(driverId, score), HttpStatus.CREATED);
    }

    // URL: http://localhost:8080/api/v1/drivers/5/scores?scope=0
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/{driverId}/scores")
    public ResponseEntity<Response> getScoreScope(@PathVariable(name = "driverId") Long driverId, @RequestParam(name = "scope") Integer scope) throws MissingServletRequestParameterException {
        if (scope == null) {
            throw new MissingServletRequestParameterException("scope", "Long");
        }

        return new ResponseEntity<Response>(scoreService.getScoreScope(driverId, scope), HttpStatus.OK);
    }
}
