package com.ajou.procoding.reactspringweb.repository;

import com.ajou.procoding.reactspringweb.entity.FavoriteMusic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<FavoriteMusic, String> {
    List<FavoriteMusic> findAll();
    void deleteById(String id);
}