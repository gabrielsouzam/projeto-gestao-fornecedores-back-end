package br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.use_cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gabrielmendes.desafio_dev_back_end.exceptions.SupplierFoundException;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.entity.SupplierEntity;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.repository.SupplierRepository;

@Service
public class CreateSupplierUseCase {
  
  @Autowired
  private SupplierRepository supplierRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public SupplierEntity execute(SupplierEntity supplierEntity) {
    this.supplierRepository
      .findByCnpj(supplierEntity.getCnpj())
      .ifPresent((user) -> {
        throw new SupplierFoundException();
      }); 

    
    var password = passwordEncoder.encode(supplierEntity.getPassword());
    supplierEntity.setPassword(password);

    return this.supplierRepository.save(supplierEntity);

    
  }
  
}
