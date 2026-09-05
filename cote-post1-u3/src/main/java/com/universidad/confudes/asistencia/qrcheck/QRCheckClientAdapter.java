package com.universidad.confudes.asistencia.qrcheck;

import org.springframework.stereotype.Service;

import com.universidad.confudes.asistencia.ResultadoCheckIn;
import com.universidad.confudes.asistencia.ServicioAsistencia;
import com.universidad.confudes.externo.qrcheck.QRCheckClient;
import com.universidad.confudes.externo.qrcheck.QRCheckRequest;
import com.universidad.confudes.externo.qrcheck.QRCheckResponse;

@Service
public class QRCheckClientAdapter implements ServicioAsistencia {

    private final QRCheckClient qrCheckClient;

    public QRCheckClientAdapter(QRCheckClient qrCheckClient) {
        this.qrCheckClient = qrCheckClient;
    }

    @Override
    public ResultadoCheckIn registrarAsistencia(
            String eventoId,
            String participanteId,
            String credencialQR) {

        long idEvento = convertirEventoId(eventoId);

        String payload = credencialQR;
        if(credencialQR.startsWith("QR-")){
                payload = "QR-" + payload;
        }
        

        QRCheckRequest request =
                new QRCheckRequest(payload, idEvento);

        QRCheckResponse response =
                qrCheckClient.validar(request);

        boolean exitoso = response.getCodigoRespuesta() == 200;

        return new ResultadoCheckIn(
                exitoso,
                response.getDetalle()
        );
    }

    private long convertirEventoId(String eventoId) {
        return Long.parseLong(eventoId.replace("EVT-", ""));
    }
}