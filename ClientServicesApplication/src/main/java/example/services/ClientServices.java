package example.services;

import example.entities.AddressEntity;
import example.entities.ClientEntity;
import example.errors.ServiceNotFoundException;
import example.repositories.AddressCache;
import example.repositories.AddressRepository;
import example.repositories.ClientCache;
import example.repositories.ClientRepository;
import example.rest.dto.ClientDTO;
import example.until.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServices {
    private final ClientRepository clientRepository;
    private final ClientCache clientCache;
    private final AddressRepository addressRepository;
    private final AddressCache addressCache;

    public ClientDTO addClient(ClientDTO clientDTO) {
        ClientEntity clientEntity = EntityDtoMapper.mappedToClientEntity(clientDTO);
        AddressEntity addressEntity = EntityDtoMapper.mappedToAddressEntity(clientDTO.getAddress());
        clientEntity.setAddressEntity(addressEntity);
        addressEntity.setClientEntity(clientEntity);
        ClientEntity save = clientRepository.save(clientEntity);
        addressRepository.save(save.getAddressEntity());
        clientCache.saveClientInCache(EntityDtoMapper.mappedToClientDTO(save));
        addressCache.saveAddressInCache(EntityDtoMapper.mappedToClientDTO(save).getAddress());
        return EntityDtoMapper.mappedToClientDTO(save);
    }

    public void deleteClientById(Long id) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);
        if (clientEntity.isPresent()) {
            if (clientEntity.get().getAddressEntity() != null) {
                addressRepository.delete(clientEntity.get().getAddressEntity());
                addressCache.deleteAddressFromCache(clientEntity.get().getId());
            }
            clientRepository.delete(clientEntity.get());
            clientCache.deleteClientFromCache(clientEntity.get().getId());
        } else
            throw new ServiceNotFoundException(id);
    }

    public ClientDTO editClient(Long id, ClientDTO clientDTO) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);
        if (clientEntity.isPresent()) {
            ClientEntity client = clientEntity.get();
            client.setName(clientDTO.getName());
            client.setAddressEntity(EntityDtoMapper.mappedToAddressEntity(clientDTO.getAddress()));
            ClientEntity save = clientRepository.save(client);
            AddressEntity save1 = addressRepository.save(client.getAddressEntity());
            clientCache.saveClientInCache(EntityDtoMapper.mappedToClientDTO(save));
            addressCache.saveAddressInCache(EntityDtoMapper.mapToAddressDto(save1));
            return EntityDtoMapper.mappedToClientDTO(save);
        } else {
            throw new ServiceNotFoundException(id);
        }
    }

    public ClientDTO findClientById(Long id) {
        return clientCache.getClient(id).orElseGet(()->clientRepository.findById(id)
                .map(EntityDtoMapper::mappedToClientDTO).orElseThrow(()-> new ServiceNotFoundException(id)));
    }


    public List<ClientDTO> getClient(String name, Long id) {
        return clientRepository.findAll().stream()
                .filter(clientEntity -> name == null || clientEntity.getName().equals(name))
                .filter(clientEntity -> id == null || clientEntity.getId() == id)
                .map(EntityDtoMapper::mappedToClientDTO)
                .collect(Collectors.toList());
    }
}