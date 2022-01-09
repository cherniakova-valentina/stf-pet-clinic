package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    static final String LAST_NAME = "Smith";
    static final Long OWNER_ID = 1L;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService ownerService;

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        // given
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        // when
        Owner smith  = ownerService.findByLastName(LAST_NAME);

        // then
        assertEquals(LAST_NAME, smith.getLastName());
        verify(ownerRepository).findByLastName(any());
        verify(ownerRepository, times(1)).findByLastName(any());
    }

    @Test
    void findAll() {
        // given
        Set<Owner> owners = new HashSet<>();

        Owner owner1 = Owner.builder().id(1L).build();
        Owner owner2 = Owner.builder().id(2L).build();

        owners.add(owner1);
        owners.add(owner2);

        when(ownerRepository.findAll()).thenReturn(owners);

        // when
        Set<Owner> resultOwners = ownerService.findAll();

        //then
        assertNotNull(resultOwners);
        assertEquals(owners.size(), resultOwners.size());
        verify(ownerRepository).findAll();
        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        // given

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        // when
        Owner owner  = ownerService.findById(OWNER_ID);

        // then
        assertNotNull(owner);
        assertEquals(OWNER_ID, owner.getId());
        verify(ownerRepository).findById(any());
        verify(ownerRepository, times(1)).findById(any());
    }

    @Test
    void findByIdNotFound() {
        // given
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when
        Owner owner  = ownerService.findById(OWNER_ID);

        // then
        assertNull(owner);
        verify(ownerRepository).findById(any());
        verify(ownerRepository, times(1)).findById(any());
    }

    @Test
    void save() {
        // given
        when(ownerRepository.save(any())).thenReturn(returnOwner);

        // when
        Owner owner  = ownerService.save(returnOwner);

        // then
        assertNotNull(owner);
        assertEquals(OWNER_ID, owner.getId());
        verify(ownerRepository).save(any());
        verify(ownerRepository, times(1)).save(any());
    }

    @Test
    void delete() {
        // when
        ownerService.delete(returnOwner);

        // then
        verify(ownerRepository).delete(any());
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        // when
        ownerService.deleteById(OWNER_ID);

        // then
        verify(ownerRepository).deleteById(anyLong());
        verify(ownerRepository, times(1)).deleteById(anyLong());
    }
}