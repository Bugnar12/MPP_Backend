package org.backendspring_boot.backendspring_boot.service;

import java.time.Instant;

public record LoginRequest(String username, String password, Instant loginTime) {
}
