package br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSupplierDTO {

  private String cnpj;

  private String name;

  private String cellphone;

  @Email(message = "O campo deve possuir formato de email")
  private String email;

  private String description;
}
