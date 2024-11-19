package com.spring.handbook.data.service;

import com.spring.handbook.data.dto.AddressDTO;
import com.spring.handbook.data.dto.BaseUserDTO;
import com.spring.handbook.data.dto.UserDTO;
import com.spring.handbook.data.entity.Address;
import com.spring.handbook.data.entity.User;
import com.spring.handbook.data.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDTO update(Long id) {
        var user = userRepository.findById(id).orElseThrow();
        user.setFirstName(UUID.randomUUID().toString());
        user.setLastName(UUID.randomUUID().toString());
        var address = user.getAddress();
        AddressDTO addressDTO = new AddressDTO(address.getId(), address.getValue());
        return new UserDTO(id, user.getFirstName(), user.getLastName(), null, null, addressDTO);
    }

    /**
     * Three EntityManagers will be created at #1, #3, #4
     */
    public UserDTO updateInNonTransactionContext(Long id) {
        var user = userRepository.findById(id).orElseThrow(); // #1
        user.setId(id);
        user.setFirstName(UUID.randomUUID().toString());
        user.setLastName(UUID.randomUUID().toString());
        var address = user.getAddress();
        address.setValue(UUID.randomUUID().toString());
        AddressDTO addressDTO = new AddressDTO(address.getId(), address.getValue()); // #2 this will throw Lazy Exception if #1 don't include any

        // #3 get user
        user = userRepository.findById(id).orElseThrow();
        user.setFirstName(UUID.randomUUID().toString());
        user.setLastName(UUID.randomUUID().toString());

        // #4 save user
        userRepository.save(user);
        return new UserDTO(id, user.getFirstName(), user.getLastName(), null, null, addressDTO);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User saveThenGet() {
        var saveUser = this.saveUser();
        return userRepository.getReferenceById(saveUser.getId());
    }

    @Transactional
    public User saveUser() {
        var address = new Address();
        address.setValue("01, Nguyen Thai Hoc");

        var user = new User();
        user.setFirstName("Duy");
        user.setLastName("Le");
        user.setAddress(address);
        return userRepository.save(user);
    }

    @Transactional
    public BaseUserDTO getUserById(Long userId) {
        var user = userRepository.getReferenceById(userId);
        return new BaseUserDTO(user.getId(), user.getFirstName(), null);
    }

}
