package br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.use_cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielmendes.desafio_dev_back_end.exceptions.SupplierNotFoundException;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.repository.SupplierRepository;

@Service
public class DeleteSupplierUseCase {
  
  @Autowired
  private SupplierRepository supplierRepository;

  public void execute(String supplierCnpj) {
    var supplierToDelete = this.supplierRepository.findByCnpj(supplierCnpj);


    if(supplierToDelete.isPresent()) {
      try {
        this.supplierRepository.delete(supplierToDelete.get());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }else {
      throw new SupplierNotFoundException();
    }

    

    
      
  }
}
