package com.ajou.procoding.reactspringweb.dto;

import com.ajou.procoding.reactspringweb.entity.FavoriteMusic;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FavoriteMusicRequestDto {
    private String trackId;
    private String trackName;
    private String artistName;
    private String artworkUrl100;
    private String previewUrl;
    public FavoriteMusic toEntity() {
        FavoriteMusic music = new FavoriteMusic();
        music.setTrackId(this.trackId);
        music.setTrackName(this.trackName);
        music.setArtistName(this.artistName);
        music.setArtworkUrl100(this.artworkUrl100);
        music.setPreviewUrl(this.previewUrl);
        return music;
    }
}