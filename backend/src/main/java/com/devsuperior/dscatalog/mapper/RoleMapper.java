package com.devsuperior.dscatalog.mapper;

import com.devsuperior.dscatalog.dto.RoleDTO;
import com.devsuperior.dscatalog.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role dtoToEntity(RoleDTO dto);

    RoleDTO entityToDTO(Role entity);
}
