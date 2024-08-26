package br.com.gabrielmendes.desafio_dev_back_end.modules.supplier.entity;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Supplier")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true)
  private String cnpj;

  private String name;

  private String cellphone;

  @Email(message = "O campo deve possuir formato de email")
  private String email;

  @Length(min = 8, max = 100, message = "A senha deve possuir entre 8 e 100 caracteres")
  private String password;

  private String description;
}
