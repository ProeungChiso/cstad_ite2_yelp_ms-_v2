package kh.edu.cstad.gateway.dto;

public record UserProfile(
        String username,
        String jti,
        String sub
) {
}
