package com.panda.corp.macrocounter.macro;

import com.panda.corp.macrocounter.macro.model.MealDTO;
import com.panda.corp.macrocounter.macro.model.MealMacroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MacroController {
    private MacroService macroService;

    public MacroController(MacroService macroService) {
        this.macroService = macroService;
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<String>> getAvailableProducts() {
        return ResponseEntity.ok(macroService.getAvailableProductNames());
    }

    @PostMapping("/getMealMacro")
    public ResponseEntity<MealMacroDTO> calculateMacro(@RequestBody MealDTO meal) {
        return ResponseEntity.ok(macroService.getMealMacro(meal));
    }
}
