package ru.vakoom.troubleticketservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vakoom.troubleticketservice.exception.AccountAlreadyExistsException;
import ru.vakoom.troubleticketservice.model.Account;
import ru.vakoom.troubleticketservice.repo.AccountRepository;
import ru.vakoom.troubleticketservice.web.dto.AccountDto;
import ru.vakoom.troubleticketservice.web.mapper.AccountMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public Optional<Account> findUserByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Account createAccount(AccountDto accountDto) {
        if (findUserByUsername(accountDto.getUsername()).isPresent()) {
            throw new AccountAlreadyExistsException("Try another username");
        }
        return accountRepository.save(accountMapper.map(accountDto));
    }
}
