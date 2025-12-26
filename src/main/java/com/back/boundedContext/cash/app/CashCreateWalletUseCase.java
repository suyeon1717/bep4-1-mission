package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.boundedContext.cash.out.WalletRepository;
import com.back.shared.cash.dto.CashMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashCreateWalletUseCase {
    private final WalletRepository walletRepository;
    private final CashMemberRepository cashMemberRepository;

    public Wallet createWallet(CashMemberDto member) {
        // getReferenceById : Id값만 들어있는 프록시 반환, 실제 DB 쿼리 x
        CashMember cashMember = cashMemberRepository.getReferenceById(member.getId());
        Wallet wallet = new Wallet(cashMember);

        return walletRepository.save(wallet);
    }
}
