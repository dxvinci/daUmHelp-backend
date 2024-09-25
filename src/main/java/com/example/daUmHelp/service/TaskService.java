package com.example.daUmHelp.service;

import com.example.daUmHelp.domain.achievement.Achievement;
import com.example.daUmHelp.domain.task.Task;
import com.example.daUmHelp.domain.task.TaskCompletionResponse;
import com.example.daUmHelp.domain.task.TaskDTO;
import com.example.daUmHelp.domain.user.User;
import com.example.daUmHelp.domain.user.UserTask;
import com.example.daUmHelp.repository.AchievementRepository;
import com.example.daUmHelp.repository.TaskRepository;
import com.example.daUmHelp.repository.UserRepository;
import com.example.daUmHelp.shared.util.Constants;
import com.example.daUmHelp.shared.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new NoSuchElementException(Messages.RECORD_NOT_FOUND + task.getRelatedAchievementId()));


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
        checkLevelUp(user);

        userRepository.save(user);

        return new TaskCompletionResponse(taskExperiencePoints);
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    private void checkLevelUp(User user) {
        if (user.getExperiencePoints() >= Constants.BASE_EXP_TO_LEVEL_UP) {
            user.setLevel(user.getLevel() + 1);
            user.setExperiencePoints(user.getExperiencePoints() - Constants.BASE_EXP_TO_LEVEL_UP);
        }
    }

}
