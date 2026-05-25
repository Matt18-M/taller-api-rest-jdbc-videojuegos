package com.krakedev.jdbc.videojuegos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.jdbc.videojuegos.VideojuegoJdbc;
import com.krakedev.videojuegos.entidades.VideoJuego;

@Service
public class ServicioVideoJuegoJdbc {

	public VideoJuego crear(VideoJuego vj) {

		return VideojuegoJdbc.insertar(vj.getCodigo(), vj.getNombre(), vj.getPlataforma(), vj.getPrecio(),
				vj.isDisponible(), vj.getGenero());
	}

	public VideoJuego buscarPorCodigo(String codigo) {

		return VideojuegoJdbc.buscar(codigo);
	}

	public List<VideoJuego> listar() {
		return VideojuegoJdbc.listar();
	}

	public VideoJuego actualizar(String codigo, VideoJuego vjActualizado) {

		return VideojuegoJdbc.actualizar(codigo, vjActualizado.getNombre(), vjActualizado.getPlataforma(),
				vjActualizado.getPrecio(), vjActualizado.isDisponible(), vjActualizado.getGenero());
	}

	public boolean eliminar(String codigo) {

		return VideojuegoJdbc.eliminar(codigo);
	}
}
