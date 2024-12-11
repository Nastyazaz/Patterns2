package ru.netology.data;

import lombok.Data;

@Data
public class Registrationinfo {
    private final String login;
    private final String password;
    private final String status;
}
