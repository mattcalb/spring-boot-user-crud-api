package com.mattcalb.spring_boot_crud.service;

import com.mattcalb.spring_boot_crud.dtos.UserCreateDto;
import com.mattcalb.spring_boot_crud.dtos.UserResponseDto;
import com.mattcalb.spring_boot_crud.dtos.UserUpdateDto;
import com.mattcalb.spring_boot_crud.entity.User;
import com.mattcalb.spring_boot_crud.exceptions.EmailAlreadyRegisteredException;
import com.mattcalb.spring_boot_crud.exceptions.UserNotFoundException;
import com.mattcalb.spring_boot_crud.mapper.UserMapper;
import com.mattcalb.spring_boot_crud.mapper.UserUpdate;
import com.mattcalb.spring_boot_crud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserUpdate userUpdate;

    public UserService(UserRepository userRepository, UserMapper userMapper, UserUpdate userUpdate) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userUpdate = userUpdate;
    }

    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        if (userRepository.findByEmail(userCreateDto.email()).isPresent()) {
            throw new EmailAlreadyRegisteredException();

        }
        User newUser = User.builder()
                .email(userCreateDto.email())
                .name(userCreateDto.name())
                .birthDate(userCreateDto.birthDate())
                .build();

        User createdUser = userRepository.save(newUser);

        return userMapper.toDto(createdUser);
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> userList = userRepository.findAll();

        return userMapper.toDtoList(userList);
    }

    public UserResponseDto getUserById(String id) {
        UUID userId = UUID.fromString(id);

        return userMapper.toDto(userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new
        ));
    }

    public void deleteUserById(String id) {
        UUID userId = UUID.fromString(id);

        userRepository.deleteById(userId);
    }

    public void updateUserById(String id, UserUpdateDto userUpdateDto) {
        UUID userId = UUID.fromString(id);

        User user = userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new
        );

        userUpdate.updateUserFromDto(userUpdateDto, user);

        userRepository.save(user);
    }

}
