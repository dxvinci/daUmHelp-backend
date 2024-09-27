package com.example.daUmHelp.service;

import com.example.daUmHelp.domain.achievement.Achievement;
import com.example.daUmHelp.domain.achievement.AchievementDetails;
import com.example.daUmHelp.domain.achievement.AchievementResponse;
import com.example.daUmHelp.domain.task.Task;
import com.example.daUmHelp.domain.task.TaskCompletionResponse;
import com.example.daUmHelp.domain.task.TaskDTO;
import com.example.daUmHelp.domain.user.User;
import com.example.daUmHelp.domain.user.UserAchievement;
import com.example.daUmHelp.domain.user.UserTask;
import com.example.daUmHelp.repository.AchievementRepository;
import com.example.daUmHelp.repository.TaskRepository;
import com.example.daUmHelp.repository.UserRepository;
import com.example.daUmHelp.shared.util.Constants;
import com.example.daUmHelp.shared.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AchievementRepository achievementRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(TaskDTO taskDTO) {
        Task task = new Task(taskDTO);
        return taskRepository.save(task);
    }

    public List<Task> createTasks(List<TaskDTO> taskDTOs) {
        return taskDTOs.stream()
                .map(Task::new)
                .map(taskRepository::save)
                .toList();
    }

    public TaskCompletionResponse completeTask(String userId, String taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException(Messages.RECORD_NOT_FOUND + taskId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException(Messages.RECORD_NOT_FOUND + userId));

        Achievement achievement = achievementRepository.findById(task.getRelatedAchievementId())
                .orElse(null);

        UserTask userTask = user.getUserTasks().stream()
                .filter(t -> t.getTaskId().equals(taskId))
                .findFirst()
                .orElse(null);

        Integer taskExperiencePoints = task.getExperiencePoints();

        if (userTask != null) {
            userTask.setTimesCompleted(userTask.getTimesCompleted() + 1);
        } else {
            userTask = new UserTask(taskId, 1);
            user.getUserTasks().add(userTask);
        }

        user.setExperiencePoints(user.getExperiencePoints() + taskExperiencePoints);

        boolean userLeveledUp = checkLevelUp(user);

        AchievementResponse achievementResponse = new AchievementResponse();
        if (achievement != null) {
            UserAchievement unlockedUserAchievement = checkAndUnlockAchievement(user, achievement, userTask.getTimesCompleted());
            if (unlockedUserAchievement != null) {
                achievementResponse.setAchievementId(achievement.getId());
                achievementResponse.setName(achievement.getName());
                achievementResponse.setDescription(achievement.getDescription());
                achievementResponse.setType(unlockedUserAchievement.getAchievementTypeEnum());
                achievementResponse.setImageUrl(unlockedUserAchievement.getImageUrl());
            }
            else achievementResponse = null;
        }

        userRepository.save(user);
        return new TaskCompletionResponse(taskExperiencePoints, userLeveledUp, achievementResponse);
    }

    private UserAchievement checkAndUnlockAchievement(User user, Achievement achievement, int timesCompleted) {
        List<AchievementDetails> sortedAchievementDetails = achievement.getType().stream()
                .sorted((a1, a2) -> a2.getThreshold().compareTo(a1.getThreshold()))
                .toList();

        for (AchievementDetails details : sortedAchievementDetails) {
            boolean alreadyUnlocked = user.getUserAchievements().stream()
                    .anyMatch(ua -> ua.getAchievementId().equals(achievement.getId()) &&
                            ua.getAchievementTypeEnum().equals(details.getType()));

            if (timesCompleted >= details.getThreshold() && !alreadyUnlocked) {
                UserAchievement userAchievement = new UserAchievement(achievement.getId(), details.getImageUrl(), details.getType());
                user.getUserAchievements().add(userAchievement);
                return userAchievement;
            }
        }
        return null;
    }


    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    private boolean checkLevelUp(User user) {
        if (user.getExperiencePoints() >= Constants.BASE_EXP_TO_LEVEL_UP) {
            user.setLevel(user.getLevel() + 1);
            user.setExperiencePoints(user.getExperiencePoints() - Constants.BASE_EXP_TO_LEVEL_UP);
            return true;
        }
        return false;
    }

}
