package config;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BrowserConfig {

    private String name;
    private String headless;
}
