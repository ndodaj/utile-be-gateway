package al.utile.utile_be_gateway.controller;

package com.example.professional.controller;

import com.example.professional.client.UserClient;
import com.example.professional.dto.ProfessionalDTO;
import com.example.professional.dto.UserDTO;
import com.example.professional.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professionals")
public class ProfessionalController {

    @Autowired
    private ProfessionalService professionalService;

    @Autowired
    private UserClient userClient;

    @GetMapping
    public List<ProfessionalDTO> getAllProfessionals() {
        return professionalService.findAll();
    }

    @GetMapping("/{id}")
    public ProfessionalDTO getProfessionalById(@PathVariable Long id) {
        return professionalService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professional not found"));
    }

    @PostMapping
    public ProfessionalDTO createProfessional(@RequestBody ProfessionalDTO professionalDTO) {
        return professionalService.save(professionalDTO);
    }

    @PutMapping("/{id}")
    public ProfessionalDTO updateProfessional(@PathVariable Long id, @RequestBody ProfessionalDTO professionalDTO) {
        return professionalService.update(id, professionalDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProfessional(@PathVariable Long id) {
        professionalService.delete(id);
    }

    @GetMapping("/user/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
        return userClient.getUserByUsername(username);
    }
}

