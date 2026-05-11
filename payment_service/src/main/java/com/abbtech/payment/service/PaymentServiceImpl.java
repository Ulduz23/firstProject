package com.abbtech.payment.service;

import com.abbtech.payment.client.notifications.RequestNotificationDto;
import com.abbtech.payment.client.notifications.enums.NotificationStatus;
import com.abbtech.payment.common.exception.MarketPlaceErrorEnum;
import com.abbtech.payment.common.exception.MarketPlaceException;
import com.abbtech.payment.dto.RequestPaymentDto;
import com.abbtech.payment.dto.RequestPaymentStatusDto;
import com.abbtech.payment.dto.ResponsePaymentDto;
import com.abbtech.payment.model.Payment;
import com.abbtech.payment.model.enums.PaymentStatus;
import com.abbtech.payment.repository.NotificationRepository;
import com.abbtech.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public ResponsePaymentDto create(RequestPaymentDto request) {
        Payment payment = new Payment();
        payment.setAmount(request.amount());
        payment.setMethod(request.method());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setUserId(String.valueOf(request.userId()));

        Payment savedPayment = paymentRepository.save(payment);
        sendPaymentNotification(request);

        return mapToResponse(savedPayment);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponsePaymentDto getById(Long id) {
        return mapToResponse(findPaymentByIdOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponsePaymentDto> getByUserId(Long userId) {
        return paymentRepository.findByUserIdOrderByCreatedAtDesc(String.valueOf(userId))
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public ResponsePaymentDto updateStatus(Long id, RequestPaymentStatusDto request) {
        Payment payment = findPaymentByIdOrThrow(id);
        payment.setStatus(request.status());
        return mapToResponse(paymentRepository.save(payment));
    }

    private void sendPaymentNotification(RequestPaymentDto request) {
        try {
            var responseNotificationDto = notificationRepository.sendNotification(RequestNotificationDto.builder()
                    .message("Your payment is success")
                    .status(NotificationStatus.NEW)
                    .userId(String.valueOf(request.userId()))
                    .build());
            log.info("Notification sent for payment. notificationId={}", responseNotificationDto.id());
        } catch (RuntimeException exception) {
            log.warn("Notification service request failed: {}", exception.getMessage());
        }
    }

    private ResponsePaymentDto mapToResponse(Payment payment) {
        return new ResponsePaymentDto(
                payment.getId(),
                Long.valueOf(payment.getUserId()),
                payment.getUserId(),
                payment.getAmount(),
                payment.getMethod(),
                payment.getStatus(),
                payment.getCreatedAt(),
                payment.getUpdatedAt()
        );
    }

    private Payment findPaymentByIdOrThrow(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new MarketPlaceException(MarketPlaceErrorEnum.PAYMENT_NOT_FOUND));
    }
}
