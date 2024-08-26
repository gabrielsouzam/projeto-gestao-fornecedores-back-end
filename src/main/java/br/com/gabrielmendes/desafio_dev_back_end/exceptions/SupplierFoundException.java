package br.com.gabrielmendes.desafio_dev_back_end.exceptions;

public class SupplierFoundException extends RuntimeException {
  public SupplierFoundException() {
    super("Fornecedor já existe");
  }
}
