package com.ecodrive.platform.u202110085.service;

import com.ecodrive.platform.u202110085.dto.Response;
import com.ecodrive.platform.u202110085.model.Score;

public interface ScoreService {
    public abstract Score createScore(Long driverId, Score score);
    public abstract Response getScoreScope(Long driverId, Integer scope);
}
