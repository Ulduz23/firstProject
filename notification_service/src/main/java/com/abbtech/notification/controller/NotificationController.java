package com.abbtech.notification.controller;

import com.abbtech.notification.dto.RequestNotificationDto;
import com.abbtech.notification.dto.RequestNotificationStatusDto;
import com.abbtech.notification.dto.ResponseNotificationDto;
import com.abbtech.notification.service.NotificationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
@Validated
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseNotificationDto create(@RequestBody @Valid RequestNotificationDto request) {
        return notificationService.create(request);
    }

    @GetMapping("/{id}")
    public ResponseNotificationDto getById(@PathVariable @Positive Long id) {
        return notificationService.getById(id);
    }

    @GetMapping("/users/{userId}")
    public List<ResponseNotificationDto> getByUserId(@PathVariable @Positive Long userId) {
        return notificationService.getByUserId(userId);
    }

    @PatchMapping("/{id}/status")
    public ResponseNotificationDto updateStatus(
            @PathVariable @Positive Long id,
            @RequestBody @Valid RequestNotificationStatusDto request) {
        return notificationService.updateStatus(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive Long id) {
        notificationService.delete(id);
    }
}
