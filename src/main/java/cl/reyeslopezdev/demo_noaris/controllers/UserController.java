package cl.reyeslopezdev.demo_noaris.controllers;

import cl.reyeslopezdev.demo_noaris.controllers.response.ErrorResponse;
import cl.reyeslopezdev.demo_noaris.controllers.response.UserResponse;
import cl.reyeslopezdev.demo_noaris.persistence.entities.user.User;
import cl.reyeslopezdev.demo_noaris.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    private static final String ERROR_RESPONSE = "El Correo ya esta registrado";


    @PostMapping(value = "/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody User user, BindingResult result) {
        if (userService.findUserByEmail(user.getEmail()) == null) {
            User userCreated = userService.save(user);
            UserResponse userResponse = new UserResponse(userCreated.getId(), userCreated.getCreated(), userCreated.getLastModified(), userCreated.getLastLogin(), userCreated.getIsActive(), userCreated.getToken());
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
        } else {
            ErrorResponse errorResponse = new ErrorResponse(ERROR_RESPONSE);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

    }

    @GetMapping(value = "/allUsers")
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @GetMapping(value = "/findUser/{id}")
    public ResponseEntity<?> findUserById(@PathVariable UUID id) {
        //Validate
        Optional<User> optionalUser = userService.findById(id);
        //Is present
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.orElseThrow());
        }
        //Is not present
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User user) {
        user.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }

    @GetMapping(value = "/deleteUser/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable UUID id) {
        User userDelete = new User();
        userDelete.setId(id);
        //Validate
        Optional<User> optionalUser = userService.delete(userDelete);
        //Is present
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.orElseThrow());
        }
        //Is not present
        return ResponseEntity.notFound().build();
    }
}
