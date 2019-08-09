package com.infoshareacademy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

@JsonIgnoreProperties(value = {"full_sort_key", "url", "cover_color", "cover", "epoch", "href", "simple_thumb", "slug", "cover_thumb", "liked"})
class Parser {
    public void parseFileToObjects() {
        ObjectMapper mapper = new ObjectMapper ( );
        List<Book> books = null;
        try {
            books = mapper.readValue (new File (getClass ( ).getClassLoader ( ).getResource ("books.json").getFile ( )), new TypeReference<List<Book>> ( ) {
            });
        } catch (IOException e) {
            System.out.println ("Problem with IO occurred");
        }
        System.out.println (books);
    }
}