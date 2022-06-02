package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper;

import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;


public class BaseCalcuationHelper {
    public static final float STEP_DURATION = 0.555194f;

    public boolean betweenDouble(double d, double d2, double d3) {
        return d >= d2 && d <= d3;
    }

    public boolean betweenInt(int i, int i2, int i3) {
        return i >= i2 && i <= i3;
    }

    public float stepsToActiveInMinute(float f) {
        return (f * 0.555194f) / 60.0f;
    }

    public double sizeInCmToStepSize(int i) {
        if (i < 150) {
            return 0.5d;
        }
        if (betweenInt(i, 150, Opcodes.IF_ICMPEQ)) {
            return 0.6d;
        }
        if (betweenInt(i, Opcodes.IF_ICMPNE, Opcodes.RET)) {
            return 0.65d;
        }
        return betweenInt(i, Opcodes.TABLESWITCH, Opcodes.PUTSTATIC) ? 0.7d : 0.75d;
    }

    public double roundAvoid(double d, int i) {
        double pow = Math.pow(10.0d, i);
        return Math.round(d * pow) / pow;
    }
}
