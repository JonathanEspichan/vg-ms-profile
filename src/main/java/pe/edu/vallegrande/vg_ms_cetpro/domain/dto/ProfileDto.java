package pe.edu.vallegrande.vg_ms_cetpro.domain.dto;

import lombok.Data;

@Data
public class ProfileDto {
    private String id;
    private String name;
    private String modularCode; // Código modular del CETPRO
    private String dreGre; // DRE/GRE
    private String managementType; // Tipo de gestión (privado, público, etc.)
    private Ubigeo ubigeo;
    private String status; // A = Activo, I = Inactivo
}
