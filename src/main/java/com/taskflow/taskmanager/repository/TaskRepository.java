package com.taskflow.taskmanager.repository;

import com.taskflow.taskmanager.entity.Task;
import com.taskflow.taskmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByUser(User user);

}
