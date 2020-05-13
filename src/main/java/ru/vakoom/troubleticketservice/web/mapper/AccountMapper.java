package ru.vakoom.troubleticketservice.web.mapper;

import org.springframework.stereotype.Component;
import ru.vakoom.troubleticketservice.model.Account;
import ru.vakoom.troubleticketservice.web.dto.AccountDto;

@Component
public class AccountMapper {
    public Account map(AccountDto accountDto) {
        return new Account()
                .setUsername(accountDto.getUsername())
                .setPassword(accountDto.getPassword());
    }
}
