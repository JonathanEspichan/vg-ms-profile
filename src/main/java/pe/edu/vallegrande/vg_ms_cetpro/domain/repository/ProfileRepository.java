package pe.edu.vallegrande.vg_ms_cetpro.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.vg_ms_cetpro.domain.model.Profile;
import reactor.core.publisher.Flux;

@Repository
public interface ProfileRepository extends ReactiveMongoRepository<Profile, String> {

    Flux<Profile> findByStatus(String status);
}
