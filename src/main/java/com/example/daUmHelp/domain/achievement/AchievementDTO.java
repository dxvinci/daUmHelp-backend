package com.example.daUmHelp.domain.achievement;

import java.util.List;

public record AchievementDTO(String name, String description, List<AchievementDetails> details) {
}
