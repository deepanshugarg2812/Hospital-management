package com.user.management.dao.impl;

import com.auth.layer.AuthenticationLib.dto.UserDetail;
import com.user.management.convertor.CommonConvertor;
import com.user.management.dao.UserDao;
import com.user.management.dto.UserDto;
import com.user.management.entity.Role;
import com.user.management.entity.Users;
import com.auth.layer.AuthenticationLib.exception.ApplicationRuntimeException;
import com.user.management.repository.RoleRepository;
import com.user.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Implementation for Users Dao
 */
@Component
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final CommonConvertor<UserDto, Users> userDtoUserCommonConvertor;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void addUser(UserDto userDto) {
        try {
            Users users = userDtoUserCommonConvertor.convertBack(userDto);
            if (userRepository.findByUsernameOrEmil(users.getUserId(), users.getEmail()) > NumberUtils.INTEGER_ZERO) {
                throw new ApplicationRuntimeException("User with same email and username already exist",
                        HttpStatus.BAD_REQUEST.value());
            }
            userRepository.save(users);
        } catch (ApplicationRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error while saving the user", e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public UserDetail fetchUser(String userName, String password) {
        try {
            Users user = userRepository.findByUsernameAndPassword(userName, password, Boolean.TRUE);
            if (Objects.isNull(user)) {
                throw new ApplicationRuntimeException("Inalid credentials", 400);
            }
            Role role = roleRepository.findById(user.getRoleId()).get();
            return new UserDetail().setUserId(user.getId()).setRole(role.getRoleName());
        } catch (ApplicationRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error while fetching the user", e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
