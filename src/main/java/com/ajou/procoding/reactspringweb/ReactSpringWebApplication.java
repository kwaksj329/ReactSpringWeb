package com.ajou.procoding.reactspringweb;

import com.ajou.procoding.reactspringweb.dto.FavoriteMusicRequestDto;
import com.ajou.procoding.reactspringweb.dto.MusicList;
import com.ajou.procoding.reactspringweb.entity.FavoriteMusic;
import com.ajou.procoding.reactspringweb.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class ReactSpringWebApplication {
	@Autowired
	MusicService service;

	public static void main(String[] args) {
		SpringApplication.run(ReactSpringWebApplication.class, args);
	}
	@GetMapping(value = "/musicSearch/{name}")
	public MusicList musicSearchByPath(@PathVariable String name){
		return service.searchMusic(name);
	}

	@GetMapping(value="musicSearch")
	public MusicList musicSearchByParam(@RequestParam(value="term") String name) {
		return service.searchMusic(name);
	}

	@GetMapping(value="/likes")  //Get Favorite Music list from Database
	public List<FavoriteMusic> getLikes() {
		return service.getLikes();
	}

	@PostMapping(value="/likes")
	public int postLikes(@RequestBody FavoriteMusicRequestDto favorite) {
		return service.saveFavorite(favorite);
	}

	@DeleteMapping(value="/likes/{id}")
	public int deleteLikes(@PathVariable String id){ return service.deleteFavorite(id);}

	@GetMapping(value = "/musicVideoSearch/{name}")
	public MusicList musicVideoSearchByPath(@PathVariable String name){
		return service.searchMusicVideo(name);
	}

	@GetMapping(value="musicVideoSearch")
	public MusicList musicVideoSearchByParam(@RequestParam(value="term") String name) {
		return service.searchMusicVideo(name);
	}

	@GetMapping(value = "/topList")
	public String topMusicSearchByPath(){
		return service.searchTopMusic();
	}

	@GetMapping(value = "/recommendMusic")
	public String recommendMusicByPath(){
		return service.recommendMusic();
	}
}
