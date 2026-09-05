package com.universidad.confudes.certificados;

import com.universidad.confudes.acceso.ContextoUsuario;

public class SolicitudCertificadoProxy implements ServicioCertificados {


    private final ServicioCertificados servicioCertificado;

    public SolicitudCertificadoProxy(ServicioCertificados servicioCertificado){
        this.servicioCertificado=servicioCertificado;
    }


    @Override
    public byte[] emitir(SolicitudCertificado solicitud){

        String rol = ContextoUsuario.rolActual();

        if ("ORGANIZADOR".equals(rol) || "ADMIN".equals(rol)) {
            return servicioCertificado.emitir(solicitud);
        }

        throw new SecurityException("Usuario no autorizado para emitir certificados");
    }
}
