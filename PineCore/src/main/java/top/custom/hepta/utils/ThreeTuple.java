package top.custom.hepta.utils;

import java.util.Objects;

/**
 * @author canyie
 */
public final class ThreeTuple<A, B, C> {
    public A a;
    public B b;
    public C c;

    public ThreeTuple() {
    }

    public ThreeTuple(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ThreeTuple)) return false;
        ThreeTuple<?, ?, ?> that = (ThreeTuple) obj;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b) && Objects.equals(c, that.c);
    }

    @Override public int hashCode() {
        return Objects.hashCode(a) ^ Objects.hashCode(b) ^ Objects.hashCode(c);
    }

    @Override public String toString() {
        return "ThreeTuple{A: " + a + "; b: " + b + "; c: " + c + "}";
    }
}
