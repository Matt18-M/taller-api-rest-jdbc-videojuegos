package com.krakedev.jdbc.videojuegos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.videojuegos.entidades.VideoJuego;

@Service
public class ServicioVideoJuegoJdbc {

	private ArrayList<VideoJuego> vjs = new ArrayList<VideoJuego>();

	public VideoJuego buscarPorCodigo(String codigo) {

		for (VideoJuego v : vjs) {
			if (v.getCodigo().equals(codigo)) {
				return v;
			}
		}
		return null;
	}

	public VideoJuego crear(VideoJuego vj) {

		VideoJuego existente = buscarPorCodigo(vj.getCodigo());

		if (existente != null) {
			return null;
		} else {
			vjs.add(vj);
			return vj;
		}
	}

	public List<VideoJuego> listar() {
		return vjs;
	}

	public VideoJuego actualizar(String codigo, VideoJuego vjActualizado) {

		VideoJuego vj = buscarPorCodigo(codigo);

		if (vj != null) {
			vj.setNombre(vjActualizado.getNombre());
			vj.setPlataforma(vjActualizado.getPlataforma());
			vj.setPrecio(vjActualizado.getPrecio());
			vj.setDisponible(vjActualizado.isDisponible());
			vj.setGenero(vjActualizado.getGenero());

		}
		return vj;
	}

	public boolean eliminar(String codigo) {

		VideoJuego vj = buscarPorCodigo(codigo);

		if (vj != null) {
			vjs.remove(vj);
			return true;
		} else {
			return false;
		}
	}
}
