package ru.rrk.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ViolationsException {
    private final String fieldName;
    private final String message;
}
