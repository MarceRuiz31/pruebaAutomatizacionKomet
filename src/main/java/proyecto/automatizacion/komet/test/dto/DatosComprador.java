package proyecto.automatizacion.komet.test.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DatosComprador {

    private String nombre;
    private String apellidos;
    private String direccion;
    private String ciudad;
    private String pais;
    private String codigoPostal;
    private String estado;
    private String telefono;
    private String nombresCompa;
    private String apellidosCompa;

}
