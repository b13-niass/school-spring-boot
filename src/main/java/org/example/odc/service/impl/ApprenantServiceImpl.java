package org.example.odc.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.odc.data.entity.Apprenant;
import org.example.odc.data.entity.PromoReferentiel;
import org.example.odc.data.entity.User;
import org.example.odc.data.repository.ApprenantRepository;
import org.example.odc.data.repository.PromoReferentielRepository;
import org.example.odc.service.*;
import org.example.odc.web.dto.request.ApprenantDTORequest;
import org.example.odc.web.dto.request.UserDTORequest;
import org.example.odc.web.dto.response.ApprenantDtoResponse;
import org.example.odc.web.dto.response.ApprenantResponse;
import org.example.odc.web.dto.response.UserDtoResponse;
import org.example.odc.web.dto.response.mapper.ApprenantResponseMapper;
import org.example.odc.web.dto.response.mapper.UserResponseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApprenantServiceImpl implements ApprenantService {

    private final ApprenantRepository apprenantRepository;
    private final UserService userService;
    private final PromoReferentielRepository promoReferentielRepository;
    private final QRCodeGenerator qrCodeGenerator;
    private final SendMail emailService;
    private final UserResponseMapper userResponseMapper;
    private final UploadFileService uploadFileService;
    private final ApprenantResponseMapper apprenantResponseMapper;


    @Override
    @Transactional
    public ApprenantResponse create(ApprenantDTORequest apprenant) {
        UserDTORequest userDTORequest = UserDTORequest.builder()
                .nom(apprenant.nom())
                .prenom(apprenant.prenom())
                .email(apprenant.email())
                .adresse(apprenant.adresse())
                .roleId(apprenant.roleId())
                .telephone(apprenant.telephone())
                .photo(apprenant.photo())
                .password(apprenant.password())
                .status(apprenant.status())
                .fonctionId(apprenant.fonctionId())
                .build();

        UserDtoResponse userDtoResponse = this.userService.create(userDTORequest);
        System.out.println(userDtoResponse.getEmail());
        String qrCode = this.qrCodeGenerator.generateQRCodeImage(userDtoResponse.getEmail(), 200,200,"qrcode");
        User user1 = userResponseMapper.toEntity(userDtoResponse);
        String content = "<p>Hello, Voici vos informations de connections</p>" +
                "<p>Email: "+apprenant.email()+"</p>" +
                "<p>Mot de passe: "+apprenant.password()+"</p>" +
                "<p>Lien : </p>";

        emailService.send(apprenant.email(),"Mail Athentification",content);

        PromoReferentiel promoReferentiel = promoReferentielRepository
                .findById(apprenant.promoReferentielId())
                .orElseThrow(() -> new IllegalArgumentException("Référentiel introuvable"));

        Apprenant app = Apprenant.builder()
                .nomTuteur(apprenant.nomTuteur())
                .prenomTuteur(apprenant.prenomTuteur())
                .contactTuteur(apprenant.contactTuteur())
                .cniFile(this.uploadFileService.saveFile(apprenant.cniFile(),"cniFile"))
                .extraitFile(this.uploadFileService.saveFile(apprenant.extraitFile(),"extraitFile"))
                .diplomeFile(this.uploadFileService.saveFile(apprenant.diplomeFile(),"diplomeFile"))
                .casierFile(this.uploadFileService.saveFile(apprenant.casierFile(),"casierFile"))
                .photoCouverture(this.uploadFileService.saveFile(apprenant.photoCouverture(),"couvertureFile"))
                .user(user1)
                .promoReferentiel(promoReferentiel)
                .qrCode(qrCode)
                .build();
        Apprenant app_response = apprenantRepository.save(app);
        return apprenantResponseMapper.toDTO(app_response);
//        return null;
    }
}
