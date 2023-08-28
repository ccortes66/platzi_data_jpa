package com.platzi.pizzeria.persistence.Auditory;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditUsername implements AuditorAware<String>
{
    @Override
    public Optional<String> getCurrentAuditor()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.of(authentication.getPrincipal().toString());
    }
}
