package example.rest;

import example.rest.dto.ProductDTO;
import example.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productServices;

    @GetMapping("/product")
    @Operation(description = "get product by id")
    public ProductDTO getProduct(@Parameter(description = "the id of product whose you find")
                                 @RequestParam(name = "id") Long id) {
        return productServices.findProductById(id);

    }

    @PostMapping("/addproduct")
    @Operation(description = "add product")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        productServices.addProduct(productDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/productremove")
    @Operation(description = "delete product")
    public ResponseEntity<ProductDTO> removeProduct(@Parameter(description = " the id of product whose remove")
                                                    @RequestParam(name = "id") Long id) {
        productServices.deleteProductById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/product/{id}")
    @Operation(description = "change product")
    public ProductDTO editProduct(@Parameter(description = "the id of product whose change")
                                  @PathVariable(name = "id") Long id,
                                  @Valid @RequestBody ProductDTO productDTO) {
        return productServices.editProduct(id, productDTO);
    }
    @GetMapping("/products")
    @Operation(description = "find product by parameters ")
    public List<ProductDTO> findProductByParam(@Parameter(description = " the name of product whose find")
                                               @RequestParam(name = "name", required = false) String name,
                                               @Parameter(description = " the id of product whose find")
                                               @RequestParam(name = "id", required = false) Long id,
                                               @Parameter(description = " the min Price of product whose find")
                                               @RequestParam(name = "minPrice", required = false) Integer minPrice,
                                               @Parameter(description = " the max Price of product whose find")
                                               @RequestParam(name = "maxPrice", required = false) Integer maxPrice) {
        return productServices.getProduct(maxPrice, minPrice, id, name);
    }
}
