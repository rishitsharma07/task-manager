package com.taskflow.taskmanager.service;

import com.taskflow.taskmanager.dto.TaskRequest;
import com.taskflow.taskmanager.entity.Task;
import com.taskflow.taskmanager.entity.User;
import com.taskflow.taskmanager.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public void createTask(TaskRequest taskRequest, User user){

        Task task = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .priority(taskRequest.getPriority())
                .status(taskRequest.getStatus())
                .dueDate(taskRequest.getDueDate())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        taskRepository.save(task);

    }

    public List<Task> getTasksByUser(User user){
        return taskRepository.findByUser(user);
    }


}
