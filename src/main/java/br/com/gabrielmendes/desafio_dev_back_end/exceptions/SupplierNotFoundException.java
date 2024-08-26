package br.com.gabrielmendes.desafio_dev_back_end.exceptions;

public class SupplierNotFoundException extends RuntimeException {
  public SupplierNotFoundException() {
    super("Fornecedor não foi encontrado");
  }
}