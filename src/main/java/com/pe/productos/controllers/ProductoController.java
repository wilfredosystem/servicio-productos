package com.pe.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pe.productos.models.entity.Producto;
import com.pe.productos.models.service.IProductoService;

@RestController
public class ProductoController {
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll().stream().map(//se agreago stream para agregar el puerto
				producto->{
					producto.setPort(port);//con port vemos el puerto que esta en properties, osea 0
					producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));//con esto vemos el puerto real que se genera dinamicamente
					return producto;
				}).collect(Collectors.toList());
	}

	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id){
		Producto producto = productoService.findById(id);
		producto.setPort(port);//se agrego puerto, con port vemos el puerto que esta en properties
		producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));//con esto vemos el puerto real que se genera dinamicamente
		//hablitar para probar circuit breaker con hystrix
		/*	
		boolean ok=false;
		if(!ok) {
			throw new RuntimeException("no se pudo cargar el producto");
		}
		*/
		 
		//para probar timeout con hystrix
		
//		try {
//			Thread.sleep(3000L);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return producto;
	}
}
