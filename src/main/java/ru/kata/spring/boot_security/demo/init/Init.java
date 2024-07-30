package ru.kata.spring.boot_security.demo.init;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class Init {

    private final UserService userService;

    private final RoleRepository roleRepository;

    public Init(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        Role role = new Role("ROLE_USER");
        roleRepository.save(role);

        Role admin = new Role("ROLE_ADMIN");
        roleRepository.save(admin);

        User user = new User();
        user.setName("user");
        user.setLastName("userLN");
        user.setAge(25);
        user.setPassword("user");
        user.setRoles(Set.of(role));
        userService.add(user);

        User user1 = new User();
        user1.setName("admin");
        user1.setLastName("adminLN");
        user1.setAge(32);
        user1.setPassword("admin");
        user1.setRoles(Set.of(admin));
        userService.add(user1);
    }
}