package pe.edu.vallegrande.vg_ms_cetpro.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "profile")
public class Profile {

    @Id
    private String id;
    private String name;
    private String modularCode;
    private String dreGre;
    private String managementType;
    private String ubigeo;
    private String status;
}
