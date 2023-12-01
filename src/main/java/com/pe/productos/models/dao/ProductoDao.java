package com.pe.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.pe.productos.models.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long>{

	
}
