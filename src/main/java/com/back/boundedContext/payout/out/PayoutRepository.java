package com.back.boundedContext.payout.out;

import com.back.boundedContext.payout.domain.Payout;
import com.back.boundedContext.payout.domain.PayoutMember;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PayoutRepository extends JpaRepository<Payout, Integer> {
    Optional<Payout> findByPayeeAndPayoutDateIsNull(PayoutMember payee);

    // 아직 지급 안 된(payoutDate = null), 금액이 amount보다 큰 데이터 중에서
    // id 오름차순으로 limit개만 가져오는 쿼리
    List<Payout> findByPayoutDateIsNullAndAmountGreaterThanOrderByIdAsc(long amount, Pageable pageable);
}
