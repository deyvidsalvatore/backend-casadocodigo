package com.deyvidsantos.userapi.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.deyvidsantos.shoppingclient.dto.UserDTO;
import com.deyvidsantos.shoppingclient.exceptions.UserNotFoundException;
import com.deyvidsantos.userapi.converter.DTOConverter;
import org.springframework.stereotype.Service;

import com.deyvidsantos.userapi.models.User;
import com.deyvidsantos.userapi.repositories.UserRepository;

/**
 * Classe de serviço para gerenciar usuários.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * Obtém todos os usuários e os converte em DTOs.
     *
     * @return uma lista de objetos UserDTO representando todos os usuários
     */
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * Encontra um usuário pelo ID e converte-o em um UserDTO.
     *
     * @param userId o ID do usuário a ser encontrado
     * @return o objeto UserDTO representando o usuário encontrado, ou null se não
     *         encontrado
     */
    public UserDTO findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(DTOConverter::convert).orElse(null);
    }

    /**
     * Salva um novo usuário ou atualiza um usuário existente.
     *
     * @param userDTO o objeto UserDTO representando o usuário a ser salvo ou
     *                atualizado
     * @return o objeto UserDTO representando o usuário salvo ou atualizado
     */
    public UserDTO save(UserDTO userDTO) {
        userDTO.setKey(UUID.randomUUID().toString());

        User user = userRepository.save(User.convert(userDTO));
        return DTOConverter.convert(user);
    }

    /**
     * Exclui um usuário pelo ID.
     *
     * @param userId o ID do usuário a ser excluído
     * @return o objeto UserDTO representando o usuário excluído, ou null se não
     *         encontrado
     */
    public UserDTO delete(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(userRepository::delete);
        return null;
    }

    /**
     * Encontra um usuário pelo CPF.
     *
     * @param cpf o CPF do usuário a ser encontrado
     * @return o objeto UserDTO representando o usuário encontrado, ou null se não
     *         encontrado
     */
    public UserDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        if (user != null) {
            return DTOConverter.convert(user);
        }
        throw new UserNotFoundException();
    }

    public UserDTO findByCpfAndKey(String cpf, String key) {
        User user = userRepository.findByCpfAndKey(cpf, key);
        if (user != null) {
            return DTOConverter.convert(user);
        }
        throw new UserNotFoundException();
    }

    /**
     * Consulta usuários pelo nome e os converte em DTOs.
     *
     * @param name o nome a ser pesquisado
     * @return uma lista de objetos UserDTO representando os usuários encontrados
     */
        public List<UserDTO> queryByName(String name) {
            List<User> users = userRepository.queryByNomeLike(name);
            return users.stream()
                    .map(DTOConverter::convert)
                    .collect(Collectors.toList());
        }

}
