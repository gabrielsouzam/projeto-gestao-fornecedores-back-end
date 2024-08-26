package br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.entity.SupplierEntity;

public interface SupplierRepository extends JpaRepository<SupplierEntity, UUID> {
  Optional<SupplierEntity> findByCnpj(String cnpj);
  List<SupplierEntity> findByNameContainingIgnoreCase(String filter);
  List<SupplierEntity> findByCnpjContainingIgnoreCase(String filter);
}
