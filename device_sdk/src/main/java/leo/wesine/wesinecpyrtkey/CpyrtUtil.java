package leo.wesine.wesinecpyrtkey;

import android.os.Build;
import android.util.Log;

/**
 * Created by wesine on 2018/9/12.
 */

public class CpyrtUtil {
    private static final String TAG = "CpyrtUtil";
    private static CpyrtUtil mCpyrtUtil;

    private CpyrtUtil() {
    }

    public static CpyrtUtil getCpyrtUtil() {
        if (mCpyrtUtil == null) {
            synchronized (CpyrtUtil.class) {
                if (mCpyrtUtil == null) {
                    mCpyrtUtil = new CpyrtUtil();
                }
            }
        }
        return mCpyrtUtil;
    }

    private byte[] asciiString2byteArray(String serialno) {
        byte[] bytes = new byte[11];
        if (serialno.length() != 10) {
            return bytes;
        }
        try {
            for (int i = 0; i < serialno.length(); i++) {
                char c = serialno.charAt(i);
                bytes[i] = (byte) c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public boolean validateSerial() {
        return validateSerial(Build.SERIAL, null);
    }


    public SerialInfo validateSerial(String serialno) {
        SerialInfo serialInfo = new SerialInfo();
        if (serialno != null && serialno.length() > 0) {
            StringBuffer buffer = new StringBuffer();
            byte[] preCode_v = new byte[1];
            byte[] factory_v = new byte[2];
            int[] year_v = new int[1];
            int[] week_v = new int[1];
            byte[] type_v = new byte[1];
            int[] num_v = new int[1];
            int i = validateKey(serialno, preCode_v, factory_v, year_v, week_v, type_v, num_v);
            if (i >= 0) {
                String preCode = new String(preCode_v);
                String factory = new String(factory_v);
                String type = new String(type_v);
                int week = week_v[0] - 1;
                buffer.append(preCode + "\r\n");
                buffer.append(factory + "\r\n");
                buffer.append(year_v[0] + "\r\n");
                buffer.append(week + "\r\n");
                buffer.append(type + "\r\n");
                buffer.append(num_v[0] + "\r\n");

                serialInfo.setPreCode(preCode);
                serialInfo.setFactory(factory);
                serialInfo.setYear(year_v[0]);
                serialInfo.setWeek(week);
                serialInfo.setType(type);
                serialInfo.setNum(num_v[0]);
                Log.i(TAG, "validateSerial: " + buffer);
                return serialInfo;
            }
        }
        return null;
    }

    public boolean validateSerial(String serialno, SerialCallback callback) {
        if (serialno != null && serialno.length() > 0) {
            StringBuffer buffer = new StringBuffer();
            byte[] preCode_v = new byte[1];
            byte[] factory_v = new byte[2];
            int[] year_v = new int[1];
            int[] week_v = new int[1];
            byte[] type_v = new byte[1];
            int[] num_v = new int[1];
            int i = validateKey(serialno, preCode_v, factory_v, year_v, week_v, type_v, num_v);
            if (i >= 0) {

                String preCode = new String(preCode_v);
                String factory = new String(factory_v);
                String type = new String(type_v);
                int week = week_v[0] - 1;
                buffer.append("前缀" + preCode + "\r\n");
                buffer.append("工厂" + factory + "\r\n");
                buffer.append("年" + year_v[0] + "\r\n");
                buffer.append("周" + week + "\r\n");
                buffer.append("类型" + type + "\r\n");
                buffer.append("当周生产序号" + num_v[0] + "\r\n");
                if (callback != null) {
                    SerialInfo serialInfo = new SerialInfo();
                    serialInfo.setPreCode(preCode);
                    serialInfo.setFactory(factory);
                    serialInfo.setYear(year_v[0]);
                    serialInfo.setWeek(week);
                    serialInfo.setType(type);
                    serialInfo.setNum(num_v[0]);
                    callback.onGetSerialInfo(serialInfo);
                }
                Log.i(TAG, "validateSerial: " + buffer);
                return true;
            }
        }
        return false;
    }

    private int validateKey(//INPUT
                            String key,
                            //OUTPUT
                            byte[] preCode,
                            byte[] factory,
                            int[] year, int[] week,
                            byte[] type,
                            int[] num) {
        int code = -1;
        try {
            byte[] bytes = asciiString2byteArray(key);
            code = LibWsKey.WS_ValidateKey(bytes, preCode, factory, year, week, type, num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;

    }

    protected int generateKey(//OUTPUT
                              byte[] key,
                              //INPUT
                              byte preCode,
                              byte[] factory,
                              int year, int week,
                              byte type,
                              int num) {
        int code = -1;
        try {
            code = LibWsKey.WS_GenerateKey(key, preCode, factory, year, week, type, num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }

    private int validateKey(//INPUT
                            byte[] key,
                            //OUTPUT
                            byte[] preCode,
                            byte[] factory,
                            int[] year, int[] week,
                            byte[] type,
                            int[] num) {
        int code = -1;
        try {
            code = LibWsKey.WS_ValidateKey(key, preCode, factory, year, week, type, num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }
}
