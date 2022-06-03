package com.ajou.procoding.reactspringweb.service;

import com.ajou.procoding.reactspringweb.dto.FavoriteMusicRequestDto;
import com.ajou.procoding.reactspringweb.dto.MusicList;
import com.ajou.procoding.reactspringweb.entity.FavoriteMusic;
import com.ajou.procoding.reactspringweb.repository.FavoriteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicService {
    private final FavoriteRepository albumsRepo;
    RestTemplate restTemplate = new RestTemplate();

    public MusicList searchMusic(String term) {

        try {
            String response = restTemplate.getForObject("https://itunes.apple.com/search?term="+term+"&entity=song", String.class);
            ObjectMapper mapper = new ObjectMapper();
            MusicList list = mapper.readValue(response, MusicList.class);
            System.out.println(list.getResultCount());
            return list;
        } catch(IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public MusicList searchMusicVideo(String term) {

        try {
            String response = restTemplate.getForObject("https://itunes.apple.com/search?term="+term+"&entity=musicVideo&limit=10", String.class);
            ObjectMapper mapper = new ObjectMapper();
            MusicList list = mapper.readValue(response, MusicList.class);
            System.out.println(list.getResultCount());
            return list;
        } catch(IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public String searchTopMusic() {

        String response = restTemplate.getForObject("https://itunes.apple.com/us/rss/topsongs/limit=50/json", String.class);
        JsonElement element = JsonParser.parseString(response);
        JsonObject object = element.getAsJsonObject();
        JsonArray entryJsonArray = object.get("feed").getAsJsonObject().get("entry").getAsJsonArray();
        return entryJsonArray.toString();
    }

    public String recommendMusic() {

        String response = restTemplate.getForObject("https://itunes.apple.com/us/rss/topsongs/limit=50/json", String.class);
        JsonElement element = JsonParser.parseString(response);
        JsonObject object = element.getAsJsonObject();
        JsonArray entryJsonArray = object.get("feed").getAsJsonObject().get("entry").getAsJsonArray();
        JsonArray randomJsonArray = new JsonArray();
        while(randomJsonArray.size() < 5){
            double randomValue = Math.random();
            int intValue = (int)(randomValue * 50);
            if (randomJsonArray.contains(entryJsonArray.get(intValue))){
                continue;
            }else{
                randomJsonArray.add(entryJsonArray.get(intValue));
            }
        }
        return randomJsonArray.toString();
    }
    public List<FavoriteMusic> getLikes() {

        try {
            return albumsRepo.findAll();
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public int saveFavorite(FavoriteMusicRequestDto favorite) {
        FavoriteMusic music = albumsRepo.save(favorite.toEntity());
        System.out.println(music);
        if(music != null) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public int deleteFavorite(String id) {
        try{
            albumsRepo.deleteById(id);
            return 1;
        }
        catch (EmptyResultDataAccessException e){
            System.out.println(e.toString());
            return 0;
        }
    }
}