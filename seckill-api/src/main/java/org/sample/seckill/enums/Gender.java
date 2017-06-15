package org.sample.seckill.enums;

import org.sample.enums.ValuedEnum;

/**
 * 性别
 */
public enum Gender implements ValuedEnum {

    Unknown(0, "未知"),
    Male(1, "男"),
    Female(2, "女");

    int value;
    String name;

    Gender(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static Gender toEnum(int value) {
        for (Gender type : Gender.values())
            if (type.value == value)
                return type;
        return null;
    }
}