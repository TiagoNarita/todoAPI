package com.example.demo.business.mappers;

import com.example.demo.controllers.dto.in.TaskDtoIn;
import com.example.demo.controllers.dto.out.TaskDtoOut;
import com.example.demo.infrastructure.entitys.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface TaskMapper {

    Task toEntity(TaskDtoIn taskDtoIn);

    TaskDtoOut toOut(Task task);

    @Mapping(target = "id", ignore = true) // Prevent overriding ID
    void updateTaskFromDto(TaskDtoIn dto, @MappingTarget Task task);
}
