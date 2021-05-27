package example.until;

import example.entities.AddressEntity;
import example.entities.ClientEntity;
import example.rest.dto.AddressDTO;
import example.rest.dto.ClientDTO;
import org.springframework.beans.BeanUtils;

public class EntityDtoMapper {
    public static ClientDTO mappedToClientDTO(ClientEntity clientEntity) {
        ClientDTO clientDTO = ClientDTO.builder().build();
        BeanUtils.copyProperties(clientEntity, clientDTO);
        if (clientEntity.getAddressEntity() != null) {
            clientDTO.setAddress(EntityDtoMapper.mapToAddressDto(clientEntity.getAddressEntity()));
        }
        return clientDTO;
    }

    public static ClientEntity mappedToClientEntity(ClientDTO clientDTO) {
        ClientEntity clientEntity = new ClientEntity();
        BeanUtils.copyProperties(clientDTO, clientEntity);
        if (clientDTO.getAddress() != null) {
            clientEntity.setAddressEntity(EntityDtoMapper.mappedToAddressEntity(clientDTO.getAddress()));
        }
        return clientEntity;
    }

    public static AddressDTO mapToAddressDto(AddressEntity addressEntity) {
        AddressDTO addressDTO = AddressDTO.builder().build();
        BeanUtils.copyProperties(addressEntity, addressDTO);
        return addressDTO;
    }

    public static AddressEntity mappedToAddressEntity(AddressDTO addressDTO) {
        AddressEntity addressEntity = AddressEntity.builder().build();
        BeanUtils.copyProperties(addressDTO, addressEntity);
        return addressEntity;
    }
}
