package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    final Long ownerId = 1L;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1, ownerSet.size());

    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void saveExistingId() {
        long owner2Id = 2L;

        Owner owner2 = Owner.builder().id(owner2Id).build();
        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(owner2Id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner saved = ownerMapService.save(Owner.builder().build());
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(lastName);

        assertNotNull(owner);
        assertNotNull(owner.getLastName());
        assertEquals(ownerId, owner.getId());
        assertEquals(lastName, owner.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = ownerMapService.findByLastName("foo");

        assertNull(owner);
    }
}