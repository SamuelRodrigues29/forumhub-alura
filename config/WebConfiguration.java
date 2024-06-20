package config;

public class WebConfiguration {
    @Configuration
    @EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
    public class WebConfig {

    }
}
