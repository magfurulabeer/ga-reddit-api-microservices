package com.example.usersapi;

import com.example.usersapi.model.User;
import com.example.usersapi.model.UserRole;
import com.example.usersapi.repository.UserRepository;
import com.example.usersapi.repository.UserRoleRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SignUpIntegrationTest {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRepository userRepository;

    private UserRole createUserRole(){
        UserRole userRole = userRoleRepository.findByName("ROLE_ADMIN");
        if(userRole == null){
            userRole = new UserRole();
            userRole.setName("ROLE_ADMIN");
            userRole = userRoleRepository.save(userRole);
        }
        return userRole;
    }

    private User createUser() {
        UserRole userRole = createUserRole();

        User user = new User();
        user.setUsername("batman");
        user.setPassword("bat");
        user.add(userRole);

        return user;
    }
}
