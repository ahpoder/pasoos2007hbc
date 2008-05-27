package hottargui.standard;

import hottargui.framework.Die;

public class StandardDie implements Die {
    private int value;

    public void rollDie() {
        int val = (int) (6 * Math.random() + 1);   // Range 1-6
        setValue(val);
    }

    public void setValue(int val) {
        this.value = val;
    }

    public int getValue() {
        return this.value;
    }
}