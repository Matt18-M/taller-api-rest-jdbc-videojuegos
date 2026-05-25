package com.krakedev.jdbc.videojuegos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.jdbc.videojuegos.services.ServicioVideoJuegoJdbc;
import com.krakedev.videojuegos.entidades.VideoJuego;

@RestController
@RequestMapping("/jdbc/videojuegos")
public class VideojuegoJdbcController {

	private final ServicioVideoJuegoJdbc servicio;

	public VideojuegoJdbcController(ServicioVideoJuegoJdbc servicio) {
		this.servicio = servicio;
	}

	@PostMapping
	public VideoJuego crear(@RequestBody VideoJuego vj) {
		return servicio.crear(vj);
	}

	@GetMapping
	public List<VideoJuego> listar() {
		return servicio.listar();
	}

	@GetMapping("/{codigo}")
	public VideoJuego buscar(@PathVariable String codigo) {
		return servicio.buscarPorCodigo(codigo);
	}

	@PutMapping("/{codigo}")
	public VideoJuego actualizar(@PathVariable String codigo, @RequestBody VideoJuego vj) {
		return servicio.actualizar(codigo, vj);
	}

	@DeleteMapping("/{codigo}")
	public boolean eliminar(@PathVariable String codigo) {
		return servicio.eliminar(codigo);
	}
}
