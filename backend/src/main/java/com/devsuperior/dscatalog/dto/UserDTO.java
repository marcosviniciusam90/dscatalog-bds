package com.devsuperior.dscatalog.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String firstName;
    private String lastName;

    @Email(message = "Email inválido")
    private String email;

    @Setter(AccessLevel.NONE)
    private Set<RoleDTO> roles = new HashSet<>();
}
