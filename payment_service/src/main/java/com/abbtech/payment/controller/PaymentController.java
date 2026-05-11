package com.abbtech.payment.controller;

import com.abbtech.payment.dto.RequestPaymentDto;
import com.abbtech.payment.dto.RequestPaymentStatusDto;
import com.abbtech.payment.dto.ResponsePaymentDto;
import com.abbtech.payment.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/payments")
@RequiredArgsConstructor
@Validated
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponsePaymentDto create(@Valid @RequestBody RequestPaymentDto request) {
        return paymentService.create(request);
    }

    @GetMapping("/{id}")
    public ResponsePaymentDto getById(@PathVariable @Positive Long id) {
        return paymentService.getById(id);
    }

    @GetMapping("/users/{userId}")
    public List<ResponsePaymentDto> getByUserId(@PathVariable @Positive Long userId) {
        return paymentService.getByUserId(userId);
    }

    @PatchMapping("/{id}/status")
    public ResponsePaymentDto updateStatus(
            @PathVariable @Positive Long id,
            @Valid @RequestBody RequestPaymentStatusDto request) {
        return paymentService.updateStatus(id, request);
    }
}
