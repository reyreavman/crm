package ru.rrk.core.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rrk.api.dto.seller.request.CreateSellerDTO;
import ru.rrk.api.dto.seller.request.UpdateSellerDTO;
import ru.rrk.api.dto.seller.response.SellerDTO;
import ru.rrk.core.entity.Seller;
import ru.rrk.core.exception.ServiceException;
import ru.rrk.core.mapper.SellerMapper;
import ru.rrk.core.repository.data.DBData;
import ru.rrk.core.repository.seller.SellerRepository;
import ru.rrk.core.service.seller.DefaultSellerService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class SellerServiceTest {
    @Mock
    SellerRepository sellerRepository;
    @Mock
    SellerMapper sellerMapper;

    @InjectMocks
    DefaultSellerService sellerService;

    @Before
    public void prepareMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findSellerById() {
        long id = 10;
        Seller foundSeller = new Seller(10L, "Иван", "ivan@example.com", LocalDateTime.now());
        SellerDTO sellerDTO = new SellerDTO(foundSeller.getId(), foundSeller.getName(), foundSeller.getContactInfo());

        doReturn(Optional.of(foundSeller))
                .when(sellerRepository).findById(id);
        doReturn(sellerDTO)
                .when(sellerMapper).toSellerDTO(foundSeller);

        SellerDTO actualSellerDTO = sellerService.findSellerById(id);

        assertEquals(sellerDTO, actualSellerDTO);
    }

    @Test
    void findSellerById_throwsServiceException() {
        long id = 100;

        doThrow(ServiceException.class)
                .when(sellerRepository).findById(id);

        assertThrows(ServiceException.class, () -> sellerService.findSellerById(id));
        verifyNoInteractions(sellerMapper);
    }

    @Test
    void createSeller() {
        CreateSellerDTO createSellerDTO = new CreateSellerDTO("Иван", "ivanov@example.com");
        Seller mappedSeller = new Seller(null, createSellerDTO.name(), createSellerDTO.contactInfo(), null);
        Seller savedSeller = new Seller(1L, createSellerDTO.name(), createSellerDTO.contactInfo(), LocalDateTime.now());
        SellerDTO sellerDTO = new SellerDTO(savedSeller.getId(), savedSeller.getName(), savedSeller.getContactInfo());

        doReturn(mappedSeller)
                .when(sellerMapper).toSeller(createSellerDTO);
        doReturn(savedSeller)
                .when(sellerRepository).save(mappedSeller);
        doReturn(sellerDTO)
                .when(sellerMapper).toSellerDTO(savedSeller);

        SellerDTO actualSellerDTO = sellerService.createSeller(createSellerDTO);

        assertEquals(sellerDTO, actualSellerDTO);
    }

    @Test
    void createSeller_throwsIllegalArgumentException() {
        CreateSellerDTO createSellerDTO = new CreateSellerDTO("Иван", "ivanov@example.com");
        Seller mappedSeller = null;

        doReturn(mappedSeller)
                .when(sellerMapper).toSeller(createSellerDTO);
        doThrow(IllegalArgumentException.class)
                .when(sellerRepository).save(mappedSeller);

        assertThrows(IllegalArgumentException.class, () -> sellerService.createSeller(createSellerDTO));
        verify(sellerMapper).toSeller(createSellerDTO);
        verifyNoMoreInteractions(sellerMapper);
    }

    @Test
    void updateSeller() {
        long id = 1;
        UpdateSellerDTO updateSellerDTO = new UpdateSellerDTO("Иван", "ivanov@example.com");
        Seller seller = DBData.getSellerById(id);
        Seller updatedSeller = DBData.getSellerById(id);
        updatedSeller.setName(updateSellerDTO.name());
        updatedSeller.setContactInfo(updateSellerDTO.contactInfo());
        SellerDTO sellerDTO = new SellerDTO(id, updatedSeller.getName(), updatedSeller.getContactInfo());

        doReturn(Optional.of(seller))
                .when(sellerRepository).findById(id);
        doReturn(seller)
                .when(sellerMapper).updateSellerData(updateSellerDTO, seller);
        doReturn(sellerDTO)
                .when(sellerMapper).toSellerDTO(updatedSeller);

        SellerDTO actualSellerDTO = sellerService.updateSeller(id, updateSellerDTO);

        assertEquals(sellerDTO, actualSellerDTO);
        verify(sellerRepository).findById(id);
        verifyNoMoreInteractions(sellerRepository);
        verify(sellerMapper).updateSellerData(updateSellerDTO, seller);
        verify(sellerMapper).toSellerDTO(updatedSeller);
        verifyNoMoreInteractions(sellerMapper);
    }

    @Test
    void updateSeller_throwsServiceException() {
        long id = 111L;
        UpdateSellerDTO updateSellerDTO = new UpdateSellerDTO("Иван", "ivanov@example.com");

        doThrow(ServiceException.class)
                .when(sellerRepository).findById(id);

        assertThrows(ServiceException.class, () -> sellerService.updateSeller(id, updateSellerDTO));
        verify(sellerRepository).findById(id);
        verifyNoMoreInteractions(sellerRepository);
        verifyNoInteractions(sellerMapper);
    }

    @Test
    void removeSeller() {
        long id = 1;

        assertDoesNotThrow(() -> sellerService.removeSeller(id));
        verify(sellerRepository).deleteById(id);
        verifyNoMoreInteractions(sellerRepository);
    }

    @Test
    void removeSeller_throwsIllegalArgumentException() {
        Long id = null;

        assertThrows(NullPointerException.class, () -> sellerService.removeSeller(id));
        verifyNoInteractions(sellerRepository);
    }

    @Test
    void findSellerReferenceById_returnsSeller() {
        long id = 1;
        Seller expectedSeller = DBData.getSellerById(id);

        doReturn(DBData.getSellerById(id))
                .when(sellerRepository).getReferenceById(id);

        Seller actualsSeller = sellerService.findSellerReferenceById(id);

        assertEquals(expectedSeller, actualsSeller);
        verify(sellerRepository).getReferenceById(id);
        verifyNoMoreInteractions(sellerRepository);
    }

    @Test
    void findSellerReferenceById_returnsNull() {
        long id = 100;

        doReturn(null)
                .when(sellerRepository).getReferenceById(id);

        assertNull(sellerService.findSellerReferenceById(id));
        verify(sellerRepository).getReferenceById(id);
        verifyNoMoreInteractions(sellerRepository);
    }

    @Test
    void existsById_returnsTrue() {
        long id = 1;

        doReturn(true)
                .when(sellerRepository).existsById(id);

        assertTrue(sellerService.existsById(id));
        verify(sellerRepository).existsById(id);
        verifyNoMoreInteractions(sellerRepository);
    }

    @Test
    void existsById_returnsFalse() {
        long id = 0;

        doReturn(false)
                .when(sellerRepository).existsById(id);

        assertFalse(sellerService.existsById(id));
        verify(sellerRepository).existsById(id);
        verifyNoMoreInteractions(sellerRepository);
    }


}
