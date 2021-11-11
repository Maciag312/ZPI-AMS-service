package com.zpi.domain.user;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;


@Getter
@RequiredArgsConstructor
public class OneTimePassword {
    private final String email;
    private final LocalDateTime expiresAt;
    private final String password;
    private final BufferedImage qrCode;

    public OneTimePassword(String email, LocalDateTime expiresAt) throws IOException, WriterException {
        this.email = email;
        this.expiresAt = expiresAt;
        password = RandomStringUtils.randomAlphanumeric(10);
        qrCode = generateQRcode(password, 200, 200);
    }

    public boolean isValid(String password, LocalDateTime now) {
        return passwordMatches(password) && isNotExpired(now);
    }

    private boolean passwordMatches(String password) {
        return this.password.equals(password);
    }

    private boolean isNotExpired(LocalDateTime now) {
        return now.isBefore(expiresAt);
    }

    private BufferedImage generateQRcode(String data, int h, int w) throws WriterException, IOException
    {
        var charset = "UTF-8";
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);
        var image = MatrixToImageWriter.toBufferedImage(matrix);
        return image;
    }
}
