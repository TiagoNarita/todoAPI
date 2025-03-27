package com.example.demo.business.services;

import com.example.demo.business.mappers.TaskMapper;
import com.example.demo.controllers.dto.in.TaskDtoIn;
import com.example.demo.controllers.dto.out.TaskDtoOut;
import com.example.demo.infrastructure.entitys.Task;
import com.example.demo.infrastructure.entitys.User;
import com.example.demo.infrastructure.enums.TaskStatusEnum;
import com.example.demo.infrastructure.repositorys.TaskRepository;
import com.example.demo.infrastructure.repositorys.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    public TaskDtoOut creatTask(TaskDtoIn dto, String userId){
         User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        Task task = taskMapper.toEntity(dto);
        task.setUser(user);

        return taskMapper.toOut(
                taskRepository.save(task));
    }

    public TaskDtoOut getTask(String taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + taskId));
        return taskMapper.toOut(task);
    }

    public List<TaskDtoOut> getUserTasks(String userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);

        return tasks.stream().map(taskMapper::toOut).toList();
    }

    public void changeStatus(String taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + taskId));

        TaskStatusEnum status = task.getStatus() == TaskStatusEnum.ACTIVE ? TaskStatusEnum.INACTIVE :TaskStatusEnum.ACTIVE;
        task.setStatus(status);

        taskRepository.save(task);
    }

    public TaskDtoOut updateTask(String taskId, TaskDtoIn dto) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + taskId));

        if(dto.title() != null && !dto.title().isEmpty()){
            task.setTitle(dto.title());
        }
        if(dto.description() != null && !dto.description().isEmpty()){
            task.setTitle(dto.description());
        }

        return taskMapper.toOut(taskRepository.save(task));
    }

    public void deleteTask(String taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new EntityNotFoundException("Task not found with ID: " + taskId);
        }

        taskRepository.deleteById(taskId);
    }
}
