package com.back.boundedContext.payout.app;

import com.back.shared.market.dto.OrderDto;
import com.back.shared.market.dto.OrderItemDto;
import com.back.shared.market.out.MarketApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PayoutCandidateItemsUseCase {
    private final MarketApiClient marketApiClient;

    public void addPayoutCandidateItems(OrderDto order) {
        log.debug("addPayoutCandidateItems.order: {}", order.getId());
        List<OrderItemDto> items = marketApiClient.getOrderItems(order.getId());

        items.forEach(item -> {
            log.debug("orderItem.id : {}", item.getId());
        });
    }
}
