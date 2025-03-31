package cz.st64113.nnpia25.component;

import cz.st64113.nnpia25.domain.Pet;
import cz.st64113.nnpia25.domain.User;
import cz.st64113.nnpia25.repository.PetRepository;
import cz.st64113.nnpia25.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    public DataInitializer(UserRepository userRepository, PetRepository petRepository) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
    }

    @Override
    public void run(String... args) {
        User admin = new User(0L, "st64113@upce.cz", "nnpia25", true);
        if (!userRepository.existsById(admin.getId())) {
            userRepository.save(admin);
            log.debug("Admin user created: {}", admin.toString());
        } else {
            admin = userRepository.findById(admin.getId()).get();
        }

        Pet hafik = new Pet(0L, "Hafik", admin);
        if (!petRepository.existsById(hafik.getId())) {
            petRepository.save(hafik);
            log.debug("Hafik pet created: {}", hafik.toString());
        }
    }
}
