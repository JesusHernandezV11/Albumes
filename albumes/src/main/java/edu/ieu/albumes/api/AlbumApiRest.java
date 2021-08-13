package edu.ieu.albumes.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ieu.albumes.entities.Album;
import edu.ieu.albumes.servicios.AlbumService;

@RestController
@RequestMapping("/api/albumes")

public class AlbumApiRest {


	@Autowired
	private AlbumService service; 
	
	@GetMapping
	public ResponseEntity<List<Album>> listAll(){
		List<Album> listaAlbumes = service.findAll();
		if(listaAlbumes.isEmpty()) {
			return new ResponseEntity<List<Album>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Album>>(listaAlbumes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Album> getAlbum(@PathVariable("id") int id) {
        System.out.println("Fetching album with id " + id);
        Album ins = service.findById(id);
        if (ins == null) {
            System.out.println("Instrument with id " + id + " not found");
            return new ResponseEntity<Album>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Album>(ins, HttpStatus.OK);
    }
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createAlbum(@RequestBody Album ins){
		System.out.println("Creating albubm " + ins.getTitulo());

		service.saveAlbum(ins);
		 return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Album> updateAlbum(@PathVariable("id") int id,
			@RequestBody Album album){
		 System.out.println("Updating album " + id);
		 Album albBd = service.findById(id);
		 if(albBd == null) {
			 return new ResponseEntity<Album>(HttpStatus.NOT_FOUND);
		 }
		 albBd.setLanzamiento( album.getLanzamiento() );
		 albBd.setTitulo( album.getTitulo() );
		 albBd.setGenero( album.getGenero() );
		 albBd.setBanda( album.getBanda() );
		 
		 service.updateAlbum(albBd);
		 return new ResponseEntity<Album>(albBd, HttpStatus.OK );
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteAlbum(@PathVariable("id") int id){
		System.out.println("Fetching & Deleting album with id " + id);
		Album album = service.findById(id);
		if(album == null) {
			 System.out.println("Unable to delete. album with id " + id + " not found");
			 return new ResponseEntity<Void>(HttpStatus.NOT_FOUND); // 404
		}
		service.deleteAlbumById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // 204 http
	}
}