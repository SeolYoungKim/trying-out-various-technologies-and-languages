package hello.redis;

import lombok.Getter;

@Getter
public enum Teset {
    TEST1("hi"), TEST2("bye"),;

    private final String name;

    Teset(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        System.out.println("Teset.TEST1.getName() = " + Teset.TEST1.getName());
    }
}
