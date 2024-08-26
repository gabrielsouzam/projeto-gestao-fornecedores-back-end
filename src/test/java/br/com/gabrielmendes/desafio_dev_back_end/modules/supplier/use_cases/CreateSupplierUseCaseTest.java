package br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.use_cases;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gabrielmendes.desafio_dev_back_end.exceptions.SupplierFoundException;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.entity.SupplierEntity;
import br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.repository.SupplierRepository;

@ExtendWith(MockitoExtension.class)
public class CreateSupplierUseCaseTest {
  
  @InjectMocks
  private CreateSupplierUseCase createSupplierUseCase;

  @Mock
  private SupplierRepository supplierRepository;

  @Test
  public void should_not_be_able_to_create_a_supplier_with_same_cpnj() {
    
    System.out.println("Entrou");

    String cnpjSupplier = "11.111.111/1111-11";

    var supplier = new SupplierEntity();
    supplier.setCnpj(cnpjSupplier);

    when(supplierRepository.findByCnpj(cnpjSupplier)).thenReturn(Optional.of(supplier));

    var newSupplier = SupplierEntity.builder()
      .cellphone("999999999")
      .name("supplierName")
      .cnpj(cnpjSupplier)
      .description("Supplier description")
      .email("supplier@example.com")
      .password("12345678910")
      .build();

    SupplierFoundException thrown = Assertions.assertThrows(
        SupplierFoundException.class,
        () -> createSupplierUseCase.execute(newSupplier),
        "Expected execute() to throw, but it didn't"
    );

    // Verificação da mensagem da exceção (opcional)
    assertThat(thrown.getMessage()).isEqualTo("Fornecedor já existe");

    // Verificar se o repositório foi chamado
    verify(supplierRepository).findByCnpj(cnpjSupplier);

  }
}
