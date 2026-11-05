package lk.clean.architecture.cart.api.web.webMappers;

import lk.clean.architecture.cart.api.application.records.commands.CreateCartCommand;
import lk.clean.architecture.cart.api.application.records.results.CreateCartResult;
import lk.clean.architecture.cart.api.web.DTOs.request.CreateCartRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.response.CreateCartResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface CartWebMapper {

    /* __CREATE_CART__ */

    //to command
    CreateCartCommand toCreateCartCommand(CreateCartRequestDTO createCartRequestDTO);

    //to response
    CreateCartResponseDTO toCreateCartResponseDTO(CreateCartResult createCartResult);
}
