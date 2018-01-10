package cc.yujie.dataplugs.aliyunsdk;

/**
 * Created by xwc on 2017/9/29.
 */


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Base64Kit {
    private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    private static byte[] codes = new byte[256];

    static {
        int i;
        for (i = 0; i < 256; ++i) {
            codes[i] = -1;
        }

        for (i = 65; i <= 90; ++i) {
            codes[i] = (byte) (i - 65);
        }

        for (i = 97; i <= 122; ++i) {
            codes[i] = (byte) (26 + i - 97);
        }

        for (i = 48; i <= 57; ++i) {
            codes[i] = (byte) (52 + i - 48);
        }

        codes[43] = 62;
        codes[47] = 63;
    }

    public Base64Kit() {
    }

    public static String encode(String data) {
        return new String(encode(data.getBytes()));
    }

    public static String decode(String data) {
        return new String(decode(data.toCharArray()));
    }

    public static char[] encode(byte[] data) {
        char[] out = new char[(data.length + 2) / 3 * 4];
        int i = 0;

        for (int index = 0; i < data.length; index += 4) {
            boolean quad = false;
            boolean trip = false;
            int val = 255 & data[i];
            val <<= 8;
            if (i + 1 < data.length) {
                val |= 255 & data[i + 1];
                trip = true;
            }

            val <<= 8;
            if (i + 2 < data.length) {
                val |= 255 & data[i + 2];
                quad = true;
            }

            out[index + 3] = alphabet[quad ? val & 63 : 64];
            val >>= 6;
            out[index + 2] = alphabet[trip ? val & 63 : 64];
            val >>= 6;
            out[index + 1] = alphabet[val & 63];
            val >>= 6;
            out[index + 0] = alphabet[val & 63];
            i += 3;
        }

        return out;
    }

    public static byte[] decode(char[] data) {
        int tempLen = data.length;

        int len;
        for (len = 0; len < data.length; ++len) {
            if (data[len] > 255 || codes[data[len]] < 0) {
                --tempLen;
            }
        }

        len = tempLen / 4 * 3;
        if (tempLen % 4 == 3) {
            len += 2;
        }

        if (tempLen % 4 == 2) {
            ++len;
        }

        byte[] out = new byte[len];
        int shift = 0;
        int accum = 0;
        int index = 0;

        for (int ix = 0; ix < data.length; ++ix) {
            byte value = data[ix] > 255 ? -1 : codes[data[ix]];
            if (value >= 0) {
                accum <<= 6;
                shift += 6;
                accum |= value;
                if (shift >= 8) {
                    shift -= 8;
                    out[index++] = (byte) (accum >> shift & 255);
                }
            }
        }

        if (index != out.length) {
            throw new Error("Miscalculated data length (wrote " + index + " instead of " + out.length + ")");
        } else {
            return out;
        }
    }

    public static void encode(File file) throws IOException {
        if (!file.exists()) {
            System.exit(0);
        } else {
            byte[] decoded = readBytes(file);
            char[] encoded = encode(decoded);
            writeChars(file, encoded);
        }

        file = null;
    }

    public static void decode(File file) throws IOException {
        if (!file.exists()) {
            System.exit(0);
        } else {
            char[] encoded = readChars(file);
            byte[] decoded = decode(encoded);
            writeBytes(file, decoded);
        }

        file = null;
    }

    private static byte[] readBytes(File file) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Object b = null;
        FileInputStream fis = null;
        BufferedInputStream is = null;

        byte[] b1;
        try {
            fis = new FileInputStream(file);
            is = new BufferedInputStream(fis);
            boolean count = false;
            byte[] buf = new byte[16384];

            int count1;
            while ((count1 = is.read(buf)) != -1) {
                if (count1 > 0) {
                    baos.write(buf, 0, count1);
                }
            }

            b1 = baos.toByteArray();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }

                if (is != null) {
                    is.close();
                }

                if (baos != null) {
                    baos.close();
                }
            } catch (Exception var12) {
                System.out.println(var12);
            }

        }

        return b1;
    }

    private static char[] readChars(File file) throws IOException {
        CharArrayWriter caw = new CharArrayWriter();
        FileReader fr = null;
        BufferedReader in = null;

        try {
            fr = new FileReader(file);
            in = new BufferedReader(fr);
            boolean count = false;
            char[] buf = new char[16384];

            int count1;
            while ((count1 = in.read(buf)) != -1) {
                if (count1 > 0) {
                    caw.write(buf, 0, count1);
                }
            }
        } finally {
            try {
                if (caw != null) {
                    caw.close();
                }

                if (in != null) {
                    in.close();
                }

                if (fr != null) {
                    fr.close();
                }
            } catch (Exception var11) {
                System.out.println(var11);
            }

        }

        return caw.toCharArray();
    }

    private static void writeBytes(File file, byte[] data) throws IOException {
        FileOutputStream fos = null;
        BufferedOutputStream os = null;

        try {
            fos = new FileOutputStream(file);
            os = new BufferedOutputStream(fos);
            os.write(data);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }

                if (fos != null) {
                    fos.close();
                }
            } catch (Exception var9) {
                System.out.println(var9);
            }

        }

    }

    private static void writeChars(File file, char[] data) throws IOException {
        FileWriter fos = null;
        BufferedWriter os = null;

        try {
            fos = new FileWriter(file);
            os = new BufferedWriter(fos);
            os.write(data);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }

                if (fos != null) {
                    fos.close();
                }
            } catch (Exception var9) {
                var9.printStackTrace();
            }

        }

    }
}

