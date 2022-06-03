package com.ajou.procoding.reactspringweb.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "favoriteMusic")
@Getter
@Setter
@ToString
public class FavoriteMusic {
    @Id
    @Column(length=32) private String trackId;
    @Column private String trackName;
    @Column private String artistName;
    @Column private String artworkUrl100;
    @Column private String previewUrl;
}