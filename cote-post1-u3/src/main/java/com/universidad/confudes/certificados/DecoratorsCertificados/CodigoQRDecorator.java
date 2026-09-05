package com.universidad.confudes.certificados.DecoratorsCertificados;

import com.universidad.confudes.certificados.ServicioCertificados;
import com.universidad.confudes.certificados.SolicitudCertificado;
import com.universidad.confudes.certificados.UtilidadesPDF;

public class CodigoQRDecorator extends ServicioCertificadosDecorator {


    public CodigoQRDecorator(ServicioCertificados wrappee) {
        super(wrappee);
    }

   @Override
    public byte[] emitir(SolicitudCertificado solicitud) {

        byte[] documento = wrappee.emitir(solicitud);

        if (documento == null) {
            return null;
        }

         String urlVerificacion =
            "https://confudes.edu/verificar/"
            + solicitud.getEventoId()
            + "/"
            + solicitud.getParticipanteId();

        return UtilidadesPDF.insertarCodigoQR(
                documento,
                urlVerificacion
        );
    }
}