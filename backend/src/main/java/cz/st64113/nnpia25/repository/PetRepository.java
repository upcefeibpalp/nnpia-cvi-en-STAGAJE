package cz.st64113.nnpia25.repository;

import cz.st64113.nnpia25.domain.Pet;
import cz.st64113.nnpia25.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findPetsByOwner(User owner);
}
