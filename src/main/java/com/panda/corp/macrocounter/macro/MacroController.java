package com.panda.corp.macrocounter.macro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MacroController {
    private MacroService macroService;

    public MacroController(MacroService macroService) {
        this.macroService = macroService;
    }

    @GetMapping
    public void printMeals() {

    }
}
