package com.abbtech.payment.service;

import com.abbtech.payment.dto.RequestPaymentDto;
import com.abbtech.payment.dto.RequestPaymentStatusDto;
import com.abbtech.payment.dto.ResponsePaymentDto;

import java.util.List;

public interface PaymentService {

    ResponsePaymentDto create(RequestPaymentDto request);

    ResponsePaymentDto getById(Long id);

    List<ResponsePaymentDto> getByUserId(Long userId);

    ResponsePaymentDto updateStatus(Long id, RequestPaymentStatusDto request);
}
