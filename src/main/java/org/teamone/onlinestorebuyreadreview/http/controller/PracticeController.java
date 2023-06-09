package org.teamone.onlinestorebuyreadreview.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.teamone.onlinestorebuyreadreview.database.repository.PracticeRepository;

/**
 * @author Stanislav Hlova
 */
@Controller
@RequestMapping("/practice")
@RequiredArgsConstructor
public class PracticeController {
    private final PracticeRepository practiceRepository;

    @GetMapping
    public String getAllData(Model model){
        model.addAttribute("dataList",practiceRepository.getAllData());
        return "practice";
    }
}
