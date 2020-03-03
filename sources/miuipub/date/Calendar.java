package miuipub.date;

import com.facebook.imagepipeline.memory.BitmapCounterProvider;
import com.taobao.weex.el.parse.Operators;
import com.tencent.smtt.sdk.TbsListener;
import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;
import miuipub.util.Pools;

public class Calendar implements Serializable, Cloneable, Comparable<Calendar> {
    public static final int AD = 1;
    public static final int AFTERNOON = 4;
    public static final int AM = 0;
    public static final int AM_PM = 17;
    public static final int APRIL = 3;
    public static final int AUGUST = 7;
    public static final int AUTUMN_BEGINS = 15;
    public static final int AUTUMN_EQUINOX = 18;
    public static final int BC = 0;
    public static final int CHICKEN = 9;
    public static final int CHINESE_ERA_DAY = 11;
    public static final int CHINESE_ERA_HOUR = 19;
    public static final int CHINESE_ERA_MONTH = 7;
    public static final int CHINESE_ERA_YEAR = 4;
    public static final int CHINESE_MONTH = 6;
    public static final int CHINESE_MONTH_IS_LEAP = 8;
    public static final int CHINESE_YEAR = 2;
    public static final int CHINESE_YEAR_SYMBOL_ANIMAL = 3;
    public static final int CLEAR_AND_BRIGHT = 7;
    public static final int COLD_DEWS = 19;
    public static final int COW = 1;
    public static final int DAY_OF_CHINESE_MONTH = 10;
    public static final int DAY_OF_CHINESE_YEAR = 13;
    public static final int DAY_OF_MONTH = 9;
    public static final int DAY_OF_WEEK = 14;
    public static final int DAY_OF_YEAR = 12;
    public static final int DECEMBER = 11;
    public static final int DETAIL_AM_PM = 16;
    public static final int DOG = 10;
    public static final int DRAGON = 4;
    public static final int DST_OFFSET = 24;
    public static final int EARLY_MORNING = 1;
    public static final int ERA = 0;
    public static final int EVENING = 5;
    public static final int FEBRUARY = 1;
    public static final int FIELD_COUNT = 25;
    public static final int FRIDAY = 6;
    public static final int GRAIN_BUDS = 10;
    public static final int GRAIN_IN_EAR = 11;
    public static final int GRAIN_RAIN = 8;
    public static final int GREAT_COLD = 2;
    public static final int GREAT_HEAT = 14;
    public static final int HEAVY_SNOW = 23;
    public static final int HOAR_FROST_FALLS = 20;
    public static final int HORSE = 6;
    public static final int HOUR = 18;
    public static final int INSECTS_AWAKEN = 5;
    public static final int IS_CHINESE_LEAP_MONTH = 1;
    public static final int JANUARY = 0;
    public static final int JULY = 6;
    public static final int JUNE = 5;
    public static final int LIGHT_SNOW = 22;
    public static final int MARCH = 2;
    public static final int MAY = 4;
    public static final int MIDNIGHT = 0;
    public static final int MILLISECOND = 22;
    public static final int MILLISECOND_OF_DAY = 86400000;
    public static final int MILLISECOND_OF_HOUR = 3600000;
    public static final int MILLISECOND_OF_MINUTE = 60000;
    public static final int MILLISECOND_OF_SECOND = 1000;
    public static final int MINUTE = 20;
    public static final int MONDAY = 2;
    public static final int MONKEY = 8;
    public static final int MONTH = 5;
    public static final int MORNING = 2;
    public static final int MOUSE = 0;
    public static final int NIGHT = 6;
    public static final int NOON = 3;
    public static final int NOT_CHINESE_LEAP_MONTH = 0;
    public static final int NOVEMBER = 10;
    public static final int NO_SOLAR_TERM = 0;
    public static final int OCTOBER = 9;
    public static final int PIG = 11;
    public static final int PM = 1;
    public static final int RABBIT = 3;
    public static final int SATURDAY = 7;
    public static final int SECOND = 21;
    public static final int SEPTEMBER = 8;
    public static final int SHEEP = 7;
    public static final int SLIGHT_COLD = 1;
    public static final int SLIGHT_HEAT = 13;
    public static final int SNAKE = 5;
    public static final int SOLAR_TERM = 15;
    public static final int SPRING_BEGINS = 3;
    public static final int STOPPING_THE_HEAT = 16;
    public static final int SUMMER_BEGINS = 9;
    public static final int SUMMER_SOLSTICE = 12;
    public static final int SUNDAY = 1;
    public static final int THE_RAINS = 4;
    public static final int THURSDAY = 5;
    public static final int TIGER = 2;
    public static final int TUESDAY = 3;
    public static final int VERNAL_EQUINOX = 6;
    public static final int WEDNESDAY = 4;
    public static final int WHITE_DEWS = 17;
    public static final int WINTER_BEGINS = 21;
    public static final int WINTER_SOLSTICE = 24;
    public static final int YEAR = 1;
    public static final int ZONE_OFFSET = 23;

