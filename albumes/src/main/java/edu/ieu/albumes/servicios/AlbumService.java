package edu.ieu.albumes.servicios;

import java.util.List;

import edu.ieu.albumes.entities.Album;

public interface AlbumService {


	Album findById(Integer id);
	Album findByTitulo(String titulo);
    List<Album> findAll(); 
    boolean isAlbumExist(Album alb);
 
    
    void saveAlbum(Album alb);
  
    
    void updateAlbum(Album alb);
   
    
    void deleteAlbumById(Integer alb);

}