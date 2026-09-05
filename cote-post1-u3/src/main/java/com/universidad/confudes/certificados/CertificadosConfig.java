package com.universidad.confudes.certificados;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CertificadosConfig {

    @Bean
    @Primary
    public ServicioCertificados servicioCertificadosProtegido(
            GestionCertificadosFacade facade) {

        return new SolicitudCertificadoProxy(facade);
    }
}