    /* renamed from: a  reason: collision with root package name */
    private static final int f2927a = 1900;
    private static final int b = 2100;
    private static final long c = -2206396800000L;
    private static final long d = 4136400000000L;
    private static final int e = -25537;
    private static final byte[] f = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] g = {0, 31, 59, 90, 120, 151, 181, TbsListener.ErrorCode.COPY_FAIL, 243, 273, 304, 334, 365};
    private static final String[] h = {"ERA", "YEAR", "CHINESE_YEAR", "CHINESE_YEAR_SYMBOL_ANIMAL", "CHINESE_ERA_YEAR", "MONTH", "CHINESE_MONTH", "CHINESE_ERA_MONTH", "CHINESE_MONTH_IS_LEAP", "DAY_OF_MONTH", "DAY_OF_CHINESE_MONTH", "CHINESE_ERA_DAY", "DAY_OF_YEAR", "DAY_OF_CHINESE_YEAR", "DAY_OF_WEEK", "SOLAR_TERM", "DETAIL_AM_PM", "AM_PM", "HOUR", "CHINESE_ERA_HOUR", "MINUTE", "SECOND", "MILLISECOND", "ZONE_OFFSET", "DST_OFFSET"};
    private static final int[] i = {3, -1, -1, 12, 14, 26, 0, 18, 19, -1, 18, 5, 5, 7, -1, -1, -1, -1, 22, -1, -1, -1, 26, -1, 4, 25, -1, -1, -1, -1, -1, -1, 16, -1, 14, 9, 7, -1, -1, 18, -1, -1, 18, -1, 20, -1, -1, -1, -1, -1, 21, 15, -1, -1, 26, -1, 1, 25};
    private static final int[] j = {0, BitmapCounterProvider.MAX_BITMAP_COUNT, 738, 1093, 1476, 1830, 2185, 2569, 2923, 3278, 3662, 4016, 4400, 4754, 5108, 5492, 5846, 6201, 6585, 6940, 7324, 7678, 8032, 8416, 8770, 9124, 9509, 9863, 10218, 10602, 10956, 11339, 11693, 12048, 12432, 12787, 13141, 13525, 13879, 14263, 14617, 14971, 15355, 15710, 16064, 16449, 16803, 17157, 17541, 17895, 18279, 18633, 18988, 19372, 19726, 20081, 20465, 20819, 21202, 21557, 21911, 22295, 22650, 23004, 23388, 23743, 24096, 24480, 24835, 25219, 25573, 25928, 26312, 26666, 27020, 27404, 27758, 28142, 28496, 28851, 29235, 29590, 29944, 30328, 30682, 31066, 31420, 31774, 32158, 32513, 32868, 33252, 33606, 33960, 34343, 34698, 35082, 35436, 35791, 36175, 36529, 36883, 37267, 37621, 37976, 38360, 38714, 39099, 39453, 39807, 40191, 40545, 40899, 41283, 41638, 42022, 42376, 42731, 43115, 43469, 43823, 44207, 44561, 44916, 45300, 45654, 46038, 46392, 46746, 47130, 47485, 47839, 48223, 48578, 48962, 49316, 49670, 50054, 50408, 50762, 51146, 51501, 51856, 52240, 52594, 52978, 53332, 53686, 54070, 54424, 54779, 55163, 55518, 55902, 56256, 56610, 56993, 57348, 57702, 58086, 58441, 58795, 59179, 59533, 59917, 60271, 60626, 61010, 61364, 61719, 62103, 62457, 62841, 63195, 63549, 63933, 64288, 64642, 65026, 65381, 65735, 66119, 66473, 66857, 67211, 67566, 67950, 68304, 68659, 69042, 69396, 69780, 70134, 70489, 70873, 71228, 71582, 71966, 72320, 72674, 73058, 73412};
    private static final int[] k = {19416, 19168, 42352, 21717, 53856, 55632, 21844, 22191, 39632, 21970, 19168, 42422, 42192, 53840, 53909, 46415, 54944, 44450, 38320, 18807, 18815, 42160, 46261, 27216, 27968, 43860, 11119, 38256, 21234, 18800, 25958, 54432, 59984, 27285, 23263, 11104, 34531, 37615, 51415, 51551, 54432, 55462, 46431, 22176, 42420, 9695, 37584, 53938, 43344, 46423, 27808, 46416, 21333, 19887, 42416, 17779, 21183, 43432, 59728, 27296, 44710, 43856, 19296, 43748, 42352, 21088, 62051, 55632, 23383, 22176, 38608, 19925, 19152, 42192, 54484, 53840, 54616, 46400, 46752, 38310, 38335, 18864, 43380, 42160, 45690, 27216, 27968, 44870, 43872, 38256, 19189, 18800, 25776, 29859, 59984, 27480, 23232, 43872, 38613, 37600, 51552, 55636, 54432, 55888, 30034, 22176, 43959, 9680, 37584, 51893, 43344, 46240, 47780, 44368, 21977, 19360, 42416, 20854, 21183, 43312, 31060, 27296, 44368, 23378, 19296, 42726, 42208, 53856, 60005, 54576, 23200, 30371, 38608, 19195, 19152, 42192, 53430, 53855, 54560, 56645, 46496, 22224, 21938, 18864, 42359, 42160, 43600, 45653, 27951, 44448, 19299, 37759, 18936, 18800, 25776, 26790, 59999, 27424, 42692, 43759, 37600, 53987, 51552, 54615, 54432, 55888, 23893, 22176, 42704, 21972, 21200, 43448, 43344, 46240, 46758, 44368, 21920, 43940, 42416, 21168, 45683, 26928, 29495, 27296, 44368, 19285, 19311, 42352, 21732, 53856, 59752, 54560, 55968, 27302, 22239, 19168, 43476, 42192, 53584, 62034, 54560};
    private static final int[] l = {4, 19, 3, 18, 4, 19, 4, 19, 4, 20, 4, 20, 6, 22, 6, 22, 6, 22, 7, 22, 6, 21, 6, 21};
    private static final byte[] m = "0123415341536789:;<9:=<>:=1>?012@015@015@015AB78CDE8CD=1FD01GH01GH01IH01IJ0KLMN;LMBEOPDQRST0RUH0RVH0RWH0RWM0XYMNZ[MB\\]PT^_ST`_WH`_WH`_WM`_WM`aYMbc[Mde]Sfe]gfh_gih_Wih_WjhaWjka[jkl[jmn]ope]qph_qrh_sth_W".getBytes();
    private static final byte[] n = "211122112122112121222211221122122222212222222221222122222232222222222222222233223232223232222222322222112122112121222211222122222222222222222222322222112122112121222111211122122222212221222221221122122222222222222222222223222232222232222222222222112122112121122111211122122122212221222221221122122222222222222221211122112122212221222211222122222232222232222222222222112122112121111111222222112121112121111111222222111121112121111111211122112122112121122111222212111121111121111111111122112122112121122111211122112122212221222221222211111121111121111111222111111121111111111111111122112121112121111111222111111111111111111111111122111121112121111111221122122222212221222221222111011111111111111111111122111121111121111111211122112122112121122211221111011111101111111111111112111121111121111111211122112122112221222211221111011111101111111110111111111121111111111111111122112121112121122111111011111121111111111111111011111111112111111111111011111111111111111111221111011111101110111110111011011111111111111111221111011011101110111110111011011111101111111111211111001011101110111110110011011111101111111111211111001011001010111110110011011111101111111110211111001011001010111100110011011011101110111110211111001011001010011100110011001011101110111110211111001010001010011000100011001011001010111110111111001010001010011000111111111111111111111111100011001011001010111100111111001010001010000000111111000010000010000000100011001011001010011100110011001011001110111110100011001010001010011000110011001011001010111110111100000010000000000000000011001010001010011000111100000000000000000000000011001010001010000000111000000000000000000000000011001010000010000000".getBytes();
    private static final long o = -12219292800000L;
    private static final long serialVersionUID = 1;
    private int[] mFields;
    private long mMillisecond;
    private TimeZone mTimeZone;
    private transient long p;
    private transient int q;
    private transient int r;
    private transient int s;
    private transient int t;
    private transient int u;

    public Calendar() {
        this((TimeZone) null);
    }

    public Calendar(TimeZone timeZone) {
        this.mFields = new int[25];
        this.p = o;
        this.q = 1582;
        this.r = ((this.q / 100) - (this.q / 400)) - 2;
        this.s = (((this.q - 2000) / 400) + this.r) - ((this.q - 2000) / 100);
        this.t = 10;
        this.u = 0;
        this.mMillisecond = System.currentTimeMillis();
        setTimeZone(timeZone);
    }

    public Calendar setTimeZone(TimeZone timeZone) {
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        if (this.mTimeZone == null || !this.mTimeZone.getID().equals(timeZone.getID())) {
            this.mTimeZone = timeZone;
            c();
        }
        return this;
    }

    public TimeZone getTimeZone() {
        return this.mTimeZone;
    }

    public long getTimeInMillis() {
        return this.mMillisecond;
    }

    public Calendar setTimeInMillis(long j2) {
        this.mMillisecond = j2;
        c();
        return this;
    }

    public Calendar set(int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i3 < 0 || i3 > 11) {
            throw new IllegalArgumentException("Year " + i2 + " has no month " + i3);
        } else if (i4 <= 0 || i4 > a(isLeapYear(i2), i3)) {
            throw new IllegalArgumentException("Year " + i2 + " month " + i3 + " has no day " + i4);
        } else if (i5 < 0 || i5 > 23) {
            throw new IllegalArgumentException("Invalid hour " + i5);
        } else if (i6 < 0 || i6 > 59) {
            throw new IllegalArgumentException("Invalid minute " + i6);
        } else if (i7 < 0 || i7 > 59) {
            throw new IllegalArgumentException("Invalid second " + i7);
        } else if (i8 < 0 || i8 > 999) {
            throw new IllegalArgumentException("Invalid millisecond " + i8);
        } else {
            this.mFields[1] = i2;
            this.mFields[5] = i3;
            this.mFields[9] = i4;
            this.mFields[18] = i5;
            this.mFields[20] = i6;
            this.mFields[21] = i7;
            this.mFields[22] = i8;
            a();
            return this;
        }
    }

    public Calendar setChineseTime(int i2, int i3, int i4, boolean z, int i5, int i6, int i7, int i8) {
        if (i2 < 1900 || i2 > 2100) {
            throw new IllegalArgumentException("Date out of range [1900 - 2100] expected, but year is " + i2);
        } else if (i3 < 0 || i3 > 11) {
            throw new IllegalArgumentException("Year " + i2 + " has no month " + i3);
        } else if (z == 0 || b(i2) == i3) {
            if (z != 0) {
                if (i4 <= 0 || i4 > leapDaysInChineseYear(i2)) {
                    throw new IllegalArgumentException("Year " + i2 + " month " + i3 + " has no day " + i4);
                }
            } else if (i4 <= 0 || i4 > daysInChineseMonth(i2, i3)) {
                throw new IllegalArgumentException("Year " + i2 + " month " + i3 + " has no day " + i4);
            }
            if (i5 < 0 || i5 > 23) {
                throw new IllegalArgumentException("Invalid hour " + i5);
            } else if (i6 < 0 || i6 > 59) {
                throw new IllegalArgumentException("Invalid minute " + i6);
            } else if (i7 < 0 || i7 > 59) {
                throw new IllegalArgumentException("Invalid second " + i7);
            } else if (i8 < 0 || i8 > 1000) {
                throw new IllegalArgumentException("Invalid millisecond " + i8);
            } else {
                this.mFields[2] = i2;
                this.mFields[6] = i3;
                this.mFields[10] = i4;
                this.mFields[8] = z;
                this.mFields[18] = i5;
                this.mFields[20] = i6;
                this.mFields[21] = i7;
                this.mFields[22] = i8;
                b();
                return this;
            }
        } else {
            throw new IllegalArgumentException("Year " + i2 + " has no leap month " + i3);
        }
    }

    public Calendar set(int i2, int i3) {
        int i4;
        if (i2 == 6) {
            if (i3 < 0) {
                i3 = -i3;
                if (i3 == b(this.mFields[2])) {
                    this.mFields[8] = 1;
                } else {
                    throw new IllegalArgumentException("year " + this.mFields[2] + " has no such leap month:" + i3);
                }
            } else if (i3 >= getActualMinimum(i2) || i3 <= getActualMaximum(i2)) {
                this.mFields[8] = 0;
            } else {
                throw new IllegalArgumentException("value is out of date range [" + getActualMinimum(i2) + "-" + getActualMaximum(i2) + "]: " + i3);
            }
            this.mFields[6] = i3;
            if (this.mFields[8] == 1) {
                i4 = leapDaysInChineseYear(this.mFields[2]);
            } else {
                i4 = daysInChineseMonth(this.mFields[2], this.mFields[6]);
            }
            if (this.mFields[10] > i4) {
                this.mFields[10] = i4;
            }
            b();
            return this;
        } else if (i3 >= getActualMinimum(i2) || i3 <= getActualMaximum(i2)) {
            try {
                add(i2, i3 - this.mFields[i2]);
                return this;
            } catch (IllegalArgumentException unused) {
                throw new IllegalArgumentException("unsupported set field:" + h[i2]);
            }
        } else {
            throw new IllegalArgumentException("value is out of date range [" + getActualMinimum(i2) + "-" + getActualMaximum(i2) + "]: " + i3);
        }
    }

    public Calendar add(int i2, int i3) {
        int i4;
        int i5;
        if (i2 < 0 || i2 >= 25) {
            throw new IllegalArgumentException("Field out of range [0-25]: " + i2);
        }
        boolean z = false;
        switch (i2) {
            case 0:
                if (!(i3 == 0 || this.mFields[0] == (this.mFields[0] + i3) % 2 || this.mFields[0] != 0)) {
                    this.mFields[1] = -1 - this.mFields[1];
                    a();
                    break;
                }
            case 1:
                if (i3 != 0) {
                    if (this.mFields[0] == 0) {
                        this.mFields[1] = -1 - this.mFields[1];
                    }
                    int[] iArr = this.mFields;
                    iArr[1] = iArr[1] + i3;
                    int a2 = a(isLeapYear(this.mFields[1]), this.mFields[5]);
                    if (this.mFields[9] > a2) {
                        this.mFields[9] = a2;
                    }
                    a();
                    break;
                }
                break;
            case 2:
                if (i3 != 0) {
                    int i6 = i3 + this.mFields[2];
                    if (!outOfChineseCalendarRange() && i6 >= 1900 && i6 <= 2100) {
                        this.mFields[2] = i6;
                        if (this.mFields[8] == 1 && this.mFields[6] == b(this.mFields[2])) {
                            this.mFields[8] = 0;
                        }
                        if (this.mFields[8] == 1) {
                            i4 = leapDaysInChineseYear(this.mFields[2]);
                        } else {
                            i4 = daysInChineseMonth(this.mFields[2], this.mFields[6]);
                        }
                        if (this.mFields[10] > i4) {
                            this.mFields[10] = i4;
                        }
                        b();
                        break;
                    } else {
                        throw new IllegalArgumentException("out of range of Chinese Lunar Year");
                    }
                }
                break;
            case 5:
                if (i3 != 0) {
                    int i7 = i3 + this.mFields[5];
                    int i8 = i7 / 12;
                    int i9 = i7 % 12;
                    if (i9 < 0) {
                        i9 += 12;
                        i8--;
                    }
                    this.mFields[5] = i9;
                    if (i8 != 0) {
                        add(1, i8);
                        break;
                    } else {
                        if (this.mFields[0] == 0) {
                            this.mFields[1] = -1 - this.mFields[1];
                        }
                        int a3 = a(isLeapYear(this.mFields[1]), this.mFields[5]);
                        if (this.mFields[9] > a3) {
                            this.mFields[9] = a3;
                        }
                        a();
                        break;
                    }
                }
                break;
            case 6:
                if (i3 != 0) {
                    if (!outOfChineseCalendarRange()) {
                        int b2 = b(this.mFields[2]);
                        while (i3 > 0) {
                            if (this.mFields[6] == b2 && this.mFields[8] == 0) {
                                this.mFields[8] = 1;
                            } else {
                                int[] iArr2 = this.mFields;
                                iArr2[6] = iArr2[6] + 1;
                                this.mFields[8] = 0;
                                if (this.mFields[6] > 11) {
                                    this.mFields[6] = 0;
                                    int[] iArr3 = this.mFields;
                                    iArr3[2] = iArr3[2] + 1;
                                    if (this.mFields[2] <= 2100) {
                                        b2 = b(this.mFields[2]);
                                    } else {
                                        throw new IllegalArgumentException("out of range of Chinese Lunar Year");
                                    }
                                } else {
                                    continue;
                                }
                            }
                            i3--;
                        }
                        while (i3 < 0) {
                            if (this.mFields[6] == b2 && this.mFields[8] == 1) {
                                this.mFields[8] = 0;
                            } else {
                                int[] iArr4 = this.mFields;
                                iArr4[6] = iArr4[6] - 1;
                                if (this.mFields[6] < 0) {
                                    this.mFields[6] = 11;
                                    int[] iArr5 = this.mFields;
                                    iArr5[6] = iArr5[6] - 1;
                                    if (this.mFields[2] >= 1900) {
                                        b2 = b(this.mFields[1]);
                                    } else {
                                        throw new IllegalArgumentException("out of range of Chinese Lunar Year");
                                    }
                                }
                                if (this.mFields[6] == b2) {
                                    this.mFields[8] = 1;
                                }
                            }
                            i3++;
                        }
                        if (this.mFields[8] == 1) {
                            i5 = leapDaysInChineseYear(this.mFields[2]);
                        } else {
                            i5 = daysInChineseMonth(this.mFields[2], this.mFields[6]);
                        }
                        if (this.mFields[10] > i5) {
                            this.mFields[10] = i5;
                        }
                        b();
                        break;
                    } else {
                        throw new IllegalArgumentException("out of range of Chinese Lunar Year");
                    }
                }
                break;
            case 9:
            case 10:
            case 12:
            case 13:
                if (i3 != 0) {
                    long j2 = this.mMillisecond + (((long) i3) * 86400000);
                    boolean z2 = i3 > 0;
                    if (j2 > this.mMillisecond) {
                        z = true;
                    }
                    if (z2 == z) {
                        this.mMillisecond = j2;
                        c();
                        break;
                    } else {
                        throw new IllegalArgumentException("out of range");
                    }
                }
                break;
            case 18:
                if (i3 != 0) {
                    long j3 = this.mMillisecond + (((long) i3) * 3600000);
                    boolean z3 = i3 > 0;
                    if (j3 > this.mMillisecond) {
                        z = true;
                    }
                    if (z3 == z) {
                        this.mMillisecond = j3;
                        c();
                        break;
                    } else {
                        throw new IllegalArgumentException("out of range");
                    }
                }
                break;
            case 20:
                if (i3 != 0) {
                    long j4 = this.mMillisecond + (((long) i3) * 60000);
                    boolean z4 = i3 > 0;
                    if (j4 > this.mMillisecond) {
                        z = true;
                    }
                    if (z4 == z) {
                        this.mMillisecond = j4;
                        c();
                        break;
                    } else {
                        throw new IllegalArgumentException("out of range");
                    }
                }
                break;
            case 21:
                if (i3 != 0) {
                    long j5 = this.mMillisecond + (((long) i3) * 1000);
                    boolean z5 = i3 > 0;
                    if (j5 > this.mMillisecond) {
                        z = true;
                    }
                    if (z5 == z) {
                        this.mMillisecond = j5;
                        c();
                        break;
                    } else {
                        throw new IllegalArgumentException("out of range");
                    }
                }
                break;
            case 22:
                if (i3 != 0) {
                    long j6 = this.mMillisecond + ((long) i3);
                    boolean z6 = i3 > 0;
                    if (j6 > this.mMillisecond) {
                        z = true;
                    }
                    if (z6 == z) {
                        this.mMillisecond = j6;
                        c();
                        break;
                    } else {
                        throw new IllegalArgumentException("out of range");
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("unsupported set field:" + h[i2]);
        }
        return this;
    }

    private void a() {
        int i2 = this.mFields[1];
        int i3 = this.mFields[5];
        int i4 = this.mFields[9];
        long c2 = c((long) i2) + ((long) ((b(isLeapYear(i2), i3) + i4) - 1));
        int[] iArr = this.mFields;
        int a2 = a(c2 - 3, 7) + 1;
        iArr[14] = a2;
        int i5 = (this.mFields[18] * 3600000) + (this.mFields[20] * 60000) + (this.mFields[21] * 1000) + this.mFields[22];
        this.mMillisecond = (c2 * 86400000) + ((long) i5);
        long offset = (long) this.mTimeZone.getOffset(1, i2, i3, i4, a2, i5);
        if (this.mTimeZone.inDaylightTime(new Date(this.mMillisecond))) {
            offset -= (long) c(i5);
        }
        long j2 = this.mMillisecond;
        if (i2 <= 0) {
            offset = 0;
        }
        this.mMillisecond = j2 - offset;
        c();
    }

    private void b() {
        long j2 = ((long) j[this.mFields[2] - 1900]) - 25537;
        for (int i2 = 0; i2 < this.mFields[6]; i2++) {
            j2 += (long) daysInChineseMonth(this.mFields[2], i2);
        }
        if (this.mFields[8] == 1) {
            j2 += (long) daysInChineseMonth(this.mFields[2], this.mFields[6]);
        } else {
            int b2 = b(this.mFields[2]);
            if (b2 >= 0 && b2 < this.mFields[6]) {
                j2 += (long) leapDaysInChineseYear(this.mFields[2]);
            }
        }
        a(j2 + ((long) (this.mFields[10] - 1)), 0);
        a();
    }

    public int get(int i2) {
        if (i2 >= 0 && i2 < 25) {
            return this.mFields[i2];
        }
        throw new IllegalArgumentException("Field out of range [0-25]: " + i2);
    }

    private void c() {
        long e2 = e();
        if (!outOfChineseCalendarRange()) {
            b(e2);
            d();
            a(e2);
        }
    }

    public boolean outOfChineseCalendarRange() {
        return this.mMillisecond < (c - ((long) this.mFields[23])) - ((long) this.mFields[24]) || this.mMillisecond >= (d - ((long) this.mFields[23])) - ((long) this.mFields[24]);
    }

    private void a(long j2) {
        int i2 = this.mFields[1];
        if (this.mFields[5] < 2) {
            i2--;
        }
        if (this.mFields[5] == 1 && this.mFields[9] >= (solarTermDaysOfMonth(this.mFields[1], 1) >> 8)) {
            i2 = this.mFields[1];
        }
        int i3 = i2 - 1900;
        this.mFields[3] = a((long) (i3 + 12), 12);
        this.mFields[4] = a((long) (i3 + 36), 60);
        int solarTermDaysOfMonth = solarTermDaysOfMonth(this.mFields[1], this.mFields[5]) >> 8;
        int i4 = ((this.mFields[1] - 1900) * 12) + this.mFields[5];
        if (this.mFields[9] >= solarTermDaysOfMonth) {
            i4++;
        }
        this.mFields[7] = a((long) (i4 + 12), 60);
        int i5 = (int) (j2 - -25537);
        this.mFields[11] = a((long) (i5 + 40), 60);
        this.mFields[19] = a((long) ((i5 * 12) + (((this.mFields[18] + 1) % 24) / 2)), 60);
    }

    private void d() {
        int solarTermDaysOfMonth = solarTermDaysOfMonth(this.mFields[1], this.mFields[5]);
        if (this.mFields[9] == (solarTermDaysOfMonth >> 8)) {
            this.mFields[15] = (this.mFields[5] * 2) + 1;
        } else if (this.mFields[9] == (solarTermDaysOfMonth & 255)) {
            this.mFields[15] = (this.mFields[5] * 2) + 2;
        } else {
            this.mFields[15] = 0;
        }
    }

    private void b(long j2) {
        int i2 = (int) (j2 - -25537);
        int i3 = 2100;
        if (this.mFields[1] < 2100) {
            i3 = this.mFields[1] + 1;
        }
        int i4 = i2 - j[i3 - 1900];
        if (i4 < 0) {
            i3--;
            i4 += daysInChineseYear(i3);
        }
        if (i4 < 0) {
            i3--;
            i4 += daysInChineseYear(i3);
        }
        this.mFields[2] = i3;
        this.mFields[13] = i4 + 1;
        int b2 = b(i3);
        int i5 = 0;
        int i6 = i4;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < 12 && i6 > 0) {
            if (b2 >= 0 && i7 == b2 + 1 && i8 == 0) {
                i7--;
                i9 = leapDaysInChineseYear(i3);
                i8 = 1;
            } else {
                i9 = daysInChineseMonth(i3, i7);
            }
            if (i8 != 0 && i7 == b2 + 1) {
                i8 = 0;
            }
            i6 -= i9;
            i7++;
        }
        if (i6 != 0 || b2 <= 0 || i7 != b2 + 1) {
            i5 = i8;
        } else if (i8 == 0) {
            i7--;
            i5 = 1;
        }
        if (i6 < 0) {
            i6 += i9;
            i7--;
        }
        this.mFields[8] = i5;
        this.mFields[6] = i7;
        this.mFields[10] = i6 + 1;
    }

    public void setGregorianChange(long j2) {
        this.p = j2;
        Calendar calendar = new Calendar(TimeZone.getTimeZone("GMT"));
        calendar.setTimeInMillis(j2);
        this.q = calendar.get(1);
        if (calendar.get(0) == 0) {
            this.q = 1 - this.q;
        }
        this.r = ((this.q / 100) - (this.q / 400)) - 2;
        this.s = (((this.q - 2000) / 400) + this.r) - ((this.q - 2000) / 100);
        int i2 = calendar.get(12);
        if (i2 < this.s) {
            this.t = i2 - 1;
            this.u = (this.s - i2) + 1;
            return;
        }
        this.u = 0;
        this.t = this.s;
    }

    private long e() {
        int i2;
        long j2;
        this.mFields[23] = this.mTimeZone.getRawOffset();
        long j3 = this.mMillisecond / 86400000;
        int i3 = (int) (this.mMillisecond % 86400000);
        if (i3 < 0) {
            i3 += 86400000;
            j3--;
        }
        int i4 = i3 + this.mFields[23];
        while (i4 < 0) {
            i4 += 86400000;
            j3--;
        }
        while (i2 >= 86400000) {
            i4 = i2 - 86400000;
            j3++;
        }
        int i5 = this.mFields[23];
        long j4 = this.mMillisecond + ((long) i5);
        if (this.mMillisecond > 0 && j4 < 0 && i5 > 0) {
            j4 = Long.MAX_VALUE;
        } else if (this.mMillisecond < 0 && j4 > 0 && i5 < 0) {
            j4 = Long.MIN_VALUE;
        }
        a(j3, j4);
        int c2 = c(i2);
        this.mFields[24] = c2;
        if (c2 != 0) {
            i2 += c2;
            if (i2 < 0) {
                i2 += 86400000;
                j2 = j3 - 1;
            } else if (i2 >= 86400000) {
                i2 -= 86400000;
                j2 = 1 + j3;
            } else {
                j2 = j3;
            }
            if (j3 != j2) {
                int i6 = this.mFields[24] - this.mFields[23];
                long j5 = this.mMillisecond + ((long) i6);
                if (this.mMillisecond > 0 && j5 < 0 && i6 > 0) {
                    j5 = Long.MAX_VALUE;
                } else if (this.mMillisecond < 0 && j5 > 0 && i6 < 0) {
                    j5 = Long.MIN_VALUE;
                }
                a(j2, j5);
            }
            j3 = j2;
        }
        if (this.mFields[1] <= 0) {
            this.mFields[0] = 0;
            this.mFields[1] = 1 - this.mFields[1];
        } else {
            this.mFields[0] = 1;
        }
        this.mFields[22] = i2 % 1000;
        int i7 = i2 / 1000;
        this.mFields[21] = i7 % 60;
        int i8 = i7 / 60;
        this.mFields[20] = i8 % 60;
        this.mFields[18] = (i8 / 60) % 24;
        this.mFields[17] = this.mFields[18] > 11 ? 1 : 0;
        switch (this.mFields[18]) {
            case 0:
                this.mFields[16] = 0;
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                this.mFields[16] = 1;
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                this.mFields[16] = 2;
                break;
            case 12:
                this.mFields[16] = 3;
                break;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                this.mFields[16] = 4;
                break;
            case 18:
                this.mFields[16] = 5;
                break;
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                this.mFields[16] = 6;
                break;
        }
        return j3;
    }

    public boolean isChineseLeapMonth() {
        return this.mFields[8] == 1;
    }

    public int getChineseLeapMonth() {
        return b(this.mFields[2]);
    }

    public int compareTo(Calendar calendar) {
        if (this.mMillisecond < calendar.mMillisecond) {
            return -1;
        }
        return this.mMillisecond == calendar.mMillisecond ? 0 : 1;
    }

    public int hashCode() {
        return (int) (this.mMillisecond ^ (this.mMillisecond >>> 32));
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof Calendar) && this.mMillisecond == ((Calendar) obj).mMillisecond);
    }

    public String toString() {
        StringBuilder b2 = Pools.a().b();
        b2.append(getClass().getName());
        b2.append("[time");
        b2.append(this.mMillisecond);
        b2.append(",zone=");
        b2.append(this.mTimeZone.getID());
        for (int i2 = 0; i2 < 25; i2++) {
            b2.append(',');
            b2.append(h[i2]);
            b2.append('=');
            b2.append(this.mFields[i2]);
        }
        b2.append(Operators.ARRAY_END);
        String sb = b2.toString();
        Pools.a().b(b2);
        return sb;
    }

    public final Object clone() {
        try {
            Calendar calendar = (Calendar) super.clone();
            calendar.mFields = (int[]) this.mFields.clone();
            calendar.mTimeZone = (TimeZone) this.mTimeZone.clone();
            return calendar;
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }

    public boolean after(Calendar calendar) {
        return getTimeInMillis() > calendar.getTimeInMillis();
    }

    public boolean before(Calendar calendar) {
        return getTimeInMillis() < calendar.getTimeInMillis();
    }

    public int getActualMaximum(int i2) {
        if (i2 < 0 || i2 >= 25) {
            throw new IllegalArgumentException("Field out of range [0-25]: " + i2);
        }
        switch (i2) {
            case 0:
                return 1;
            case 1:
                return this.mFields[0] == 1 ? 292278994 : 292269055;
            case 2:
                return 2100;
            case 3:
                return 11;
            case 4:
            case 7:
            case 11:
            case 19:
                return 59;
            case 5:
            case 6:
                return 11;
            case 8:
                return 1;
            case 9:
                return a(isLeapYear(this.mFields[1]), this.mFields[5]);
            case 10:
                if (outOfChineseCalendarRange()) {
                    return 0;
                }
                return isChineseLeapMonth() ? leapDaysInChineseYear(this.mFields[2]) : daysInChineseMonth(this.mFields[2], this.mFields[6]);
            case 12:
                return a(this.mFields[1]);
            case 13:
                if (outOfChineseCalendarRange()) {
                    return 0;
                }
                return daysInChineseYear(this.mFields[2]);
            case 14:
                return 7;
            case 15:
                return 24;
            case 16:
                return 6;
            case 17:
                return 1;
            case 18:
                return 23;
            case 20:
                return 59;
            case 21:
                return 59;
            case 22:
                return 999;
            default:
                throw new IllegalArgumentException("unsupported field: " + h[i2]);
        }
    }

    public int getActualMinimum(int i2) {
        if (i2 < 0 || i2 >= 25) {
            throw new IllegalArgumentException("Field out of range [0-25]: " + i2);
        }
        switch (i2) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 1900;
            case 3:
                return 0;
            case 4:
            case 7:
            case 11:
            case 19:
                return 0;
            case 5:
            case 6:
                return 0;
            case 8:
                return 0;
            case 9:
                return 1;
            case 10:
                return outOfChineseCalendarRange() ^ true ? 1 : 0;
            case 12:
                return 1;
            case 13:
                return outOfChineseCalendarRange() ^ true ? 1 : 0;
            case 14:
                return 1;
            case 15:
                return 0;
            case 16:
                return 0;
            case 17:
                return 0;
            case 18:
                return 0;
            case 20:
                return 0;
            case 21:
                return 0;
            case 22:
                return 0;
            default:
                throw new IllegalArgumentException("unsupported field: " + h[i2]);
        }
    }

    public boolean isLeapYear(int i2) {
        if (i2 > this.q) {
            if (i2 % 4 != 0 || (i2 % 100 == 0 && i2 % 400 != 0)) {
                return false;
            }
            return true;
        } else if (i2 % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void a(long j2, long j3) {
        int b2 = b(j2, j3);
        this.mFields[12] = b2;
        if (this.mFields[1] == this.q && this.p <= j3) {
            b2 += this.t;
        }
        int i2 = b2 / 32;
        boolean isLeapYear = isLeapYear(this.mFields[1]);
        int b3 = b2 - b(isLeapYear, i2);
        if (b3 > a(isLeapYear, i2)) {
            b3 -= a(isLeapYear, i2);
            i2++;
        }
        this.mFields[5] = i2;
        this.mFields[9] = b3;
        this.mFields[14] = a(j2 - 3, 7) + 1;
    }

    private int b(long j2, long j3) {
        int i2 = (j3 > this.p ? 1 : (j3 == this.p ? 0 : -1));
        int i3 = 1970;
        long j4 = i2 < 0 ? j2 - ((long) this.s) : j2;
        while (true) {
            int i4 = (int) (j4 / 365);
            if (i4 == 0) {
                break;
            }
            i3 += i4;
            j4 = j2 - c((long) i3);
        }
        if (j4 < 0) {
            i3--;
            j4 += (long) a(i3);
        }
        this.mFields[1] = i3;
        return ((int) j4) + 1;
    }

    private long c(long j2) {
        if (j2 >= 1970) {
            long j3 = ((j2 - 1970) * 365) + ((j2 - 1969) / 4);
            if (j2 > ((long) this.q)) {
                return j3 - (((j2 - 1901) / 100) - ((j2 - 1601) / 400));
            }
            if (j2 == ((long) this.q)) {
                return j3 + ((long) this.t);
            }
            if (j2 == ((long) (this.q - 1))) {
                return j3 + ((long) this.u);
            }
            return j3 + ((long) this.s);
        } else if (j2 <= ((long) this.q)) {
            return ((j2 - 1970) * 365) + ((j2 - 1972) / 4) + ((long) this.s);
        } else {
            long j4 = j2 - 2000;
            return ((((j2 - 1970) * 365) + ((j2 - 1972) / 4)) - (j4 / 100)) + (j4 / 400);
        }
    }

    private static int a(boolean z, int i2) {
        if (!z || i2 != 1) {
            return f[i2];
        }
        return f[i2] + 1;
    }

    private int a(int i2) {
        int i3 = isLeapYear(i2) ? 366 : 365;
        if (i2 == this.q) {
            i3 -= this.t;
        }
        return i2 == this.q + -1 ? i3 - this.u : i3;
    }

    private static int b(boolean z, int i2) {
        if (!z || i2 <= 1) {
            return g[i2];
        }
        return g[i2] + 1;
    }

    private static int a(long j2, int i2) {
        int i3 = (int) (j2 % ((long) i2));
        return (j2 >= 0 || i3 >= 0) ? i3 : i3 + i2;
    }

    static int daysInChineseMonth(int i2, int i3) {
        return (k[i2 + -1900] & (65536 >> (i3 + 1))) != 0 ? 30 : 29;
    }

    static int daysInChineseYear(int i2) {
        int i3 = i2 - 1900;
        return j[i3 + 1] - j[i3];
    }

    static int leapDaysInChineseYear(int i2) {
        if (b(i2) >= 0) {
            return (k[(i2 + -1900) + 1] & 15) == 15 ? 30 : 29;
        }
        return 0;
    }

    private static int b(int i2) {
        int i3 = k[i2 - 1900] & 15;
        if (i3 == 15) {
            return -1;
        }
        return i3 - 1;
    }

    static int solarTermDaysOfMonth(int i2, int i3) {
        if (i2 > 2100) {
            return 0;
        }
        int i4 = i3 * 2;
        int i5 = ((m[i2 - 1900] - 48) * 24) + i4;
        int i6 = i5 + 1;
        return (((n[i5] - 48) + l[i4]) << 8) + (n[i6] - 48) + l[i4 + 1];
    }

    public String format(CharSequence charSequence) {
        return format(charSequence, (CalendarFormatSymbols) null);
    }

    public String format(CharSequence charSequence, CalendarFormatSymbols calendarFormatSymbols) {
        StringBuilder b2 = Pools.a().b();
        String sb = format(b2, charSequence, calendarFormatSymbols).toString();
        Pools.a().b(b2);
        return sb;
    }

    public StringBuilder format(StringBuilder sb, CharSequence charSequence) {
        return format(sb, charSequence, (CalendarFormatSymbols) null);
    }

    public StringBuilder format(StringBuilder sb, CharSequence charSequence, CalendarFormatSymbols calendarFormatSymbols) {
        int i2;
        if (calendarFormatSymbols == null) {
            calendarFormatSymbols = CalendarFormatSymbols.a();
        }
        int length = charSequence.length();
        int i3 = 0;
        boolean z = false;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            if (z) {
                if (charAt == '\'') {
                    i2 = i3 + 1;
                    if (i2 >= length || charSequence.charAt(i2) != charAt) {
                        z = false;
                    } else {
                        sb.append(charAt);
                    }
                } else {
                    sb.append(charAt);
                }
                i3++;
            } else {
                if (charAt == '\'') {
                    i2 = i3 + 1;
                    if (i2 >= length || charSequence.charAt(i2) != charAt) {
                        z = true;
                    } else {
                        sb.append(charAt);
                    }
                } else {
                    if (charAt >= 'A' && charAt <= 'z') {
                        int i4 = charAt - 'A';
                        if (i[i4] >= 0) {
                            int i5 = i3;
                            int i6 = 1;
                            while (true) {
                                int i7 = i5 + 1;
                                if (i7 >= length || charSequence.charAt(i7) != charAt) {
                                    a(sb, calendarFormatSymbols, charAt, i6, i[i4]);
                                    i3 = i5;
                                } else {
                                    i6++;
                                    i5 = i7;
                                }
                            }
                            a(sb, calendarFormatSymbols, charAt, i6, i[i4]);
                            i3 = i5;
                        }
                    }
                    sb.append(charAt);
                }
                i3++;
            }
            i3 = i2;
            i3++;
        }
        return sb;
    }

    private void a(StringBuilder sb, CalendarFormatSymbols calendarFormatSymbols, char c2, int i2, int i3) {
        switch (c2) {
            case 'A':
                sb.append(calendarFormatSymbols.o()[this.mFields[3]]);
                return;
            case 'D':
            case 'H':
            case 'S':
            case 'd':
            case 'm':
            case 's':
                a(sb, i2, this.mFields[i3]);
                return;
            case 'E':
            case 'c':
                if (i2 == 4) {
                    sb.append(calendarFormatSymbols.s()[this.mFields[14] - 1]);
                    return;
                } else if (i2 == 5) {
                    sb.append(calendarFormatSymbols.r()[this.mFields[14] - 1]);
                    return;
                } else {
                    sb.append(calendarFormatSymbols.q()[this.mFields[14] - 1]);
                    return;
                }
            case 'G':
                sb.append(calendarFormatSymbols.p()[this.mFields[0]]);
                return;
            case 'I':
                if (i2 == 2) {
                    sb.append(calendarFormatSymbols.n()[this.mFields[19] % 10]);
                }
                sb.append(calendarFormatSymbols.j()[this.mFields[19] % 12]);
                return;
            case 'K':
                a(sb, i2, this.mFields[18] % 12);
                return;
            case 'L':
            case 'M':
                if (i2 < 3) {
                    a(sb, i2, this.mFields[5] + 1);
                    return;
                } else if (i2 == 4) {
                    sb.append(calendarFormatSymbols.m()[this.mFields[5]]);
                    return;
                } else if (i2 == 5) {
                    sb.append(calendarFormatSymbols.l()[this.mFields[5]]);
                    return;
                } else {
                    sb.append(calendarFormatSymbols.k()[this.mFields[5]]);
                    return;
                }
            case 'N':
                if (i2 != 2) {
                    sb.append(calendarFormatSymbols.h()[this.mFields[8]]);
                    sb.append(calendarFormatSymbols.i()[this.mFields[6]]);
                    return;
                }
                sb.append(calendarFormatSymbols.n()[this.mFields[7] % 10]);
                sb.append(calendarFormatSymbols.j()[this.mFields[7] % 12]);
                return;
            case 'Y':
                if (i2 != 2) {
                    String[] g2 = calendarFormatSymbols.g();
                    int i4 = this.mFields[2];
                    int length = sb.length();
                    while (i4 > 0) {
                        int i5 = i4 % 10;
                        i4 /= 10;
                        sb.insert(length, g2[i5]);
                    }
                    return;
                }
                sb.append(calendarFormatSymbols.n()[this.mFields[4] % 10]);
                sb.append(calendarFormatSymbols.j()[this.mFields[4] % 12]);
                return;
            case 'Z':
                if (i2 == 4) {
                    a(sb, true, true);
                    return;
                } else if (i2 == 5) {
                    a(sb, false, true);
                    return;
                } else {
                    a(sb, false, false);
                    return;
                }
            case 'a':
                if (i2 != 2) {
                    sb.append(calendarFormatSymbols.f()[this.mFields[17]]);
                    return;
                } else {
                    sb.append(calendarFormatSymbols.e()[this.mFields[16]]);
                    return;
                }
            case 'e':
                if (i2 != 2) {
                    sb.append(calendarFormatSymbols.d()[this.mFields[10] - 1]);
                    return;
                }
                sb.append(calendarFormatSymbols.n()[this.mFields[11] % 10]);
                sb.append(calendarFormatSymbols.j()[this.mFields[11] % 12]);
                return;
            case 'h':
                int i6 = this.mFields[18] % 12;
                if (i6 == 0) {
                    i6 = 12;
                }
                a(sb, i2, i6);
                return;
            case 'k':
                a(sb, i2, this.mFields[18] == 0 ? 24 : this.mFields[18]);
                return;
            case 't':
                sb.append(calendarFormatSymbols.c()[this.mFields[15]]);
                return;
            case 'y':
                if (i2 == 2) {
                    a(sb, i2, this.mFields[1] % 100);
                    return;
                } else {
                    a(sb, i2, this.mFields[1]);
                    return;
                }
            case 'z':
                a(sb, calendarFormatSymbols, i2);
                return;
            default:
                return;
        }
    }

    private static void a(StringBuilder sb, int i2, int i3) {
        if (i3 >= 10000) {
            String num = Integer.toString(i3);
            for (int length = num.length(); length < i2; length++) {
                sb.append('0');
            }
            sb.append(num);
            return;
        }
        for (int i4 = i3 >= 1000 ? 4 : i3 >= 100 ? 3 : i3 >= 10 ? 2 : 1; i4 < i2; i4++) {
            sb.append('0');
        }
        sb.append(i3);
    }

    private void a(StringBuilder sb, CalendarFormatSymbols calendarFormatSymbols, int i2) {
        TimeZone timeZone = this.mTimeZone;
        int i3 = 1;
        boolean z = this.mFields[24] != 0;
        if (i2 != 4) {
            i3 = 0;
        }
        String displayName = timeZone.getDisplayName(z, i3, calendarFormatSymbols.b());
        if (displayName != null) {
            sb.append(displayName);
        } else {
            a(sb, false, false);
        }
    }

    private void a(StringBuilder sb, boolean z, boolean z2) {
        char c2;
        int i2 = get(23) + get(24);
        if (i2 < 0) {
            c2 = '-';
            i2 = -i2;
        } else {
            c2 = '+';
        }
        if (z) {
            sb.append("GMT");
        }
        sb.append(c2);
        a(sb, 2, i2 / 3600000);
        if (z2) {
            sb.append(Operators.CONDITION_IF_MIDDLE);
        }
        a(sb, 2, (i2 % 3600000) / 60000);
    }

    private int c(int i2) {
        int offset = this.mFields[1] <= 0 ? 0 : this.mTimeZone.getOffset(1, this.mFields[1], this.mFields[5], this.mFields[9], this.mFields[14], i2);
        if (this.mFields[1] > 0) {
            return offset - this.mFields[23];
        }
        return 0;
    }
}
