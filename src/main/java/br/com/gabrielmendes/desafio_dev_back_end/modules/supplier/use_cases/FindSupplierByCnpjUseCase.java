package br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.use_cases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.entity.SupplierEntity;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.repository.SupplierRepository;

@Service
public class FindSupplierByCnpjUseCase {
  
  @Autowired
  private SupplierRepository supplierRepository;

  public List<SupplierEntity> execute(String supplierCnpj) {
    return this.supplierRepository.findByCnpjContainingIgnoreCase(supplierCnpj);
  }
}
