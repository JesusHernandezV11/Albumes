package edu.ieu.albumes.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.ieu.albumes.entities.Album;


public interface AlbumesRepository extends CrudRepository<Album, Integer> {

	@Query("SELECT alb FROM Album alb WHERE alb.titulo = ?1  ")

	public Album findByTitulo(String titulo);

}