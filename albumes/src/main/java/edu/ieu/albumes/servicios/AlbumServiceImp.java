package edu.ieu.albumes.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ieu.albumes.entities.Album;
import edu.ieu.albumes.repositorios.AlbumesRepository;

@Service
public class AlbumServiceImp implements AlbumService{



	@Autowired
	private AlbumesRepository albumDao;

	
	@Override
	public Album findById(Integer id) {
		return albumDao.findById(id)
				.orElse(null);
	
	}

	@Override
	public Album findByTitulo(String titulo) {
		return albumDao.findByTitulo(titulo);
	
	}

	@Override
	public List<Album> findAll() {
		List<Album> lista = new ArrayList<>();
		albumDao.findAll()
			.forEach( lista::add );
		return lista;
	}

	@Override
	public boolean isAlbumExist(Album alb) {
		return albumDao.existsById(alb.getId() );
	}

	
	@Override @Transactional
	public void saveAlbum(Album album) {
		albumDao.save(album);
		
	}

	@Override @Transactional
	public void updateAlbum(Album alb) {
		Album albumdb = albumDao.findById(alb.getId() ).orElse(null);
		if(albumdb != null) {
			albumdb.setLanzamiento( alb.getLanzamiento() );
			albumdb.setTitulo( alb.getTitulo() );
			albumdb.setGenero( alb.getGenero() );
			albumdb.setBanda( alb.getBanda() );	
		
			
			albumDao.save(albumdb);
		}
	}

	@Override @Transactional
	public void deleteAlbumById(Integer id) {
		albumDao.deleteById(  id );	
	}
}