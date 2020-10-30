package com.javedrpi.xiaourl.repository;

import com.javedrpi.xiaourl.model.Urls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XiaourlDbRepository extends JpaRepository<Urls, Long> {
    Urls findByShortenUrl(String shortUrl);
}
