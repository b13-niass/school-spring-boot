package org.example.odc.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.odc.data.entity.Apprenant;
import org.example.odc.data.entity.PromoReferentiel;
import org.example.odc.data.entity.User;
import org.example.odc.data.repository.ApprenantRepository;
import org.example.odc.data.repository.PromoReferentielRepository;
import org.example.odc.service.*;
import org.example.odc.web.dto.request.ApprenantDTORequest;
import org.example.odc.web.dto.request.ApprenantImportDTORequest;
import org.example.odc.web.dto.request.UserDTORequest;
import org.example.odc.web.dto.response.ApprenantDtoResponse;
import org.example.odc.web.dto.response.ApprenantImportDtoResponse;
import org.example.odc.web.dto.response.ApprenantResponse;
import org.example.odc.web.dto.response.UserDtoResponse;
import org.example.odc.web.dto.response.mapper.ApprenantResponseMapper;
import org.example.odc.web.dto.response.mapper.UserResponseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private final MatriculeService matriculeService;


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

        String matricule = this.matriculeService.generateMatricule(
                promoReferentiel.getPromo().getLibelle(),
                promoReferentiel.getReferentiel().getLibelle(),
                (int)promoReferentiel.getApprenants().stream().count()
                );

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
                .matricule(matricule)
                .qrCode(qrCode)
                .build();
        Apprenant app_response = apprenantRepository.save(app);
        return apprenantResponseMapper.toDTO(app_response);
//        return null;
    }

    @Override
    @Transactional
    public ApprenantImportDtoResponse importApprenants(List<ApprenantImportDTORequest> importDTORequest) {
        ApprenantImportDtoResponse response_final = new ApprenantImportDtoResponse();
        List<Apprenant> apprenantsInscrit = new ArrayList<>();
        List<Apprenant> apprenantsNonInscrit = new ArrayList<>();
        for (ApprenantImportDTORequest apprenantDTORequest : importDTORequest) {
            Optional<Apprenant> found_apprenant = this.apprenantRepository.findByNomPrenomAndEmail(apprenantDTORequest.nom(),apprenantDTORequest.prenom(),apprenantDTORequest.email());
            if (found_apprenant.isPresent()) {
                apprenantsNonInscrit.add(found_apprenant.get());
            }else {
            UserDTORequest userDTORequest = UserDTORequest.builder()
                    .nom(apprenantDTORequest.nom())
                    .prenom(apprenantDTORequest.prenom())
                    .email(apprenantDTORequest.email())
                    .adresse(apprenantDTORequest.adresse())
                    .roleId(3L)
                    .telephone(apprenantDTORequest.telephone())
                    .photo(apprenantDTORequest.photo())
                    .password(apprenantDTORequest.password())
                    .status("ACTIF")
                    .fonctionId(null)
                    .build();

            UserDtoResponse userDtoResponse = this.userService.create(userDTORequest);
            System.out.println(userDtoResponse.getEmail());
            String qrCode = this.qrCodeGenerator.generateQRCodeImage(userDtoResponse.getEmail(), 200,200,"qrcode");
            User user1 = userResponseMapper.toEntity(userDtoResponse);
            String content = "<p>Hello, Voici vos informations de connections</p>" +
                    "<p>Email: "+apprenantDTORequest.email()+"</p>" +
                    "<p>Mot de passe: "+apprenantDTORequest.password()+"</p>" +
                    "<p>Lien : </p>";

            emailService.send(apprenantDTORequest.email(),"Mail Athentification",content);
                System.out.println(apprenantDTORequest.referentiel());
            PromoReferentiel promoReferentiel = promoReferentielRepository
                    .findPromoReferentielByStatusAndReferentielCode(apprenantDTORequest.referentiel())
                    .orElseThrow(() -> new IllegalArgumentException("Référentiel introuvable"));

            String matricule = this.matriculeService.generateMatricule(
                    promoReferentiel.getPromo().getLibelle(),
                    promoReferentiel.getReferentiel().getLibelle(),
                    (int)promoReferentiel.getApprenants().stream().count()
            );

            Apprenant app = Apprenant.builder()
                    .nomTuteur(apprenantDTORequest.nomTuteur())
                    .prenomTuteur(apprenantDTORequest.prenomTuteur())
                    .contactTuteur(apprenantDTORequest.contactTuteur())
                    .cniFile(this.uploadFileService.saveFile(apprenantDTORequest.cniFile(),"cniFile"))
                    .extraitFile(this.uploadFileService.saveFile(apprenantDTORequest.extraitFile(),"extraitFile"))
                    .diplomeFile(this.uploadFileService.saveFile(apprenantDTORequest.diplomeFile(),"diplomeFile"))
                    .casierFile(this.uploadFileService.saveFile(apprenantDTORequest.casierFile(),"casierFile"))
                    .photoCouverture(this.uploadFileService.saveFile(apprenantDTORequest.photoCouverture(),"couvertureFile"))
                    .user(user1)
                    .promoReferentiel(promoReferentiel)
                    .matricule(matricule)
                    .qrCode(qrCode)
                    .build();

                Apprenant app_response = apprenantRepository.save(app);
                apprenantsInscrit.add(app_response);
            }
        }
        response_final.setApprenantsInscrit(apprenantsInscrit);
        response_final.setApprenantsNonInscrit(apprenantsNonInscrit);
        return response_final;
    }
}
