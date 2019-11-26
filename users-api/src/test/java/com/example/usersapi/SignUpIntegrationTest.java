package com.example.usersapi;

import com.example.usersapi.exception.DuplicateUserException;
import com.example.usersapi.model.User;
import com.example.usersapi.model.UserRole;
import com.example.usersapi.repository.UserRepository;
import com.example.usersapi.repository.UserRoleRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SignUpIntegrationTest {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRepository userRepository;

    private UserRole createUserRole(){
        UserRole userRole = userRoleRepository.findByName("ROLE_USER");
        if(userRole == null){
            userRole = new UserRole();
            userRole.setName("ROLE_USER");
            userRole = userRoleRepository.save(userRole);
        }
        return userRole;
    }

    private User createUser() {
        UserRole userRole = createUserRole();

        User user = new User();
        user.setUsername("batman");
        user.setPassword("bat");
        user.setEmail("batman@bat.com");
        user.addUserRole(userRole);

        return user;
    }

    @After
    public void removeUser(){
        User foundUser = userRepository.getUserByUsername("batman");
        if(foundUser!=null)
            userRepository.delete(foundUser);
    }

    @Test
    public void signup_User_Success(){
        User user = createUser();
        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());

        User foundUser  = userRepository.getUserByUsername(savedUser.getUsername());

        assertNotNull(foundUser);
        assertEquals(savedUser.getId(), foundUser.getId());

    }

    @Test(expected = DuplicateUserException.class)
    public void signup_DuplicateUser_Exception() throws DuplicateUserException {
        try {
            User user = createUser();
            userRepository.save(user);
            User duplicateUser = createUser();
            userRepository.save(duplicateUser);
        } catch(DataIntegrityViolationException e) {
            throw new DuplicateUserException("Email/Username is already taken");
        }

    }

}
