package com.maths.app.core.either;

public interface Either <L,R> {
    boolean isLeft();
    boolean isRight();

    L getLeftValue();
    R getRightValue();
}
