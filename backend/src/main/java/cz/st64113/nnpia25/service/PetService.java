package cz.st64113.nnpia25.service;

import cz.st64113.nnpia25.domain.Pet;
import cz.st64113.nnpia25.domain.User;
import cz.st64113.nnpia25.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PetService {
    private final PetRepository petRepository;
    private final UserService userService;

    public PetService(PetRepository petRepository, UserService userService) {
        this.petRepository = petRepository;
        this.userService = userService;
    }

    public Pet findPetById(Long id) {
        Optional<Pet> res = petRepository.findById(id);
        if (res.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found");
        }

        Pet pet = res.get();
        log.debug("User found: {}", pet.toString());
        return pet;
    }

    public List<Pet> findAllPets() {
        log.debug("Finding all pets");
        return petRepository.findAll();
    }

    public List<Pet> findPetsByOwner(Long ownerId) {
        log.debug("Finding pets by owner.");
        User owner = userService.findUserById(ownerId);
        return petRepository.findPetsByOwner(owner);
    }
}
