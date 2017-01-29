/*
 * The second question from http://www.1point3acres.com/bbs/thread-218628-1-1.html
 *
 */
 
public class Base64Encoding {
    
    static String encodeString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    
    public char[] encode(byte[] byteArray) {
        int length = byteArray.length % 3 == 0 ? byteArray.length / 3 * 4 : (byteArray.length / 3 + 1) * 4;
        char[] result = new byte[length];
        
        for (int i = 0; i < byteArray.length / 3; i++) {
            result[4 * i] = encodeString.charAt(byteArray[3 * i] >> 2);
            result[4 * i + 1] = encodeString.charAt(((byteArray[3 * i] & 0b11) << 4) & (byteArray[3 * i + 1] >> 4));
            result[4 * i + 2] = encodeString.charAt(((byteArray[3 * i + 1] & 0b1111) << 2) & (byteArray[3 * i + 2] >> 6));
            result[4 * i + 3] = encodeString.charAt(byteArray[3 * i + 2] & 0b111111);
        }
       
        if (byteArray.length % 3 == 1) {
            int index = byteArray.length / 3 * 3 + 1;
            result[byteArray.length / 3 * 4 + 1] = encodeString.charAt(byteArray[index] >> 2);
            result[byteArray.length / 3 * 4 + 2] = encodeString.charAt((byteArray[index] & 0b11) << 4);
            result[byteArray.length / 3 * 4 + 3] = '=';
            result[byteArray.length / 3 * 4 + 4] = '=';
        } else if (byteArray.length % 3 == 2) {
            int index = byteArray.length / 3 * 3 + 1;
            result[byteArray.length / 3 * 4 + 1] = encodeString.charAt(byteArray[index] >> 2);
            result[byteArray.length / 3 * 4 + 2] = encodeString.charAt((byteArray[index] & 0b11) << 4) & (byteArray[index + 1] >> 4));
            result[byteArray.length / 3 * 4 + 3] = encodeString.charAt(byteArray[index + 1] & 0b1111) << 2);
            result[byteArray.length / 3 * 4 + 4] = '=';
        }
        
        return result;
    }
}
