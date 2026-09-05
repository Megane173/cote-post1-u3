package com.universidad.confudes.certificados;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

import com.universidad.confudes.certificados.DecoratorsCertificados.CodigoQRDecorator;
import com.universidad.confudes.certificados.DecoratorsCertificados.MarcaDeAguaDecorator;
import com.universidad.confudes.certificados.DecoratorsCertificados.TraduccionAInglesDecorator;

class MejorasCertificadoTest {

    private final SolicitudCertificado solicitud =
        new SolicitudCertificado("EVT-001", "PART-123", "Ana Ríos", "ana@correo.com");

    @Test
    void emiteSinNingunaMejoraActivada() {
        ServicioCertificados base = new GestionCertificadosFacade(
        new ValidadorAsistencia(),
        new GeneradorCertificadoPDF(),
        new FirmaDigitalService(),
        new EnvioCorreoService()
        );
        assertDoesNotThrow(() -> base.emitir(solicitud));
    }

    @Test
    void combinaLasTresMejorasSinCrearUnaClaseNueva() {
        ServicioCertificados conTodo = new MarcaDeAguaDecorator( 
            new CodigoQRDecorator(
                new TraduccionAInglesDecorator(new GestionCertificadosFacade(
                        new ValidadorAsistencia(),
                        new GeneradorCertificadoPDF(),
                        new FirmaDigitalService(),
                        new EnvioCorreoService()
                        )
                    )
            ), 
            "CONFUDES");
        assertDoesNotThrow(() -> conTodo.emitir(solicitud));
    }

    @Test
    void unaSolaMejoraFuncionaDeFormaIndependiente() {
        ServicioCertificados soloMarcaDeAgua = new MarcaDeAguaDecorator(
            new GestionCertificadosFacade(
                new ValidadorAsistencia(),
                new GeneradorCertificadoPDF(),
                new FirmaDigitalService(),
                new EnvioCorreoService()
            ), "CONFUDES");
        assertDoesNotThrow(() -> soloMarcaDeAgua.emitir(solicitud));
    }
}