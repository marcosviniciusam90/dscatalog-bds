package com.devsuperior.dscatalog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor()
public class UserInsertDTO extends UserDTO{
    private String password;
}
