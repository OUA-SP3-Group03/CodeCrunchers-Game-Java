package io.codecrunchers.classes;

import java.util.HashMap;

public class ASCII {
    //Static Key Bindings
    public static final int escape = 27;
    public static final int shift = 16;
    public static final int backspace = 8;

    //Static ASCII key map
    public static HashMap<Integer, Integer> alternativeKeys;

    static {
        //ALTERNATIVE KEYS TOP

        alternativeKeys = new HashMap<Integer, Integer>();
        //1-!
        alternativeKeys.put(49, 33);
        //2-@
        alternativeKeys.put(50, 64);
        //3-#
        alternativeKeys.put(51, 35);
        //4-$
        alternativeKeys.put(52, 36);
        //5-%
        alternativeKeys.put(53, 37);
        //6-^
        alternativeKeys.put(54,94);
        //7-&
        alternativeKeys.put(55, 38);
        //8-*
        alternativeKeys.put(56, 42);
        //9-(
        alternativeKeys.put(57, 40);
        //0-)
        alternativeKeys.put(48, 41);
        //--_
        alternativeKeys.put(45, 95);
        //=-+
        alternativeKeys.put(61, 43);

        //ALTERNATIVE KEYS RIGHT HAND SIDE

        //[-{
        alternativeKeys.put(91, 123);
        //]-}
        alternativeKeys.put(93, 125);
        //\-|
        alternativeKeys.put(92, 124);
        //;-:
        alternativeKeys.put(59, 58);
        //,-<
        alternativeKeys.put(44, 60);
        //.->
        alternativeKeys.put(46, 62);
        //.->
        alternativeKeys.put(47, 63);
    }
}
