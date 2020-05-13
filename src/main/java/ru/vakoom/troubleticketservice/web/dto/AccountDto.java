package ru.vakoom.troubleticketservice.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountDto {
    private final String username;
    private final String password;
}
