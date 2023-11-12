package com.turkcell.orderservice.business.concretes;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;

import java.time.Duration;

import com.turkcell.orderservice.business.abstracts.OrderService;
import com.turkcell.orderservice.entities.Order;
import com.turkcell.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderManager implements OrderService  {

    private final OrderRepository orderRepository;
    private CircuitBreaker circuitBreaker;

    @Override
    public List<Order> getAll() {
        return orderRepository.getForListing();
    }

        private void simulateServiceCall() {
            // Servis çağrısını temsil eden bir metot
            // Bu örnekte sadece hata fırlatıyormuş gibi düşünelim
            throw new RuntimeException("Servis çağrısı başarısız oldu");
        }

        public void simulateTransitionToOpenState() {
            // Circuit Breaker'ı manuel olarak açık duruma geçirme simülasyonu
            circuitBreaker.transitionToOpenState();
        }

        public void simulateTransitionToHalfOpenState() {
            // Circuit Breaker'ı manuel olarak yarı açık duruma geçirme simülasyonu
            circuitBreaker.transitionToHalfOpenState();
        }

        public void simulateTransitionToClosedState() {
            // Circuit Breaker'ı manuel olarak kapalı duruma geçirme simülasyonu
            circuitBreaker.transitionToClosedState();
        }
    }





