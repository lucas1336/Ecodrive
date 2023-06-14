package com.ecodrive.platform.u202110085.service.impl;

import com.ecodrive.platform.u202110085.exception.ValidationException;
import com.ecodrive.platform.u202110085.dto.Response;
import com.ecodrive.platform.u202110085.model.Score;
import com.ecodrive.platform.u202110085.repository.ScoreRepository;
import com.ecodrive.platform.u202110085.service.ScoreService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScoreServiceImpl implements ScoreService {
    ScoreRepository scoreRepository;
    ModelMapper modelMapper;

    public ScoreServiceImpl(ScoreRepository scoreRepository, ModelMapper modelMapper) {
        this.scoreRepository = scoreRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Score createScore(Long driverId, Score score) {
        validateScore(score);
        score.setDriverId(driverId);
        score.setRegisteredAt(new Date());
        return scoreRepository.save(score);
    }

    @Override
    public Response getScoreScope(Long driverId, Integer scope) {
        validateScope(scope);
        existsDriver(driverId);
        Score score = new Score();
        score.setDriverId(driverId);
        if (scope == 0) {
            score.setValue(scoreRepository.findAverageScoreByDriverId(driverId));
        } else {
            score.setValue(scoreRepository.findMaxScoreByDriverId(driverId));
        }
        return modelMapper.map(score, Response.class);
    }

    private void validateScore(Score score) {
        if (score.getValue() < 0) {
            throw new ValidationException("Score value debe ser mayor a 0");
        }
    }

    private void validateScope(Integer scope) {
        if (scope != 0 && scope != 1) {
            throw new ValidationException("Scope debe ser 0 o 1");
        }
    }

    private void existsDriver (Long driverId) {
        if (!scoreRepository.existsByDriverId(driverId)) {
            throw new ValidationException("El driverId no existe");
        }
    }
}
