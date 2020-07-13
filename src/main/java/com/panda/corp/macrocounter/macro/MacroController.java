package com.panda.corp.macrocounter.macro;

import com.panda.corp.macrocounter.macro.model.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
