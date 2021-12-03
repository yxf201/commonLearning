package designPattern.singleton;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yxf
 * @date 2021/12/1 16:23
 */
public class PerfectSingleTon {
    private PerfectSingleTon(){}

    private static class HolderClass{
        private final static PerfectSingleTon instance = new PerfectSingleTon();
    }

    public static PerfectSingleTon getInstance(){
        return HolderClass.instance;
    }
}
