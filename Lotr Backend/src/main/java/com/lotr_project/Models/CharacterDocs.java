package com.lotr_project.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CharacterDocs {

    @JsonProperty("docs")
    private Character[] characters;

    public Character[] getCharacters() {
        return characters;
    }

    public void setCharacters(Character[] characters) {
        this.characters = characters;
    }
}
