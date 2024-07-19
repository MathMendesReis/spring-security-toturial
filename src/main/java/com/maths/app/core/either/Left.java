package com.maths.app.core.either;

public final class Left<L, R> implements Either<L, R> {
    private final L value;

    public Left(L value) {
        this.value = value;
    }

    @Override
    public boolean isLeft() {
        return true;
    }

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    public L getLeftValue() {
        return value;
    }

    @Override
    public R getRightValue() {
        throw new UnsupportedOperationException("Cannot get right value from Left instance.");
    }

}
