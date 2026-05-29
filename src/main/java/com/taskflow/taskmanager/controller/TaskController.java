package com.taskflow.taskmanager.controller;

import com.taskflow.taskmanager.dto.TaskRequest;
import com.taskflow.taskmanager.entity.User;
import com.taskflow.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<?> getAllTasks(){
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return ResponseEntity.ok(taskService.getTasksByUser(user));
    }

    @PostMapping
    public ResponseEntity<?> createNewTasks(@RequestBody TaskRequest taskRequest){
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return ResponseEntity.ok(taskService.createTask(taskRequest, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id,@RequestBody TaskRequest taskRequest){
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return ResponseEntity.ok(taskService.updateTask(id, taskRequest, user));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        taskService.deleteTask(id,user);
        return ResponseEntity.ok("Task deleted Sucessfully");
    }
}
