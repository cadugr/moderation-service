package com.algacomments.moderation.service.domain.model;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
public class ProhibitedWords {

    private final List<String> prohibitedWords = new ArrayList<String>();

    @PostConstruct
    public void init() {
        prohibitedWords.add("ódio");
        prohibitedWords.add("xingamento");
    }

    public List<String> getProhibitedWords(String text) {
        String textLower = text.toLowerCase();
        return prohibitedWords.stream()
                .filter(ph -> textLower.contains(ph.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean containAny(String text) {
        return !getProhibitedWords(text).isEmpty();
    }

}
