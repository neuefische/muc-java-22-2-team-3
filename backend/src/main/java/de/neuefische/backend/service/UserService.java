package de.neuefische.backend.service;

import de.neuefische.backend.model.User;
import de.neuefische.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User update(User user) {
        return UserRepository.update(user);
    }

}
