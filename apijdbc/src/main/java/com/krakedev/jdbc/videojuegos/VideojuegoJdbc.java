package com.krakedev.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakedev.jdbc.Conexion;
import com.krakedev.videojuegos.entidades.VideoJuego;

public class VideojuegoJdbc {

	private static final Logger log = LogManager.getLogger(VideojuegoJdbc.class);

	// metodo insertar videojuego
	public static VideoJuego insertar(String codigo, String nombre, String plataforma, double precio,
			boolean disponible, String genero) {

		Connection con = null;
		PreparedStatement ps = null;
		VideoJuego vj = null;

		try {
			con = Conexion.getConnection();
			String sql = "INSERT INTO videojuego(codigo,nombre,plataforma,precio,disponible,genero)"
					+ "VALUES (?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, codigo);
			ps.setString(2, nombre);
			ps.setString(3, plataforma);
			ps.setDouble(4, precio);
			ps.setBoolean(5, disponible);
			ps.setString(6, genero);

			vj = new VideoJuego(codigo, nombre, plataforma, precio, disponible, genero);

			int filas = ps.executeUpdate();
			log.info("Filas insertadas: " + filas);

		} catch (Exception e) {
			log.error("Error al insertar: ", e);
			throw new RuntimeException("Error al insertar: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return vj;
	}

	// metodo listar
	public static List<VideoJuego> listar() {

		List<VideoJuego> vj = new ArrayList<>();
		Connection con = null;

		try {
			con = Conexion.getConnection();

			String sql = "SELECT * FROM videojuegos";

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				VideoJuego v = new VideoJuego(rs.getString("codigo"), rs.getString("nombre"),
						rs.getString("plataforma"), rs.getDouble("precio"), rs.getBoolean("disponible"),
						rs.getString("genero"));
				vj.add(v);
			}
		} catch (Exception e) {
			log.error("Error al listar: ", e);
			throw new RuntimeException("Error al listar: " + e.getMessage());

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vj;
	}

	// buscar videojuego
	public static VideoJuego buscar(String codigo) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = "SELECT * FROM videojuegos WHERE codigo = ?";
		ResultSet rs = null;
		VideoJuego vj = null;

		try {
			con = Conexion.getConnection();

			ps = con.prepareStatement(sql);
			ps.setString(1, codigo);

			rs = ps.executeQuery();
			if (rs.next()) {
				vj = new VideoJuego(rs.getString("codigo"), rs.getString("nombre"), rs.getString("plataforma"),
						rs.getDouble("precio"), rs.getBoolean("disponible"), rs.getString("genero"));
			}

		} catch (Exception e) {
			log.error("Error al buscar", e);
			throw new RuntimeException("Error al buscar: " + e.getMessage());

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vj;
	}

	// actualizar videojuego
	public static VideoJuego actualizar(String codigo, String nuevoNombre, String nuevaPlataforma, double nuevoPrecio,
			boolean nuevaDisponible, String nuevoGenero) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE videojuegos Set nombre = ?, plataforma = ?, precio = ?, disponible = ?, genero = ? WHERE codigo = ?";
		ResultSet rs = null;
		VideoJuego vj = null;

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, nuevoNombre);
			ps.setString(2, nuevaPlataforma);
			ps.setDouble(3, nuevoPrecio);
			ps.setBoolean(4, nuevaDisponible);
			ps.setString(5, nuevoGenero);

			ps.setString(6, codigo);

			int fila = ps.executeUpdate();
			vj = new VideoJuego(codigo, nuevoNombre, nuevaPlataforma, nuevoPrecio, nuevaDisponible, nuevoGenero);
			log.info("Filas insertadas: " + fila);
		} catch (Exception e) {
			log.error("No se puede actualizar", e);
			throw new RuntimeException("Error al actualizar: " + e.getMessage());

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vj;
	}

	// eliminar videojuego
	public static boolean eliminar(String codigo) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM videojuegos WHERE codigo = ?";
		ResultSet rs = null;

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, codigo);

			int fila = ps.executeUpdate();
			log.info("Filas eliminadas: " + fila);
			return true;

		} catch (Exception e) {
			log.error("Error al eliminar", e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
