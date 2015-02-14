package si.api.utils;

/**
 * Created by dd.fernandes on 07/10/2014.
 */
public class HwByte {

    private boolean[] bits = new boolean[8];

    public HwByte() {
    }

    public HwByte(boolean[] bits) {
        this.bits = bits;
    }

    public int getBit(int pos) {
        return (this.bits[7-pos]) ? 1 : 0;
    }

    public void setBit(int pos, int val) {
        this.bits[7-pos] = (val == 1);
    }

    public boolean[] getBits() {
        return bits;
    }

    public void setBits(boolean[] bits) {
        this.bits = bits;
    }

    public int toInt() {
        int n = 0;
        for (int i = 0; i < 8; ++i) {
            n = (n << 1) + (this.bits[i] ? 1 : 0);
        }
        return n;
    }

    public static HwByte parseInt(int data) {
        HwByte hwByte = new HwByte();

        for (int i = 0; i < 8; i++) {
            hwByte.bits[7-i] = (data & (1 << i)) != 0;
        }

        return hwByte;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof HwByte)) return false;
        HwByte hwByte = (HwByte) obj;
        for (int i = 0; i < 8; i++) {
            if (this.bits[i] != hwByte.bits[i])
                return false;
        }
        return true;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p/>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        String a = "";
        for (boolean b : this.getBits()){
            a = a.concat(String.valueOf((b)? 1 : 0));
        }
        return a;
    }
}
