package cz.st64113.nnpia25.controller;

import cz.st64113.nnpia25.domain.Pet;
import cz.st64113.nnpia25.service.PetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) { this.petService = petService; }

    @GetMapping("/pets")
    public Collection<Pet> findAllPets(@RequestParam(required = false) Long owner_id) { return (owner_id == null) ? petService.findAllPets() : petService.findPetsByOwner(owner_id); }

    @GetMapping("/pets/{id}")
    public Pet findPet(@PathVariable Long id) { return petService.findPetById(id); }
}
