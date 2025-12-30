package com.back.boundedContext.payout.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "PAYOUT_PAYOUT_ITEM")
@NoArgsConstructor
@AllArgsConstructor
public class PayoutItem extends BaseIdAndTime {
    @ManyToOne(fetch = LAZY)
    private Payout payout;
    @Enumerated(EnumType.STRING)
    private PayoutEventType eventType;
    String relTypeCode;
    private int relId;
    private LocalDateTime paymentDate;
    @ManyToOne(fetch = LAZY)
    private PayoutMember payer;
    @ManyToOne(fetch = LAZY)
    private PayoutMember payee;
    private long amount;
}
