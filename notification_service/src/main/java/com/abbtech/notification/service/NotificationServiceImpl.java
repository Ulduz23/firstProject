package com.abbtech.notification.service;

import com.abbtech.notification.dto.RequestNotificationDto;
import com.abbtech.notification.dto.RequestNotificationStatusDto;
import com.abbtech.notification.dto.ResponseNotificationDto;
import com.abbtech.notification.model.Notification;
import com.abbtech.notification.model.enums.NotificationStatus;
import com.abbtech.notification.repository.NotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public ResponseNotificationDto create(RequestNotificationDto request) {
        Notification notification = new Notification();
        notification.setMessage(request.message());
        notification.setStatus(request.status() == null ? NotificationStatus.NEW : request.status());
        notification.setUserId(String.valueOf(request.userId()));

        return mapToResponse(notificationRepository.save(notification));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseNotificationDto getById(Long id) {
        return mapToResponse(findNotificationByIdOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseNotificationDto> getByUserId(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(String.valueOf(userId))
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public ResponseNotificationDto updateStatus(Long id, RequestNotificationStatusDto request) {
        Notification notification = findNotificationByIdOrThrow(id);
        notification.setStatus(request.status());

        if (request.status() == NotificationStatus.READ && notification.getReadAt() == null) {
            notification.setReadAt(LocalDateTime.now());
        }

        return mapToResponse(notificationRepository.save(notification));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        notificationRepository.delete(findNotificationByIdOrThrow(id));
    }

    private ResponseNotificationDto mapToResponse(Notification notification) {
        return new ResponseNotificationDto(
                notification.getId(),
                Long.valueOf(notification.getUserId()),
                notification.getUserId(),
                notification.getMessage(),
                notification.getStatus(),
                notification.getCreatedAt(),
                notification.getUpdatedAt(),
                notification.getReadAt()
        );
    }

    private Notification findNotificationByIdOrThrow(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found: " + id));
    }
}
