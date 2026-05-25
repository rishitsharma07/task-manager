package com.taskflow.taskmanager.service;

import com.taskflow.taskmanager.dto.TaskRequest;
import com.taskflow.taskmanager.entity.Task;
import com.taskflow.taskmanager.entity.User;
import com.taskflow.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task createTask(TaskRequest taskRequest, User user){

        Task task = Task.builder()
                .user(user)
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .priority(taskRequest.getPriority())
                .status(taskRequest.getStatus())
                .dueDate(taskRequest.getDueDate())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return taskRepository.save(task);

    }

    public List<Task> getTasksByUser(User user){
        return taskRepository.findByUser(user);
    }

    public Task updateTask(Long id, TaskRequest taskRequest, User user){

        Task task1 = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if(!task1.getUser().getId().equals(user.getId())){
            throw new RuntimeException("Unauthorized");
        }

        task1.setTitle(taskRequest.getTitle());
        task1.setDescription(taskRequest.getDescription());
        task1.setPriority(taskRequest.getPriority());
        task1.setStatus(taskRequest.getStatus());
        task1.setDueDate(taskRequest.getDueDate());
        task1.setUpdatedAt(LocalDateTime.now());

        return taskRepository.save(task1);

    }

    public void deleteTask(Long id, User user){

        Task task2 = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task2.getUser().getId().equals(user.getId())){
            throw new RuntimeException("Unauthorized");
        }

        taskRepository.deleteById(id);

    }

}
