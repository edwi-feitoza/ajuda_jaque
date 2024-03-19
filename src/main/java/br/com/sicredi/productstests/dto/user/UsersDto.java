package br.com.sicredi.productstests.dto.user;

import java.util.List;

public record UsersDto(
        List<UserDto> users;
) {}
