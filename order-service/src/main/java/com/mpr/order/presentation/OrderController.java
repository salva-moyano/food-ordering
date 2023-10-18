package com.mpr.order.presentation;

import com.mpr.order.business.dto.create.CreateOrderCommand;
import com.mpr.order.business.dto.create.CreateOrderResponse;
import com.mpr.order.business.dto.track.TrackOrderQuery;
import com.mpr.order.business.dto.track.TrackOrderResponse;
import com.mpr.order.business.ports.input.service.OrderApplicationService;
import com.mpr.order.business.domain.exception.OrderDomainException;
import com.mpr.order.business.domain.exception.OrderNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static java.util.stream.Collectors.joining;

@Slf4j
@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    @GetMapping("/{trackingId}")
    public ResponseEntity<TrackOrderResponse> getOrderByTrackingId(@PathVariable UUID trackingId) {
        TrackOrderResponse trackOrderResponse =
                orderApplicationService.trackOrder(TrackOrderQuery.builder().orderTrackingId(trackingId).build());
        log.info("Returning order status with tracking id: {}", trackOrderResponse.getOrderTrackingId());
        return  ResponseEntity.ok(trackOrderResponse);
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
        log.info("Creating order for customer: {} at restaurant: {}", createOrderCommand.getCustomerId(),
                 createOrderCommand.getRestaurantId());
        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
        log.info("Order created with tracking id: {}", createOrderResponse.getOrderTrackingId());
        return ResponseEntity.ok(createOrderResponse);
    }


    @ExceptionHandler(value = {OrderDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleException(OrderDomainException orderDomainException) {
        log.error(orderDomainException.getMessage(), orderDomainException);
        return new ErrorMessage(HttpStatus.BAD_REQUEST.getReasonPhrase(), orderDomainException.getMessage());
    }


    @ExceptionHandler(value = {OrderNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleException(OrderNotFoundException orderNotFoundException) {
        log.warn(orderNotFoundException.getMessage(), orderNotFoundException);
        return new ErrorMessage(HttpStatus.BAD_REQUEST.getReasonPhrase(), orderNotFoundException.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorMessage(HttpStatus.BAD_REQUEST.getReasonPhrase(), "Unexpected error!");
    }

    @ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleException(ValidationException validationException) {
       ErrorMessage errorMessage;
       if (validationException instanceof ConstraintViolationException) {
           String violations = extractViolationsFromException((ConstraintViolationException) validationException);
           log.error(violations, validationException);
           errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.getReasonPhrase(), violations);
       } else {
           String exceptionMessage = validationException.getMessage();
           log.error(exceptionMessage, validationException);
           errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.getReasonPhrase(), exceptionMessage);
       }
       return errorMessage;
    }

    private String extractViolationsFromException(ConstraintViolationException validationException) {
        return validationException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(joining("--"));
    }

    public record ErrorMessage (String codce, String message) {}
}
