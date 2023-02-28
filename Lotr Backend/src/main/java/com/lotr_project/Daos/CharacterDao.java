package com.lotr_project.Daos;

import com.lotr_project.Models.Character;

import java.util.List;

public interface CharacterDao {

    //Read all characters
    List<Character> getAllCharacters();

    //Read character by character_id
    Character getCharacterByCharacterId(int id);

    //Create character
    Character addNewCharacter(Character character);

    //Delete character
    void deleteCharacter(int id);
}
