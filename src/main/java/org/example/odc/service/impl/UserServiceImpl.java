package org.example.odc.service.impl;

import org.example.odc.data.entity.Fonction;
import org.example.odc.data.entity.Role;
import org.example.odc.data.entity.User;
import org.example.odc.data.repository.FonctionRepository;
import org.example.odc.data.repository.RoleRepository;
import org.example.odc.data.repository.UserRepository;
import org.example.odc.service.UploadFileService;
import org.example.odc.service.UserService;
import org.example.odc.web.dto.request.UserDTORequest;
import org.example.odc.web.dto.response.UserDtoResponse;
import org.example.odc.web.dto.response.mapper.UserResponseMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final FonctionRepository fonctionRepository;
    private final PasswordEncoder passwordEncoder;
    private final UploadFileService uploadFileService;
    private final UserResponseMapper responseMapper; // Service to handle file uploads

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           FonctionRepository fonctionRepository,
                           PasswordEncoder passwordEncoder,
                           UploadFileService uploadFileService,
                           UserResponseMapper responseMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.fonctionRepository = fonctionRepository;
        this.passwordEncoder = passwordEncoder;
        this.uploadFileService = uploadFileService;
        this.responseMapper = responseMapper;
    }

    @Override
    @Transactional
    public UserDtoResponse create(UserDTORequest userDTORequest) {
        Role role = roleRepository.findById(userDTORequest.roleId())
                .orElseThrow(() -> new IllegalArgumentException("Rôle introuvable avec l'ID : " + userDTORequest.roleId()));
        Fonction fonction = null;
        if (userDTORequest.fonctionId() != null){
             fonction = fonctionRepository.findById(Long.parseLong(userDTORequest.fonctionId()))
                    .orElseThrow(() -> new IllegalArgumentException("Fonction introuvable avec l'ID : " + userDTORequest.fonctionId()));
        }
        String hashedPassword = passwordEncoder.encode(userDTORequest.password());

        String photoPath = uploadFileService.saveFile(userDTORequest.photo(),"photoProfile");

        User user = User.builder()
                .nom(userDTORequest.nom())
                .prenom(userDTORequest.prenom())
                .adresse(userDTORequest.adresse())
                .email(userDTORequest.email())
                .telephone(userDTORequest.telephone())
                .photo(photoPath)
                .role(role)
                .fonction(fonction)
                .password(hashedPassword)
                .status(userDTORequest.status())
                .build();

        User user1 = userRepository.save(user);
        return responseMapper.toDTO(user1);
    }
}
