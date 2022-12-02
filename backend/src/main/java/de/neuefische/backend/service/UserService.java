package de.neuefische.backend.service;
import de.neuefische.backend.model.User;
import de.neuefische.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService {

    private final UserRepository userRepository;
    private final IDGenerator idGenerator;

    public UserService(UserRepository userRepository, IDGenerator idGenerator) {
        this.userRepository = userRepository;
        this.idGenerator = idGenerator;
    }


    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
