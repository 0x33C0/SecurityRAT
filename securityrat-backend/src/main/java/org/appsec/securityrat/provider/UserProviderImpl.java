package org.appsec.securityrat.provider;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import lombok.Getter;
import org.appsec.securityrat.api.UserProvider;
import org.appsec.securityrat.api.dto.Authority;
import org.appsec.securityrat.api.dto.PersistentToken;
import org.appsec.securityrat.api.dto.User;
import org.appsec.securityrat.api.exception.ApiException;
import org.appsec.securityrat.api.exception.EmailAlreadyInUseException;
import org.appsec.securityrat.api.exception.UsernameTakenException;
import org.appsec.securityrat.repository.UserRepository;
import org.appsec.securityrat.repository.search.UserSearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserProviderImpl
        extends AbstractProviderImplementation<
            Long,
            org.appsec.securityrat.api.dto.User,
            org.appsec.securityrat.domain.User>
        implements UserProvider {
    
    // NOTE: Due to the reason that the frontend will directly display the date
    //       that is formatted with the DateTimeFormatter bellow, we should
    //       always use ENGLISH as the locale because otherwise the translation
    //       of the month's name will depend on the server's language
    //       configuration (which is not really that nice).
    
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
    
    @Getter
    @Inject
    private UserRepository repository;
    
    @Getter
    @Inject
    private UserSearchRepository searchRepository;

    @Override
    protected org.appsec.securityrat.domain.User createOrUpdateEntity(
            User dto,
            org.appsec.securityrat.domain.User target) throws ApiException {
        if (target == null) {
            target = new org.appsec.securityrat.domain.User();
        }
        
        // Checking, if the username or email is already in use (only if the
        // updated information differ from the persistent one)
        
        if (!Objects.equals(target.getEmail(), dto.getEmail()) &&
                this.repository.findOneByEmail(dto.getEmail()).isPresent()) {
            throw new EmailAlreadyInUseException();
        }
        
        if (!Objects.equals(target.getLogin(), dto.getLogin()) &&
                this.repository.findOneByLogin(dto.getLogin()).isPresent()) {
            throw new UsernameTakenException();
        }
        
        // Updating the information
        
        target.setLogin(dto.getLogin());
        target.setFirstName(dto.getFirstName());
        target.setLastName(dto.getLastName());
        target.setEmail(dto.getEmail());
        target.setActivated(dto.isActivated());
        target.setLangKey(dto.getLangKey());
        target.setResetKey(dto.getResetKey());
        target.setResetDate(dto.getResetDate());
        target.setAuthorities(dto.getAuthorities()
                .stream()
                .map(e -> {
                    org.appsec.securityrat.domain.Authority authority =
                            new org.appsec.securityrat.domain.Authority();                    
                    
                    authority.setName(e.getName());
                    
                    return authority;
                })
                .collect(Collectors.toSet()));
        
        // NOTE: We do not support updating the persistent tokens.
        
        return target;
    }

    @Override
    protected Long getId(org.appsec.securityrat.domain.User entity) {
        return entity.getId();
    }

    @Override
    protected User createDto(org.appsec.securityrat.domain.User entity) {
        User dto = new User();
        
        dto.setId(entity.getId());
        dto.setLogin(entity.getLogin());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setActivated(entity.getActivated());
        dto.setLangKey(entity.getLangKey());
        dto.setResetKey(entity.getResetKey());
        dto.setResetDate(entity.getResetDate());
        dto.setAuthorities(entity.getAuthorities()
                .stream()
                .map(e -> {
                    Authority authority = new Authority();
                    authority.setName(e.getName());
                    return authority;
                })
                .collect(Collectors.toSet()));
        
        dto.setPersistentTokens(entity.getPersistentTokens()
                .stream()
                .map(e -> {
                    PersistentToken token = new PersistentToken();
                    
                    token.setFormattedTokenDate(e.getTokenDate().format(
                            UserProviderImpl.FORMAT));
                    
                    token.setIpAddress(e.getIpAddress());
                    token.setSeries(e.getSeries());
                    token.setUserAgent(e.getUserAgent());
                    
                    return token;
                })
                .collect(Collectors.toSet()));
        
        return dto;
    }

    @Override
    @Transactional
    public Optional<User> findByLogin(String login) {
        return this.repository.findOneByLogin(login)
                .map(this::createDto);
    }
}