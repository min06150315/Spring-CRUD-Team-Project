package org.example.springteamproject.dao;

import org.example.springteamproject.mapper.URLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class URLDAO {
    @Autowired
    private URLMapper urlMapper;

    public String getURL(String lang) {
        return urlMapper.getURL(lang);
    }
}
