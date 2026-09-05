package com.universidad.confudes.acceso;

import com.universidad.confudes.certificados.EnvioCorreoService;
import com.universidad.confudes.certificados.FirmaDigitalService;
import com.universidad.confudes.certificados.GeneradorCertificadoPDF;
import com.universidad.confudes.certificados.GestionCertificadosFacade;
import com.universidad.confudes.certificados.ServicioCertificados;
import com.universidad.confudes.certificados.SolicitudCertificado;
import com.universidad.confudes.certificados.SolicitudCertificadoProxy;
import com.universidad.confudes.certificados.ValidadorAsistencia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

class AccesoDescargaMasivaTest {

    @AfterEach
    void limpiarRol() {
        System.clearProperty("confudes.rol");
    }

    @Test
    void rechazaAParticipanteSinLlegarAEmitir() {
        System.setProperty("confudes.rol", "PARTICIPANTE");

        ServicioCertificados base = new GestionCertificadosFacade(
            new ValidadorAsistencia(),
            new GeneradorCertificadoPDF(),
            new FirmaDigitalService(),
            new EnvioCorreoService()
        );
        ServicioCertificados controlado = new SolicitudCertificadoProxy(base);
        SolicitudCertificado solicitud = new SolicitudCertificado("EVT-001", "PART-123", "Ana", "ana@correo.com");
        assertThrows(SecurityException.class, () -> controlado.emitir(solicitud));
    }

    @Test
    void permiteAOrganizador() {
        System.setProperty("confudes.rol", "ORGANIZADOR");
        ServicioCertificados base = new GestionCertificadosFacade(
            new ValidadorAsistencia(),
            new GeneradorCertificadoPDF(),
            new FirmaDigitalService(),
            new EnvioCorreoService()
        );
        ServicioCertificados controlado = new SolicitudCertificadoProxy(base);
        SolicitudCertificado solicitud = new SolicitudCertificado("EVT-001", "PART-123", "Ana", "ana@correo.com");
        assertDoesNotThrow(() -> controlado.emitir(solicitud));
    }
}