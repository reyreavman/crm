package ru.rrk.api.controller.seller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rrk.api.dto.PagedData;
import ru.rrk.api.dto.seller.CreateSellerDTO;
import ru.rrk.api.dto.seller.SellerDTO;
import ru.rrk.core.service.seller.SellerService;

import java.util.List;

@RestController
@RequestMapping("/crm-api/v1/sellers")
@RequiredArgsConstructor
public class SellersRestController {
    private final SellerService sellerService;

    @GetMapping
    public PagedData<SellerDTO> getPagedSellers(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return sellerService.findPagedSellers(pageNumber, pageSize);
    }

    @GetMapping("all")
    public List<SellerDTO> getAllSellers() {
        return sellerService.findAllSellers();
    }

    @PostMapping
    public SellerDTO createSeller(@RequestBody CreateSellerDTO createSellerDTO) {
        return sellerService.createSeller(createSellerDTO);
    }
}
