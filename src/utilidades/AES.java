package utilidades;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    private static SecretKey key;
    private static final int LONGITUD = 128;

    private static byte[] IV;

    public static void inicializar(String llave, String iv) {

        key = new SecretKeySpec(decodificar(llave), "AES");
        IV = decodificar(iv);

    }

    public static String desencriptar(String textoEncriptado) {

        byte[] textoEnBytes = decodificar(textoEncriptado);
        Cipher cifradoDesencriptacion = null;

        try {

            cifradoDesencriptacion = Cipher.getInstance("AES/GCM/NoPadding");

            GCMParameterSpec parametros = new GCMParameterSpec(LONGITUD, IV);
            cifradoDesencriptacion.init(Cipher.DECRYPT_MODE, key, parametros);

            byte[] textoCifrado = cifradoDesencriptacion.doFinal(textoEnBytes);

            return new String(textoCifrado);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
            return null;

        } catch (NoSuchPaddingException e) {

            e.printStackTrace();
            return null;

        } catch (InvalidKeyException e) {

            e.printStackTrace();
            return null;

        } catch (IllegalBlockSizeException e) {

            e.printStackTrace();
            return null;

        } catch (BadPaddingException e) {

            e.printStackTrace();
            return null;

        } catch (InvalidAlgorithmParameterException e) {

            e.printStackTrace();
            return null;

        }

    }

    public static String codificar(byte[] informacion) {

        return Base64.getEncoder().encodeToString(informacion);

    }

    public static byte[] decodificar(String informacion) {

        if (informacion == null) {
            throw new IllegalArgumentException(
                    "El parametro informacion no puede ser nulo");
        }

        try {
            return Base64.getDecoder().decode(informacion);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "La entrada no es un valor Base64 valido", e);
        }

    }

}