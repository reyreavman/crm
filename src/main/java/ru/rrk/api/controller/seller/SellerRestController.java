package ru.rrk.api.controller.seller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rrk.api.dto.seller.SellerDTO;
import ru.rrk.api.dto.seller.UpdateSellerDTO;
import ru.rrk.core.service.seller.SellerService;

@RestController
@RequestMapping("/crm-api/v1/sellers/{sellerId:\\d+}")
@RequiredArgsConstructor
public class SellerRestController {
    private final SellerService sellerService;

    @GetMapping
    public SellerDTO getSellerInfo(@Min(1L) @PathVariable long sellerId) {
        return sellerService.findSellerById(sellerId);
    }

    @PatchMapping
    public SellerDTO updateSeller(@Min(1L) @PathVariable long sellerId, @RequestBody UpdateSellerDTO updateSellerDTO) {
        return sellerService.updateSeller(sellerId, updateSellerDTO);
    }

    @DeleteMapping
    public void deleteSeller(@Min(1L) @PathVariable long sellerId) {
        sellerService.removeSeller(sellerId);
    }
}
