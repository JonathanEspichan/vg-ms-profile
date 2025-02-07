package pe.edu.vallegrande.vg_ms_cetpro.domain.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Ubigeo {

    @Id
    private String idUbigeo;        
    private String ubigeoReniec;    
    private String department;       
    private String province;         
    private String district;         
    private String region;           
    private String status; 

}