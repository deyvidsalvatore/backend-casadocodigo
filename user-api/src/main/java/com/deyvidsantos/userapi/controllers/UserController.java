package com.deyvidsantos.userapi.controllers;

import java.util.List;

import com.deyvidsantos.shoppingclient.dto.UserDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deyvidsantos.userapi.services.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/")
    public List<UserDTO> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    UserDTO findUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/user/cpf/{cpf}")
    UserDTO findUserByCpf (@PathVariable String cpf) {
        return userService.findByCpf(cpf);
    }

    @PostMapping("/user")
    UserDTO newUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @DeleteMapping("/user/{id}")
    UserDTO deleteUserById(@PathVariable Long id) {
        return userService.delete(id);
    }

    @GetMapping("/user/search")
    public List<UserDTO> queryByName(@RequestParam(name = "nome", required = true) String nome) {
        return userService.queryByName(nome + "%");
    }

    @GetMapping("/user/cpf/{cpf}")
    UserDTO findByCpfAndKey(
            @RequestParam(name="key", required=true) String key,
            @PathVariable String cpf) {
        return userService.findByCpfAndKey(cpf, key);
    }
/*
    @GetMapping("/")
    public String getMensagem() {
        return "Spring boot is working!";
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return usuarios;
    }

    @GetMapping("/users/{cpf}")
    public UserDTO getUsersByCpf(@PathVariable String cpf) {
        for (UserDTO userFilter : usuarios) {
            if (userFilter.getCpf().equals(cpf)) {
                return userFilter;
            }
        }
        return null;
    }

    @PostMapping("/newUser")
    UserDTO inserir(@RequestBody UserDTO userDTO) {
        userDTO.setDataCadastro(new Date());
        usuarios.add(userDTO);
        return userDTO;
    }

    @DeleteMapping("/user/{cpf}")
    public boolean remover(@PathVariable String cpf) {
        for (UserDTO userFilter : usuarios) {
            if (userFilter.getCpf().equals(cpf)) {
                usuarios.remove(userFilter);
                return true;
            }
        }
        return false;
    }
    */
}
