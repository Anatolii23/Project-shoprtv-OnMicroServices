package example.rest;

import example.rest.dto.ClientDTO;
import example.services.ClientServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientServices clientServices;

    @GetMapping("/client")
    @Operation(description = "get client by id")
    public ClientDTO getClients(@Parameter(description = "the id of client whose you find")
                                @RequestParam(name = "id") Long id) {
        return clientServices.findClientById(id);

    }

    @PostMapping("/addclient")
    @Operation(description = "add client")
    public ResponseEntity<ClientDTO> addClient(@Valid @RequestBody ClientDTO clientDTO) {
        clientServices.addClient(clientDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clientremove")
    @Operation(description = "delete client")
    public ResponseEntity<ClientDTO> removeClient(@Parameter(description = " the id of client whose remove")
                                                  @RequestParam(name = "id") Long id) {
        clientServices.deleteClientById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/client/{id}")
    @Operation(description = "change client")
    public ClientDTO editClient(@Parameter(description = "the id of client whose change")
                                @PathVariable(name = "id") Long id,
                                @Valid @RequestBody ClientDTO clientDTO) {
        return clientServices.editClient(id, clientDTO);
    }

    @GetMapping("/clients")
    @Operation(description = "find client by parameters ")
    public List<ClientDTO> findClientByParam(@Parameter(description = " the name of client whose find")
                                             @RequestParam(name = "name", required = false) String name,
                                             @Parameter(description = " the id of client whose find")
                                             @RequestParam(name = "id", required = false) Long id) {
        return clientServices.getClient(name, id);
    }
}
