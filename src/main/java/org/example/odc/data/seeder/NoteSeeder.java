package org.example.odc.data.seeder;

import org.example.odc.data.entity.Apprenant;
import org.example.odc.data.entity.Module;
import org.example.odc.data.entity.Note;
import org.example.odc.data.repository.NoteRepository;
import org.example.odc.data.repository.ApprenantRepository;
import org.example.odc.data.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(11)
public class NoteSeeder implements CommandLineRunner {

    private final NoteRepository noteRepository;
    private final ApprenantRepository apprenantRepository;
    private final ModuleRepository moduleRepository;
    @Value("${seeder.enabled}")
    private boolean seederEnabled;

    public NoteSeeder(NoteRepository noteRepository, ApprenantRepository apprenantRepository,
                      ModuleRepository moduleRepository) {
        this.noteRepository = noteRepository;
        this.apprenantRepository = apprenantRepository;
        this.moduleRepository = moduleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (seederEnabled) {
            Apprenant apprenant = apprenantRepository.findById(1L).orElseThrow();
            Module module = moduleRepository.findById(1L).orElseThrow();

            Note note1 = new Note();
            note1.setApprenant(apprenant);
            note1.setModule(module);
            note1.setNote(85);

            Note note2 = new Note();
            note2.setApprenant(apprenant);
            note2.setModule(module);
            note2.setNote(90);

            noteRepository.saveAll(Arrays.asList(note1, note2));
            System.out.println("NoteSeeder is running");
        }else {
            System.out.println("NoteSeeder is disabled");
        }
    }
}
