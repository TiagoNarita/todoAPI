package com.example.demo.controllers;

import com.example.demo.business.services.TaskService;
import com.example.demo.controllers.dto.in.TaskDtoIn;
import com.example.demo.controllers.dto.out.TaskDtoOut;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService service;

    @Operation(description = "Endpoint responsável por criar tasks")
    @PostMapping("/{userId}")
    public ResponseEntity<TaskDtoOut> createTask (@PathVariable String userId, @RequestBody TaskDtoIn dto){
        return ResponseEntity.ok(service.creatTask(dto, userId));
    }

    @Operation(description = "Endpoint responsável por consultar todas as tasks de um usuário")
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<TaskDtoOut>> getUserTasks  (@PathVariable String userId){
        return ResponseEntity.ok(service.getUserTasks (userId));
    }

    @Operation(description = "Endpoint responsável por consultar uma tasks")
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDtoOut> getTasks(@PathVariable String taskId){
        return ResponseEntity.ok(service.getTask(taskId));
    }

    @Operation(description = "Endpoint responsável por mudar o status de uma tasks")
    @PutMapping("/status/{taskId}")
    public ResponseEntity<Void> changeStatusTask(@PathVariable String taskId){
        service.changeStatus(taskId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDtoOut> updateTask(@PathVariable String taskId, @RequestBody TaskDtoIn dto){
        return ResponseEntity.ok(service.updateTask(taskId, dto));
    }

    @Operation(description = "Endpoint responsável por deletar uma tasks")
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId){
        service.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
