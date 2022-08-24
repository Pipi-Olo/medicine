package com.pipiolo.medicine.web.pharmacy;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/pharmacies")
@Controller
public class PharmacyController {

    private final PharmacyService pharmacyService;

    @GetMapping
    public String findAll(
            @ModelAttribute("pharmacySearch") PharmacySearchCondition searchCondition,
            Pageable pageable,
            Model model
    ) {
        model.addAttribute("slice",
                pharmacyService.findAll(searchCondition, pageable));

        return "pharmacy/pharmacies";
    }
}
