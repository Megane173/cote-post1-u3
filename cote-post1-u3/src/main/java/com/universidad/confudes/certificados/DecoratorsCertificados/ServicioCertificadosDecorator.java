
package com.universidad.confudes.certificados.DecoratorsCertificados;

import com.universidad.confudes.certificados.ServicioCertificados;
import com.universidad.confudes.certificados.SolicitudCertificado;

public abstract class ServicioCertificadosDecorator implements ServicioCertificados {

    protected final ServicioCertificados wrappee;

    protected ServicioCertificadosDecorator(ServicioCertificados wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public byte[] emitir(SolicitudCertificado solicitud) {
        return wrappee.emitir(solicitud);
    }
}
