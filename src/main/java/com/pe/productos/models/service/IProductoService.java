package com.pe.productos.models.service;

import java.util.List;

import com.pe.productos.models.entity.Producto;

public interface IProductoService {
  List<Producto> findAll();
  Producto findById(Long id);
}
