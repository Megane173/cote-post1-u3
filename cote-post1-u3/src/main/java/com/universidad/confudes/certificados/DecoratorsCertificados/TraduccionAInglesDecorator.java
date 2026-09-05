package com.universidad.confudes.certificados.DecoratorsCertificados;

import com.universidad.confudes.certificados.ServicioCertificados;
import com.universidad.confudes.certificados.SolicitudCertificado;
import com.universidad.confudes.certificados.UtilidadesPDF;

public class TraduccionAInglesDecorator extends ServicioCertificadosDecorator {

    public TraduccionAInglesDecorator(ServicioCertificados wrappee) {
        super(wrappee);
    }

    @Override
    public byte[] emitir(SolicitudCertificado solicitud) {

        byte[] documento = wrappee.emitir(solicitud);

        if (documento == null) {
            return null;
        }

        return UtilidadesPDF.traducirAIngles(documento);
    }
}