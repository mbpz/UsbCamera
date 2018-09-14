package leo.wesine.wesinecpyrtkey;

/**
 * Created by leo on 2018/2/27.
 */

public class LibWsKey {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("libwskey");
    }

    public static native int WS_GenerateKey(
            //OUTPUT
            byte[] key,
            //INPUT
            byte preCode,
            byte[] factory,
            int year, int week,
            byte type,
            int num);

    public static native int WS_ValidateKey(
            //INPUT
            byte[] key,
            //OUTPUT
            byte[] preCode,
            byte[] factory,
            int[] year, int[] week,
            byte[] type,
            int[] num);
}
