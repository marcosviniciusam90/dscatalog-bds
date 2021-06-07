package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.services.validation.UserInsertValid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@UserInsertValid
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor()
public class UserInsertDTO extends UserDTO{
    private String password;
}
