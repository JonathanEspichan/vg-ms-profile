package pe.edu.vallegrande.vg_ms_cetpro.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.vg_ms_cetpro.domain.dto.ProfileDto;
import pe.edu.vallegrande.vg_ms_cetpro.domain.dto.Ubigeo;
import pe.edu.vallegrande.vg_ms_cetpro.domain.model.Profile;
import pe.edu.vallegrande.vg_ms_cetpro.application.service.ProfileService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/management/${api.version}/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;


    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/list/active")
    public Flux<ProfileDto> listActive() {
        return profileService.getByStatus("A");
    }

    @GetMapping("/list/inactive")
    public Flux<ProfileDto> listInactive() {
        return profileService.getByStatus("I");
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<Mono<Profile>> getProfileById(@PathVariable String id) {
        return ResponseEntity.ok(profileService.getById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<Mono<Profile>> create(@RequestBody Profile profile) {
        return ResponseEntity.ok(profileService.create(profile));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mono<Profile>> update(@PathVariable String id, @RequestBody Profile profile) {
        return ResponseEntity.ok(profileService.update(id, profile));
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Mono<Profile>> activate(@PathVariable String id) {
        return ResponseEntity.ok(profileService.changeStatus(id, "A"));
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Mono<Profile>> deactivate(@PathVariable String id) {
        return ResponseEntity.ok(profileService.changeStatus(id, "I"));
    }
    @GetMapping("/list/ubigeo")
    public Flux<Ubigeo> getAllUbigeo() {
        return profileService.findAllUbigeo();
    }
}
