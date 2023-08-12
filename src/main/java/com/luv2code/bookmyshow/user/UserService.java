package com.luv2code.bookmyshow.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    /**
     * This method is used to find a user by id.
     * @param userId, Integer as input.
     *                  This is the id of the user.
     * @return User as output.
     * @throws EntityNotFoundException if user does not exist.
     * */
    public User findUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(
                "user with id [" + userId + "] does not exist"));
    }
}
