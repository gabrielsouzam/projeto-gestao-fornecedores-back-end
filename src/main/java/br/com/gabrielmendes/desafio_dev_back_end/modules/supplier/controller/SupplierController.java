package br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabrielmendes.desafio_dev_back_end.exceptions.SupplierNotFoundException;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.dto.UpdateSupplierDTO;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.entity.SupplierEntity;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.use_cases.CreateSupplierUseCase;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.use_cases.DeleteSupplierUseCase;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.use_cases.FindAllSuppliersUseCase;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.use_cases.FindSupplierByCnpjUseCase;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.use_cases.FindSupplierByNameUseCase;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.use_cases.UpdateSupplierUseCase;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/supplier")
public class SupplierController {
  
  @Autowired
  private CreateSupplierUseCase createSupplierUseCase;

  @Autowired 
  private DeleteSupplierUseCase deleteSupplierUseCase;

  @Autowired
  private UpdateSupplierUseCase UpdateSupplierUseCase;

  @Autowired
  private FindSupplierByNameUseCase findSupplierByNameUseCase;

  @Autowired
  private FindSupplierByCnpjUseCase findSupplierByCnpjUseCase;

  @Autowired 
  private FindAllSuppliersUseCase findAllSuppliersUseCase;

  @PostMapping("/")
  @CrossOrigin(origins = "http://localhost:3000")
  public ResponseEntity<Object> create(@Valid @RequestBody SupplierEntity supplierEntity) {
    try {
      var result = this.createSupplierUseCase.execute(supplierEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/update")
  public ResponseEntity<Object> update(@Valid @RequestBody UpdateSupplierDTO updateSupplierDTO) {
    try {
      var result = this.UpdateSupplierUseCase.execute(updateSupplierDTO);
      return ResponseEntity.ok().body(result);
    } catch (SupplierNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @DeleteMapping("/delete/{supplierCnpjToDelete}")
  public ResponseEntity<Object> delete(@PathVariable String supplierCnpjToDelete) {
    try {
      deleteSupplierUseCase.execute(supplierCnpjToDelete);
      return ResponseEntity.noContent().build();
    } catch (SupplierNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }
  
  @GetMapping("/") 
  public List<SupplierEntity> findAllSuppliers() {
    return findAllSuppliersUseCase.execute();
  } 
  
  @GetMapping("/name") 
  public List<SupplierEntity> findByFilterName(@RequestParam String filter) {
    return this.findSupplierByNameUseCase.execute(filter);
  } 

  @GetMapping("/cnpj") 
  public List<SupplierEntity> findByFilterCnpj(@RequestParam String filter) {
    return findSupplierByCnpjUseCase.execute(filter);
  } 


  
  
}
