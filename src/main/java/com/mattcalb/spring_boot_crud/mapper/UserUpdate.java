package com.mattcalb.spring_boot_crud.mapper;

import com.mattcalb.spring_boot_crud.dtos.UserUpdateDto;
import com.mattcalb.spring_boot_crud.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserUpdate {

    void updateUserFromDto(UserUpdateDto userUpdateDto, @MappingTarget User user);
}
