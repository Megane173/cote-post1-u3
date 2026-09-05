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

    private final GestionCertificadosFacade gestionCertificadosFacade;

    public ControladorCertificados(GestionCertificadosFacade gestionCertificadosFacade) {
        this.gestionCertificadosFacade=gestionCertificadosFacade;
    }

    @PostMapping("/{eventoId}/{participanteId}")
    public ResponseEntity<String> emitir(@PathVariable String eventoId, @PathVariable String participanteId,
                                          @RequestParam String nombre, @RequestParam String correoDestino) {

        
        RespuestaPeticionesCertificados r = gestionCertificadosFacade.emitir(eventoId, participanteId, nombre, correoDestino);  

        return r.exito() ? 
        ResponseEntity.ok(r.descripcion()) 
        : ResponseEntity.status(403).body(r.descripcion());
    }
}