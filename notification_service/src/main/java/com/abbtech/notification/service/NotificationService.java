package com.abbtech.notification.service;

import com.abbtech.notification.dto.RequestNotificationDto;
import com.abbtech.notification.dto.RequestNotificationStatusDto;
import com.abbtech.notification.dto.ResponseNotificationDto;

import java.util.List;

public interface NotificationService {

    ResponseNotificationDto create(RequestNotificationDto request);

    ResponseNotificationDto getById(Long id);

    List<ResponseNotificationDto> getByUserId(Long userId);

    ResponseNotificationDto updateStatus(Long id, RequestNotificationStatusDto request);

    void delete(Long id);
}
