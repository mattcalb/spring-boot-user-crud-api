package com.mattcalb.spring_boot_crud.mapper;

import com.mattcalb.spring_boot_crud.dtos.UserResponseDto;
import com.mattcalb.spring_boot_crud.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);
    List<UserResponseDto> toDtoList(List<User> userList);
}
