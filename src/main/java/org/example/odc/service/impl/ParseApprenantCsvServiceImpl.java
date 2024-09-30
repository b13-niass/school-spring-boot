package org.example.odc.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.odc.service.ParseApprenantCsvService;
import org.example.odc.web.dto.request.ApprenantImportDTORequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service("apprenantCsv")
public class ParseApprenantCsvServiceImpl implements ParseApprenantCsvService {

    @Override
    public List<ApprenantImportDTORequest> parseCsvFile(MultipartFile csvFile) {
        List<ApprenantImportDTORequest> apprenants = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
                for (CSVRecord record : csvParser) {
                    ApprenantImportDTORequest apprenant = ApprenantImportDTORequest.builder()
                            .nomTuteur(record.get("nomTuteur"))
                            .prenomTuteur(record.get("prenomTuteur"))
                            .contactTuteur(record.get("contactTuteur"))
                            .cniFile(loadFile(record.get("cniFile")))
                            .extraitFile(loadFile(record.get("extraitFile")))
                            .diplomeFile(loadFile(record.get("diplomeFile")))
                            .casierFile( loadFile(record.get("casierFile")))
                            .photoCouverture(loadFile(record.get("photoCouverture")))
                            .nom(record.get("nom"))
                            .prenom(record.get("prenom"))
                            .adresse(record.get("adresse"))
                            .email(record.get("email"))
                            .password(record.get("password"))
                            .photo(loadFile(record.get("photo")))
                            .telephone(record.get("telephone"))
                            .referentiel(record.get("referentiel"))
                            .build();

                    apprenants.add(apprenant);
                }
            return apprenants;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MultipartFile loadFile(String filePath) {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                return new MultipartFile() {
                    @Override
                    public String getName() {
                        return path.getFileName().toString();
                    }

                    @Override
                    public String getOriginalFilename() {
                        return path.getFileName().toString();
                    }

                    @Override
                    public String getContentType() {
                        try {
                            return Files.probeContentType(path);
                        } catch (IOException e) {
                            return null;
                        }
                    }

                    @Override
                    public boolean isEmpty() {
                        try {
                            return Files.size(path) == 0;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public long getSize() {
                        try {
                            return Files.size(path);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public byte[] getBytes() throws IOException {
                        return Files.readAllBytes(path);
                    }

                    @Override
                    public InputStream getInputStream() throws IOException {
                        return Files.newInputStream(path);
                    }

                    @Override
                    public void transferTo(File dest) throws IOException, IllegalStateException {
                        Files.copy(path, dest.toPath());
                    }
                };
            }
        return null;
    }

}
