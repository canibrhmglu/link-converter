package com.trendyol.linkconverter.repository;

import com.trendyol.linkconverter.entity.UrlMap;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlMapRepository extends CrudRepository<UrlMap, Long>  {

    Optional<UrlMap> findByRequest(String request);
}
