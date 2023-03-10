package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.LoginResponse;
import kg.megalab.selim_trade.dto.RegisterRequest;
import kg.megalab.selim_trade.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mapping(target = "password", ignore = true)
    Admin toModel(RegisterRequest dto);


    LoginResponse.AdminInfo toLoginResponseAdminDto(Admin admin);

}
