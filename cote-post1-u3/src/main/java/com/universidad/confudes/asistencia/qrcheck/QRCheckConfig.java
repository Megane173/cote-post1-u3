package com.universidad.confudes.asistencia.qrcheck;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.universidad.confudes.externo.qrcheck.QRCheckClient;

@Configuration
public class QRCheckConfig {

    @Bean
    public QRCheckClient qrCheckClient() {
        return new QRCheckClient();
    }
}