package com.lotr_project.Daos;

import com.lotr_project.Models.Character;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCharacterDao implements CharacterDao {

    JdbcTemplate jdbcTemplate;

    public JdbcCharacterDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Character> getAllCharacters() {
        String sql = "SELECT character_id, the_one_character_id, name, race, wiki_url " +
                    "FROM character;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Character> characterList = new ArrayList<>();
        while(results.next()){
            Character character = mapRowToCharacter(results);
            characterList.add(character);
        }
        return characterList;
    }

    @Override
    public Character getCharacterByCharacterId(int id) {
        String sql = "SELECT character_id, the_one_character_id, name, race, wiki_url " +
                "FROM character " +
                "WHERE character_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if(results.next()){
            return mapRowToCharacter(results);
        }
        return null;
    }

    @Override
    public Character addNewCharacter(Character character) {
        String sql = "INSERT INTO character (the_one_character_id, name, race, wiki_url) " +
                    "VALUES (?, ?, ?, ?) RETURNING character_id;";
        Integer characterId = jdbcTemplate.queryForObject(sql, Integer.class, character.getTheOneCharacterId(), character.getName(),
                            character.getRace(), character.getWikiUrl());
        return getCharacterByCharacterId(characterId);
    }

    @Override
    public void deleteCharacter(int id) {
        String sql = "DELETE FROM character WHERE character_id = ?;";
        jdbcTemplate.update(sql, id);
    }

    private Character mapRowToCharacter(SqlRowSet rs) {
        Character character = new Character();
        character.setCharacterId(rs.getInt("character_id"));
        character.setTheOneCharacterId(rs.getString("the_one_character_id"));
        character.setName(rs.getString("name"));
        character.setRace(rs.getString("race"));
        character.setWikiUrl(rs.getString("wiki_url"));
        return character;
    }
}
