package com.lotr_project.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Character {

    //attributes
    private int characterId;
    @JsonProperty("_id")
    private String theOneCharacterId;
    private String name;
    private String race;
    private String wikiUrl;

    //getters and setters
    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getTheOneCharacterId() {
        return theOneCharacterId;
    }

    public void setTheOneCharacterId(String theOneCharacterId) {
        this.theOneCharacterId = theOneCharacterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    //toString
    @Override
    public String toString() {
        return "Character{" +
                "characterId=" + characterId +
                ", theOneCharacterId='" + theOneCharacterId + '\'' +
                ", name='" + name + '\'' +
                ", race='" + race + '\'' +
                ", wikiUrl='" + wikiUrl + '\'' +
                '}';
    }
}
