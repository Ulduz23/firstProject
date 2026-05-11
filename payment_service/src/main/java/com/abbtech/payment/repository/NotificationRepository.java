package com.abbtech.payment.repository;

import com.abbtech.payment.client.notifications.RequestNotificationDto;
import com.abbtech.payment.client.notifications.ResponseNotificationDto;

public interface NotificationRepository {

    ResponseNotificationDto sendNotification(RequestNotificationDto notificationRequestBody);
}
