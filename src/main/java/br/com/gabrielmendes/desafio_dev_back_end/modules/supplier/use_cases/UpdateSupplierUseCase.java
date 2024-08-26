package br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.use_cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielmendes.desafio_dev_back_end.exceptions.SupplierNotFoundException;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.dto.UpdateSupplierDTO;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.entity.SupplierEntity;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.repository.SupplierRepository;

@Service
public class UpdateSupplierUseCase {
  
  @Autowired
  private SupplierRepository supplierRepository;
 
  public SupplierEntity execute(UpdateSupplierDTO supplierEntityToUpdate) {

    var supplierOptional = this.supplierRepository.findByCnpj(supplierEntityToUpdate.getCnpj());

    if(supplierOptional.isEmpty()) {
      throw new SupplierNotFoundException();
    }

    var existingSupplier = supplierOptional.get();

    existingSupplier.setName(supplierEntityToUpdate.getName());
    existingSupplier.setCellphone(supplierEntityToUpdate.getCellphone());
    existingSupplier.setEmail(supplierEntityToUpdate.getEmail());
    existingSupplier.setDescription(supplierEntityToUpdate.getDescription());
    
    var result = this.supplierRepository.save(existingSupplier);
    return result;
  }
}
