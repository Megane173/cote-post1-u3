package com.universidad.confudes.certificados;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class GestionCertificadosFacadeConfiguracion {
    
    @Bean
    public ValidadorAsistencia validadorAsistencia(){
        return new ValidadorAsistencia();
    }

    @Bean
    public GeneradorCertificadoPDF generadorCertificadoPDF(){
        return new GeneradorCertificadoPDF();
    }

    @Bean
    public FirmaDigitalService firmaDigitalService() {
        return new FirmaDigitalService();
    }

    @Bean
    public EnvioCorreoService envioCorreoService() {
        return new EnvioCorreoService();
    }
}
