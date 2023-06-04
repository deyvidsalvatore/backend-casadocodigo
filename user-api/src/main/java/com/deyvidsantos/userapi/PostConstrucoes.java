package com.deyvidsantos.userapi;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deyvidsantos.userapi.models.User;
import com.deyvidsantos.userapi.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class PostConstrucoes {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initiateList() {
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setNome("Deyvid");
            user1.setCpf("121");
            user1.setEndereco("Rua A");
            user1.setEmail("deyvid@example.com");
            user1.setTelefone("1234-5678");
            user1.setDataCadastro(new Date());

            User user2 = new User();
            user2.setNome("Clécio");
            user2.setCpf("321");
            user2.setEndereco("Rua B");
            user2.setEmail("clecio@example.com");
            user2.setTelefone("5678-1234");
            user2.setDataCadastro(new Date());

            User user3 = new User();
            user3.setNome("Allan");
            user3.setCpf("452");
            user3.setEndereco("Rua C");
            user3.setEmail("allan@example.com");
            user3.setTelefone("3939-1234");
            user3.setDataCadastro(new Date());

            User user4 = new User();
            user4.setNome("Daniel");
            user4.setCpf("987");
            user4.setEndereco("Rua D");
            user4.setEmail("daniel@example.com");
            user4.setTelefone("8765-4321");
            user4.setDataCadastro(new Date());

            User user5 = new User();
            user5.setNome("Carlos");
            user5.setCpf("654");
            user5.setEndereco("Rua E");
            user5.setEmail("carlos@example.com");
            user5.setTelefone("5432-1098");
            user5.setDataCadastro(new Date());

            User user6 = new User();
            user6.setNome("Ana");
            user6.setCpf("789");
            user6.setEndereco("Rua F");
            user6.setEmail("ana@example.com");
            user6.setTelefone("2109-8765");
            user6.setDataCadastro(new Date());

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);
            userRepository.save(user5);
            userRepository.save(user6);
        }
    }
}
