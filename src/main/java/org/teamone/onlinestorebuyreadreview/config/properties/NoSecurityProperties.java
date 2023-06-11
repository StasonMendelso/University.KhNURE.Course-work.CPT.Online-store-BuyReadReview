package org.teamone.onlinestorebuyreadreview.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Stanislav Hlova
 */
@ConfigurationProperties(prefix = "app.security")
@Data
public class NoSecurityProperties {
    @Value("${app.security.authority}")
    private final String authority;
}
