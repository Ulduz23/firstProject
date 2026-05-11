package com.abbtech.payment.repository;

import com.abbtech.payment.client.notifications.RequestNotificationDto;
import com.abbtech.payment.client.notifications.ResponseNotificationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {

    private final RestClient restClient;

    public NotificationRepositoryImpl(
            RestClient.Builder restClientBuilder,
            @Value("${clients.notification.base-url}") String notificationBaseUrl) {
        this.restClient = restClientBuilder.baseUrl(notificationBaseUrl).build();
    }

    @Override
    public ResponseNotificationDto sendNotification(RequestNotificationDto notificationRequestBody) {
        return restClient.post()
                .body(notificationRequestBody)
                .retrieve()
                .body(ResponseNotificationDto.class);
    }
}
