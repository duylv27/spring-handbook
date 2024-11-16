package com.spring.handbook.data.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.handbook.data.dto.AddressDTO;
import com.spring.handbook.data.dto.UserDTO;
import com.spring.handbook.data.entity.Address;
import com.spring.handbook.data.entity.User;
import com.spring.handbook.data.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PlatformTransactionManager transactionManager;
    private final TransactionTemplate transactionTemplate;

    public UserService(UserRepository userRepository, PlatformTransactionManager transactionManager) {
        this.userRepository = userRepository;
        this.transactionManager = transactionManager;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
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

    public UserDTO updateInNonTransactionContext(Long id) {
        var user = userRepository.findById(id).orElseThrow();
        user.setId(id);
        user.setFirstName(UUID.randomUUID().toString());
        user.setLastName(UUID.randomUUID().toString());
        var address = user.getAddress();
        AddressDTO addressDTO = new AddressDTO(address.getId(), address.getValue()); // this will throw Lazy Exception
        return new UserDTO(id, user.getFirstName(), user.getLastName(), null, null, addressDTO);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User saveThenGet() {
        var saveUser = this.saveUser();
        return getUserById(saveUser.getId());
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
    public User getUserById(Long userId) {
        return userRepository.getReferenceById(userId);
    }
}
