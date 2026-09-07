package com.minimarket.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080") // Ganti dengan URL frontend Anda
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        // Sajikan gambar upload dari APP_UPLOAD_DIR (-production: /tmp/uploads)
        // agar URL /images/products/** tetap hidup walau bukan di classpath.
        String dir = System.getenv().getOrDefault(
                "APP_UPLOAD_DIR", "src/main/resources/static/images/products/");
        if (!dir.endsWith("/") && !dir.endsWith("\\")) {
            dir += "/";
        }
        registry.addResourceHandler("/images/products/**")
                .addResourceLocations("file:" + dir, "classpath:/static/images/products/");
    }
}
