package pe.edu.vallegrande.vg_ms_cetpro.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.vg_ms_cetpro.domain.dto.ProfileDto;
import pe.edu.vallegrande.vg_ms_cetpro.domain.model.Profile;
import pe.edu.vallegrande.vg_ms_cetpro.domain.dto.Ubigeo;
import pe.edu.vallegrande.vg_ms_cetpro.domain.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    private ExternalService externalService;

public ProfileService(ProfileRepository profileRepository, ExternalService externalService){
    this.profileRepository = profileRepository;
    this.externalService =externalService;
}

    public Flux<ProfileDto> getByStatus(String status) {
        return profileRepository.findByStatus(status)
                .flatMap(this::converTo); // Convierte cada Profile a ProfileDto
    }
    public Mono<Profile> create(Profile profile) {
        profile.setStatus("A");
        return profileRepository.save(profile);
    }

    public Mono<Profile> update(String id, Profile profile) {
        return profileRepository.findById(id)
                .flatMap(p -> {
                    p.setName(profile.getName());
                    p.setModularCode(profile.getModularCode());
                    p.setDreGre(profile.getDreGre());
                    p.setManagementType(profile.getManagementType());
                    p.setStatus("A");
                    return profileRepository.save(p);
                });
    }

    public Mono<Profile> changeStatus(String id, String status) {
        return profileRepository.findById(id)
                .flatMap(p -> {
                    p.setStatus(status);
                    return profileRepository.save(p);
                });
    }
    public Flux<Ubigeo> findAllUbigeo() {
        log.info("Listando todos los ubigeos");
        return externalService.findAllUbigeo();
    }
    public Mono<Profile> getById(String id) {  // Método añadido
        return profileRepository.findById(id);
    }

    private Mono<ProfileDto> converTo(Profile profile){
    ProfileDto dto =new ProfileDto();
        dto.setId(profile.getId());
        dto.setName(profile.getName());
        dto.setModularCode(profile.getModularCode());
        dto.setDreGre(profile.getDreGre());
        dto.setManagementType(profile.getManagementType());
        dto.setStatus(profile.getStatus());

        Mono<Ubigeo> ubigeoMono = externalService.findById(profile.getUbigeo())
                .doOnNext(ubigeo -> {
                    if (ubigeo == null) {
                        System.out.println("Ubigeo no encontrado");
                    }
                });
        return Mono.zip(ubigeoMono, Mono.just(profile.getId()), (ubigeo, id) -> {
            dto.setUbigeo(ubigeo);
            dto.setId(id);
            return dto;
        });

    }


}