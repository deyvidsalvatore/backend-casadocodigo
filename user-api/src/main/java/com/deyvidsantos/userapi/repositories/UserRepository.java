package com.deyvidsantos.userapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deyvidsantos.userapi.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByCpf(String cpf);
    List<User> queryByNomeLike(String nome);
    User findByCpfAndKey(String cpf, String key);
}
