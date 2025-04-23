package com.algacomments.moderation.service.api.controller;

import com.algacomments.moderation.service.api.model.ModerationInput;
import com.algacomments.moderation.service.api.model.ModerationOutput;
import com.algacomments.moderation.service.domain.service.ModerationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/api/moderate")
@RequiredArgsConstructor
@Slf4j
public class ModerationController {

    private final ModerationService moderationService;

    @PostMapping
    public ModerationOutput moderate(@RequestBody ModerationInput moderationInput) {
        log.info("Moderation request received with id: {} at {}", moderationInput.getCommentId(),
                OffsetDateTime.now());
        return moderationService.moderate(moderationInput);
    }

}
