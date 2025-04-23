package com.algacomments.moderation.service.domain.service;

import com.algacomments.moderation.service.api.model.ModerationInput;
import com.algacomments.moderation.service.api.model.ModerationOutput;
import com.algacomments.moderation.service.domain.model.ProhibitedWords;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModerationService {

    private final ProhibitedWords prohibitedWords;

    public ModerationOutput moderate(ModerationInput moderationInput) {
        boolean containProhibitedWords = prohibitedWords.containAny(moderationInput.getText());
        if (containProhibitedWords) {
            String reason = "the text contains the following prohibited words: " + prohibitedWords
                    .getProhibitedWords(moderationInput.getText()).stream().collect(Collectors.joining(" | "));
            log.warn("The comment with id {} has been moderated and it`s prohibited, because {}.", moderationInput.getCommentId(), reason);
            return ModerationOutput.builder()
                    .approved(false)
                    .reason(reason)
                    .build();
        } else {
            String reason = "no prohibited words found.";
            log.info("The comment with id {} has been moderated and it`s ok, because has {}",
                    moderationInput.getCommentId(), reason);
            return ModerationOutput.builder()
                    .approved(true)
                    .reason(reason)
                    .build();
        }

    }
}
