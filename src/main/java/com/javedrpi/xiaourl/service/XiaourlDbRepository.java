package com.javedrpi.xiaourl.service;

import com.javedrpi.xiaourl.model.Urls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface XiaourlDbRepository extends JpaRepository<Urls, Long> {
    Urls findByShortenUrl(String shortUrl);
}
