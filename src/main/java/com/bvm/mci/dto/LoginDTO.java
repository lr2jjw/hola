package com.bvm.mci.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LoginDTO implements Serializable {


    private static final long serialVersionUID = 8925828423283379238L;

    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @NotBlank(message= "El nombre de usuario no puede venir vacío")
    private String userName;

    private String password;


}
