package com.universidad.confudes.certificados;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// Versión actual — funciona, pero conoce y orquesta los cuatro servicios
// directamente. El equipo pide reducirlo a una sola dependencia.
@RestController
@RequestMapping("/api/certificados")
public class ControladorCertificados {

    private final ServicioCertificados servicioCertificados;

    public ControladorCertificados(ServicioCertificados servicioCertificados) {
        this.servicioCertificados = servicioCertificados;
    }

    @PostMapping("/{eventoId}/{participanteId}")
    public ResponseEntity<byte[]> emitir(@PathVariable String eventoId, @PathVariable String participanteId, 
        @RequestParam String nombre, @RequestParam String correoDestino) {

        SolicitudCertificado solicitud = new SolicitudCertificado( eventoId, participanteId, nombre, correoDestino);

        byte[] certificado = servicioCertificados.emitir(solicitud);

        if (certificado == null) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(certificado);
    }
}