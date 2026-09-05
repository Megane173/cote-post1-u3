package com.universidad.confudes.certificados;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class EmisionCertificadoTest {

    @Test
    void ordenColaboradorOrquestaLasCuatroEtapasSinExcepcion() {
        var validador = new ValidadorAsistencia();
        var generador = new GeneradorCertificadoPDF();
        var firma = new FirmaDigitalService();
        var correo = new EnvioCorreoService();

        Object colaborador = new GestionCertificadosFacade(validador, generador, firma, correo);
        // La operación simple expuesta por su colaborador debe ejecutarse sin lanzar excepciones
        assertDoesNotThrow(() -> {
            ((ServicioCertificados) colaborador).emitir( new SolicitudCertificado(
            "EVT-001",
            "PART-123",
            "Pepito",
            "petito02@ejemplo.com"
            ));
        });
    }

    @Test
    void controladorCertificadosSoloDependeDeUnColaborador() {
        // Verificación de diseño: ControladorCertificados debe declarar un solo
        // constructor con un solo parámetro tras la refactorización.
        var constructores = ControladorCertificados.class.getDeclaredConstructors();
        assertEquals(1, constructores.length);
        assertEquals(1, constructores[0].getParameterCount());
    }
}